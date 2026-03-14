package com.arc.frameworkWeb.context;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ThreadLocal-based PlaywrightManager for thread-safe parallel test execution.
 *
 * Manages the full Playwright lifecycle: Playwright instance → Browser → BrowserContext → Page.
 * The Page is stored via DriverManager.setPage() so all helpers can access it via getPageInstance().
 *
 * Usage:
 * <pre>
 * // Setup (before test)
 * PlaywrightManager.launchBrowser("chromium", false);
 * PlaywrightManager.createPage();
 * CONSTANT.TOOL = "playwright";
 *
 * // Teardown (after test)
 * PlaywrightManager.closeAll();
 * </pre>
 */
public class PlaywrightManager {

    private static final Logger log = LogManager.getLogger(PlaywrightManager.class.getName());

    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();

    private PlaywrightManager() {
        // Prevent instantiation
    }

    /**
     * Launches a browser in headless mode by default.
     *
     * @param browserType "chromium", "firefox", or "webkit"
     */
    public static void launchBrowser(String browserType) {
        launchBrowser(browserType, true);
    }

    /**
     * Launches a browser with configurable headless mode.
     *
     * @param browserType "chromium", "firefox", or "webkit"
     * @param headless    true for headless, false for headed
     */
    public static void launchBrowser(String browserType, boolean headless) {
        Playwright playwright = Playwright.create();
        playwrightThreadLocal.set(playwright);

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(headless);
        Browser browser;

        switch (browserType.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(options);
                break;
        }

        browserThreadLocal.set(browser);
        BrowserContext context = browser.newContext();
        contextThreadLocal.set(context);

        log.info("Playwright '{}' browser launched (headless={}) for thread: {}",
                browserType, headless, Thread.currentThread().getName());
    }

    /**
     * Creates a new Page from the current BrowserContext and registers it in DriverManager.
     * Must be called after launchBrowser().
     */
    public static void createPage() {
        BrowserContext context = getBrowserContext();
        Page page = context.newPage();
        DriverManager.setPage(page);
        log.info("Playwright Page created for thread: {}", Thread.currentThread().getName());
    }

    /**
     * Returns the Browser instance for the current thread.
     */
    public static Browser getBrowser() {
        Browser browser = browserThreadLocal.get();
        if (browser == null) {
            throw new IllegalStateException(
                    "No Browser found for thread: " + Thread.currentThread().getName() +
                    ". Call PlaywrightManager.launchBrowser() first.");
        }
        return browser;
    }

    /**
     * Returns the BrowserContext for the current thread.
     */
    public static BrowserContext getBrowserContext() {
        BrowserContext context = contextThreadLocal.get();
        if (context == null) {
            throw new IllegalStateException(
                    "No BrowserContext found for thread: " + Thread.currentThread().getName() +
                    ". Call PlaywrightManager.launchBrowser() first.");
        }
        return context;
    }

    /**
     * Returns the Playwright instance for the current thread.
     */
    public static Playwright getPlaywright() {
        Playwright playwright = playwrightThreadLocal.get();
        if (playwright == null) {
            throw new IllegalStateException(
                    "No Playwright instance found for thread: " + Thread.currentThread().getName() +
                    ". Call PlaywrightManager.launchBrowser() first.");
        }
        return playwright;
    }

    /**
     * Checks if a BrowserContext exists for the current thread.
     */
    public static boolean hasBrowserContext() {
        return contextThreadLocal.get() != null;
    }

    /**
     * Closes all Playwright resources for the current thread in the correct order:
     * Page → BrowserContext → Browser → Playwright instance.
     */
    public static void closeAll() {
        // Close page via DriverManager
        DriverManager.closePage();

        // Close BrowserContext
        BrowserContext context = contextThreadLocal.get();
        if (context != null) {
            try {
                context.close();
                log.info("BrowserContext closed for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                log.error("Error closing BrowserContext for thread: {}", Thread.currentThread().getName(), e);
            } finally {
                contextThreadLocal.remove();
            }
        }

        // Close Browser
        Browser browser = browserThreadLocal.get();
        if (browser != null) {
            try {
                browser.close();
                log.info("Browser closed for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                log.error("Error closing Browser for thread: {}", Thread.currentThread().getName(), e);
            } finally {
                browserThreadLocal.remove();
            }
        }

        // Close Playwright
        Playwright playwright = playwrightThreadLocal.get();
        if (playwright != null) {
            try {
                playwright.close();
                log.info("Playwright instance closed for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                log.error("Error closing Playwright instance for thread: {}", Thread.currentThread().getName(), e);
            } finally {
                playwrightThreadLocal.remove();
            }
        }
    }
}
