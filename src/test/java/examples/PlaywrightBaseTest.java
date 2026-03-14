package examples;

import base.BaseClass;
import com.arc.frameworkWeb.context.DriverManager;
import com.arc.frameworkWeb.context.PlaywrightManager;
import com.arc.frameworkWeb.helper.*;
import com.arc.frameworkWeb.utility.CONSTANT;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * PlaywrightBaseTest demonstrates how to write thread-safe parallel tests using
 * the Playwright engine within this framework.
 *
 * Key setup:
 *   1. Set CONSTANT.TOOL = "playwright" (or "selenium" to use Selenium instead)
 *   2. Call PlaywrightManager.launchBrowser() + PlaywrightManager.createPage()
 *   3. Use the same helper classes (Button, TextBox, Navigate, etc.) — they auto-route to Playwright
 *   4. Call PlaywrightManager.closeAll() in teardown
 *
 * For parallel execution, configure JUnit 5 in junit-platform.properties:
 *   junit.jupiter.execution.parallel.enabled=true
 *   junit.jupiter.execution.parallel.mode.default=concurrent
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaywrightBaseTest {

    @BeforeEach
    void setUp() {
        // Switch framework to Playwright mode
        CONSTANT.TOOL = "playwright";
        CONSTANT.BROWSER_TYPE = "chromium";

        // Launch Chromium in headed mode (set true for headless/CI)
        PlaywrightManager.launchBrowser("chromium", true);
        PlaywrightManager.createPage();
    }

    @AfterEach
    void tearDown() {
        // Closes Page → BrowserContext → Browser → Playwright instance
        PlaywrightManager.closeAll();
    }

    /**
     * Example: Navigate to a URL and verify the title.
     */
    @Test
    @Order(1)
    void testNavigateAndGetTitle() {
        Navigate.navigateTo("https://example.com");
        String title = Navigate.getTitle();
        Assertions.assertNotNull(title, "Page title should not be null");
        System.out.println("Page title: " + title);
    }

    /**
     * Example: Fill a text field and click a button.
     */
    @Test
    @Order(2)
    void testFillAndClick() {
        Navigate.navigateTo("https://example.com");
        String url = Navigate.getCurrentUrl();
        Assertions.assertTrue(url.contains("example.com"), "URL should contain 'example.com'");
    }

    /**
     * Example: Parallel data-driven test using Playwright.
     * Each invocation runs in its own Playwright Page (thread-isolated via ThreadLocal).
     */
    @ParameterizedTest
    @CsvSource({
        "https://example.com, Example Domain",
        "https://www.iana.org, IANA"
    })
    void testMultipleUrlsInParallel(String url, String expectedTitleFragment) {
        Navigate.navigateTo(url);
        String title = Navigate.getTitle();
        Assertions.assertTrue(
            title.contains(expectedTitleFragment),
            "Title '" + title + "' should contain '" + expectedTitleFragment + "'"
        );
    }

    /**
     * Example: Screenshot capture with Playwright.
     */
    @Test
    @Order(3)
    void testScreenshot() {
        Navigate.navigateTo("https://example.com");
        // ScreenShot.takeScreenShot("target/screenshots/playwright_example.png");
        System.out.println("Screenshot captured (uncomment ScreenShot call to enable)");
    }

    /**
     * Example: Verify thread isolation — each test gets its own Playwright Page.
     */
    @Test
    @Order(4)
    void testThreadIsolation() {
        Assertions.assertTrue(DriverManager.hasPage(), "Each test should have its own Playwright Page");
        Assertions.assertTrue(PlaywrightManager.hasBrowserContext(), "Each test should have its own BrowserContext");
    }
}
