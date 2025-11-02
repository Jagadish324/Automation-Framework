package examples;

import com.arc.frameworkWeb.context.DriverManager;
import com.arc.frameworkWeb.context.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Thread-safe base test class for parallel test execution.
 *
 * This class demonstrates the new ThreadLocal pattern for parallel test execution.
 * All test classes should extend this base class to benefit from:
 * - Automatic driver lifecycle management
 * - Thread-safe configuration
 * - Parallel test execution support
 * - Proper resource cleanup
 *
 * Usage:
 * <pre>
 * public class MyTest extends BaseTestThreadSafe {
 *     @Test
 *     public void testSomething() {
 *         Navigate.get("https://example.com");
 *         Button.click(By.id("login"));
 *     }
 * }
 * </pre>
 *
 * @author Automation Framework Team
 * @version 1.0
 */
public abstract class BaseTestThreadSafe {

    /**
     * Setup method called before each test.
     * Initializes WebDriver and TestContext for the current thread.
     */
    @BeforeEach
    public void setUp() {
        // Initialize WebDriver based on configuration
        WebDriver driver = initializeDriver();
        DriverManager.setWebDriver(driver);

        // Initialize test context with default values
        TestContext.initialize();
        configureTestContext();

        // Additional setup
        maximizeWindow();
        setImplicitWait();
    }

    /**
     * Teardown method called after each test.
     * Cleans up WebDriver and TestContext for the current thread.
     */
    @AfterEach
    public void tearDown() {
        // Clean up resources for this thread
        DriverManager.cleanup();
        TestContext.clear();
    }

    /**
     * Initializes the WebDriver instance based on browser configuration.
     * Override this method to customize driver initialization.
     *
     * @return WebDriver instance
     */
    protected WebDriver initializeDriver() {
        String browser = getBrowserType();

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions);

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                return new ChromeDriver(chromeOptions);
        }
    }

    /**
     * Configures the TestContext with framework settings.
     * Override this method to customize configuration.
     */
    protected void configureTestContext() {
        TestContext.setTool("selenium");
        TestContext.setBrowserType(getBrowserType());
        TestContext.setImplicitWait(10);
        TestContext.setExplicitWait(10);
        TestContext.setStepRetry(3);
        TestContext.setAutoWaitTime(3);
        TestContext.setAutoDelayBeforeAction(0);
        TestContext.setAutoDelayAfterAction(0);
    }

    /**
     * Gets the browser type for this test.
     * Override to specify different browser per test class.
     *
     * @return Browser type (chrome, firefox, etc.)
     */
    protected String getBrowserType() {
        // Can be overridden or read from system property
        return System.getProperty("browser", "chrome");
    }

    /**
     * Maximizes the browser window.
     */
    private void maximizeWindow() {
        DriverManager.getWebDriver().manage().window().maximize();
    }

    /**
     * Sets implicit wait timeout.
     */
    private void setImplicitWait() {
        int waitTime = TestContext.getImplicitWait();
        DriverManager.getWebDriver().manage().timeouts()
                .implicitlyWait(java.time.Duration.ofSeconds(waitTime));
    }

    /**
     * Gets the current thread's WebDriver instance.
     * Convenience method for test classes.
     *
     * @return WebDriver instance for current thread
     */
    protected WebDriver getDriver() {
        return DriverManager.getWebDriver();
    }
}
