package com.arc.frameworkWeb.context;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * ThreadLocal-based DriverManager for thread-safe parallel test execution.
 *
 * This class manages WebDriver and Playwright Page instances using ThreadLocal,
 * ensuring each thread has its own isolated driver instance. This enables:
 * - Parallel test execution
 * - Test isolation
 * - Better resource management
 * - Thread safety
 *
 * Usage Example:
 * <pre>
 * // Set driver for current thread
 * WebDriver driver = new ChromeDriver();
 * DriverManager.setWebDriver(driver);
 *
 * // Get driver in any helper class
 * WebDriver currentDriver = DriverManager.getWebDriver();
 *
 * // Clean up after test
 * DriverManager.quitWebDriver();
 * </pre>
 *
 * @author Automation Framework Team
 * @version 1.0
 * @since 0.0.2
 */
public class DriverManager {

    private static final Logger log = LogManager.getLogger(DriverManager.class.getName());

    /**
     * ThreadLocal storage for WebDriver instances.
     * Each thread gets its own WebDriver instance, preventing interference
     * between parallel test executions.
     */
    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    /**
     * ThreadLocal storage for Playwright Page instances.
     * Each thread gets its own Page instance for Playwright-based tests.
     */
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private DriverManager() {
        // Prevent instantiation
    }

    /**
     * Sets the WebDriver instance for the current thread.
     *
     * @param driver The WebDriver instance to set for this thread
     * @throws IllegalArgumentException if driver is null
     */
    public static void setWebDriver(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        webDriverThreadLocal.set(driver);
        log.debug("WebDriver set for thread: {}", Thread.currentThread().getName());
    }

    /**
     * Gets the WebDriver instance for the current thread.
     *
     * @return The WebDriver instance for this thread
     * @throws IllegalStateException if no WebDriver has been set for this thread
     */
    public static WebDriver getWebDriver() {
        WebDriver driver = webDriverThreadLocal.get();
        if (driver == null) {
            String errorMsg = "No WebDriver found for thread: " + Thread.currentThread().getName() +
                            ". Did you forget to call DriverManager.setWebDriver()?";
            log.error(errorMsg);
            throw new IllegalStateException(errorMsg);
        }
        return driver;
    }

    /**
     * Checks if a WebDriver instance exists for the current thread.
     *
     * @return true if a WebDriver is set for this thread, false otherwise
     */
    public static boolean hasWebDriver() {
        return webDriverThreadLocal.get() != null;
    }

    /**
     * Sets the Playwright Page instance for the current thread.
     *
     * @param page The Page instance to set for this thread
     * @throws IllegalArgumentException if page is null
     */
    public static void setPage(Page page) {
        if (page == null) {
            throw new IllegalArgumentException("Page cannot be null");
        }
        pageThreadLocal.set(page);
        log.debug("Playwright Page set for thread: {}", Thread.currentThread().getName());
    }

    /**
     * Gets the Playwright Page instance for the current thread.
     *
     * @return The Page instance for this thread
     * @throws IllegalStateException if no Page has been set for this thread
     */
    public static Page getPage() {
        Page page = pageThreadLocal.get();
        if (page == null) {
            String errorMsg = "No Playwright Page found for thread: " + Thread.currentThread().getName() +
                            ". Did you forget to call DriverManager.setPage()?";
            log.error(errorMsg);
            throw new IllegalStateException(errorMsg);
        }
        return page;
    }

    /**
     * Checks if a Playwright Page instance exists for the current thread.
     *
     * @return true if a Page is set for this thread, false otherwise
     */
    public static boolean hasPage() {
        return pageThreadLocal.get() != null;
    }

    /**
     * Quits the WebDriver instance for the current thread and removes it from ThreadLocal.
     * This method should be called in the test teardown to properly clean up resources.
     *
     * Best practice: Call this in @AfterEach or @AfterMethod
     */
    public static void quitWebDriver() {
        WebDriver driver = webDriverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                log.info("WebDriver quit successfully for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                log.error("Error quitting WebDriver for thread: {}", Thread.currentThread().getName(), e);
            } finally {
                webDriverThreadLocal.remove();
            }
        }
    }

    /**
     * Closes the Playwright Page instance for the current thread and removes it from ThreadLocal.
     * This method should be called in the test teardown to properly clean up resources.
     *
     * Best practice: Call this in @AfterEach or @AfterMethod
     */
    public static void closePage() {
        Page page = pageThreadLocal.get();
        if (page != null) {
            try {
                page.close();
                log.info("Playwright Page closed successfully for thread: {}", Thread.currentThread().getName());
            } catch (Exception e) {
                log.error("Error closing Playwright Page for thread: {}", Thread.currentThread().getName(), e);
            } finally {
                pageThreadLocal.remove();
            }
        }
    }

    /**
     * Removes the WebDriver from ThreadLocal without quitting it.
     * Use this if you want to manage driver lifecycle externally.
     *
     * Note: This does NOT quit the driver - you must do that yourself.
     */
    public static void removeWebDriver() {
        webDriverThreadLocal.remove();
        log.debug("WebDriver removed from thread: {}", Thread.currentThread().getName());
    }

    /**
     * Removes the Page from ThreadLocal without closing it.
     * Use this if you want to manage page lifecycle externally.
     *
     * Note: This does NOT close the page - you must do that yourself.
     */
    public static void removePage() {
        pageThreadLocal.remove();
        log.debug("Playwright Page removed from thread: {}", Thread.currentThread().getName());
    }

    /**
     * Cleans up all resources (both WebDriver and Page) for the current thread.
     * This is a convenience method that quits WebDriver and closes Page if they exist.
     *
     * Best practice: Call this in @AfterEach or @AfterMethod to ensure cleanup.
     */
    public static void cleanup() {
        quitWebDriver();
        closePage();
        log.info("Cleanup completed for thread: {}", Thread.currentThread().getName());
    }
}
