package base;

import com.arc.frameworkWeb.context.DriverManager;
import com.arc.frameworkWeb.context.PlaywrightManager;
import com.arc.frameworkWeb.helper.CommonHelper;
import com.arc.frameworkWeb.utility.CONSTANT;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * BaseClass provides browser/tool setup and teardown for both Selenium and Playwright.
 *
 * For Selenium:
 *   CONSTANT.TOOL = "selenium";
 *   CONSTANT.BROWSER_TYPE = "chrome"; // or firefox, edge, safari
 *   WebDriver driver = BaseClass.launchSeleniumBrowser();
 *
 * For Playwright:
 *   CONSTANT.TOOL = "playwright";
 *   BaseClass.launchPlaywrightBrowser("chromium", false); // or firefox, webkit
 *
 * Teardown (call in @After):
 *   BaseClass.quitBrowser();
 */
public class BaseClass extends CommonHelper {

    public BaseClass() {
        super();
    }

    /**
     * Launches a Selenium WebDriver based on CONSTANT.BROWSER_TYPE.
     * Sets the driver in DriverManager for thread-safe usage.
     *
     * @return The launched WebDriver instance
     */
    public static WebDriver launchSeleniumBrowser() {
        WebDriver driver;
        if (CONSTANT.BROWSER_TYPE == null || CONSTANT.BROWSER_TYPE.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            CONSTANT.DEVTOOLS = ((ChromeDriver) driver).getDevTools();
        } else if (CONSTANT.BROWSER_TYPE.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            CONSTANT.DEVTOOLS = ((FirefoxDriver) driver).getDevTools();
        } else if (CONSTANT.BROWSER_TYPE.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            CONSTANT.DEVTOOLS = ((EdgeDriver) driver).getDevTools();
        } else if (CONSTANT.BROWSER_TYPE.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            driver = new ChromeDriver();
        }

        DriverManager.setWebDriver(driver);
        driver.manage().window().maximize();
        if (CONSTANT.IMPLICIT_WAIT > 0) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CONSTANT.IMPLICIT_WAIT));
        }
        return driver;
    }

    /**
     * Launches a Playwright browser and creates a new Page, registered in DriverManager.
     *
     * @param browserType "chromium", "firefox", or "webkit"
     * @param headless    true for headless mode, false for headed
     */
    public static void launchPlaywrightBrowser(String browserType, boolean headless) {
        PlaywrightManager.launchBrowser(browserType, headless);
        PlaywrightManager.createPage();
    }

    /**
     * Quits all browser resources for the current thread.
     * Works for both Selenium and Playwright based on CONSTANT.TOOL.
     */
    public static void quitBrowser() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            DriverManager.quitWebDriver();
        } else {
            PlaywrightManager.closeAll();
        }
    }
}
