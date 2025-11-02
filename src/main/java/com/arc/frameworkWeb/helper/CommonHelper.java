package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.context.DriverManager;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * CommonHelper class provides common utility methods for interacting with web elements and performing actions on them.
 *
 * Thread Safety:
 * This class now supports both static (legacy) and ThreadLocal-based usage patterns.
 * - For parallel execution, use DriverManager.setWebDriver(driver) to set thread-local instances
 * - For single-threaded tests, the static webDriver and page fields still work (backward compatible)
 * - The getDriver() and getPageInstance() methods automatically choose the appropriate instance
 */
public class CommonHelper {
    //private static Logger log = LogManager.getLogManager().getLogger(CommonHelper.class.getName());
    private static Logger log= LogManager.getLogger(CommonHelper.class.getName());

    /**
     * @deprecated Use DriverManager.setWebDriver() and DriverManager.getWebDriver() for thread-safe parallel execution.
     * This static field is maintained for backward compatibility only.
     */
    public static WebDriver webDriver;

    /**
     * @deprecated Use DriverManager.setPage() and DriverManager.getPage() for thread-safe parallel execution.
     * This static field is maintained for backward compatibility only.
     */
    public static Page page;

    public CommonHelper(){
        super();
    }
    /**
     * Constructor that initializes CommonHelper with a WebDriver instance.
     * @param webDriver The WebDriver instance to use.
     */
    public CommonHelper(WebDriver webDriver){
        CommonHelper.webDriver = webDriver;
    }
    /**
     * Constructor that initializes CommonHelper with a Page instance.
     * @param page The Page instance to use.
     */
    public CommonHelper(Page page){
        CommonHelper.page = page;
    }
    /**
     * Converts a Selenium locator value into an XPath locator.
     * @param locatorValueInSelenium The locator value in Selenium format.
     * @return The XPath locator string.
     */
    public static String getLocator(String locatorValueInSelenium){
//        String type = "";
         if (locatorValueInSelenium.contains("By.id")) {
            String str = "By.id: combo-box-countries";
            String pattern = "By.id: (.+)";
            Pattern regex = Pattern.compile(pattern);
            return "//*[@id='" +extractSubstring(locatorValueInSelenium, regex)+"']";
        }else {
//            type="xpath";
            int startIndex = locatorValueInSelenium.indexOf('(');
            int slashIndex = locatorValueInSelenium.indexOf('/');

            if (slashIndex != -1 && (startIndex == -1 || slashIndex < startIndex)) {
                startIndex = slashIndex;
            }

            return locatorValueInSelenium.substring(startIndex);
        }

//        switch(locatorValueInSelenium) {
//            case locatorValueInSelenium.:
//                // code block
//                break;
//            case y:
//                // code block
//                break;
//            default:
//                // code block
//        }
    }
    /**
     * Gets the WebDriver instance for the current thread.
     * Supports both ThreadLocal (preferred) and static (legacy) patterns.
     *
     * @return The WebDriver instance - either from ThreadLocal or static field
     */
    protected static WebDriver getDriver() {
        // First, check if ThreadLocal driver exists (preferred for parallel execution)
        if (DriverManager.hasWebDriver()) {
            return DriverManager.getWebDriver();
        }
        // Fall back to static field for backward compatibility
        if (webDriver != null) {
            return webDriver;
        }
        throw new IllegalStateException(
            "No WebDriver found. Either set static webDriver or call DriverManager.setWebDriver(driver)"
        );
    }

    /**
     * Gets the Playwright Page instance for the current thread.
     * Supports both ThreadLocal (preferred) and static (legacy) patterns.
     *
     * @return The Page instance - either from ThreadLocal or static field
     */
    protected static Page getPageInstance() {
        // First, check if ThreadLocal page exists (preferred for parallel execution)
        if (DriverManager.hasPage()) {
            return DriverManager.getPage();
        }
        // Fall back to static field for backward compatibility
        if (page != null) {
            return page;
        }
        throw new IllegalStateException(
            "No Playwright Page found. Either set static page or call DriverManager.setPage(page)"
        );
    }

    /**
     * Retrieves a WebElement using the given locator.
     * Thread-safe: Uses getDriver() which supports both ThreadLocal and static patterns.
     *
     * @param locator The locator to use for finding the element.
     * @return The found WebElement.
     */
    public static WebElement getElement(By locator) {
            return getDriver().findElement(locator);
    }
    /**
     * Retrieves a list of WebElements using the given locator.
     * Thread-safe: Uses getDriver() which supports both ThreadLocal and static patterns.
     *
     * @param locator The locator to use for finding the elements.
     * @return The list of found WebElements.
     */
    public static List<WebElement> getListOfWebElements(By locator)
    {
        return getDriver().findElements(locator);
    }
    /**
     * Performs actions that need to be done before performing an action on a web element.
     */
    public static void beforePerformingAction(){
//        ExplicitWait.hardWait(CONSTANT.AUTO_DELAY_BEFORE_ACTION);
//        ChromeDevTools.consoleLog();
//        ChromeDevTools.networkLog();
    }
    /**
     * Performs actions that need to be done after performing an action on a web element.
     */
    public static void afterPerformingAction(){
//        ExplicitWait.hardWait(CONSTANT.AUTO_DELAY_AFTER_ACTION);
    }
    /**
     * Extracts a substring from the input string based on the provided pattern.
     * @param input The input string to extract from.
     * @param pattern The pattern to use for extracting the substring.
     * @return The extracted substring.
     */
    public static String extractSubstring(String input, Pattern pattern) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static List<String> getListFromCommaSeparatedString(String str) {
        try{
            return str.isEmpty() ? null : (new ArrayList<>(List.of(str.split(","))));
        }catch (NullPointerException e){
            return null;
        }
    }
}
