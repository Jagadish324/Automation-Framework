package com.arc.frameworkWeb.helper;

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
 */
public class CommonHelper {
    //private static Logger log = LogManager.getLogManager().getLogger(CommonHelper.class.getName());
    private static Logger log= LogManager.getLogger(CommonHelper.class.getName());
    public static WebDriver webDriver;
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
     * Retrieves a WebElement using the given locator.
     * @param locator The locator to use for finding the element.
     * @return The found WebElement.
     */
    public static WebElement getElement(By locator) {
            return webDriver.findElement(locator);
    }
    /**
     * Retrieves a list of WebElements using the given locator.
     * @param locator The locator to use for finding the elements.
     * @return The list of found WebElements.
     */
    public static List<WebElement> getListOfWebElements(By locator)
    {
        return webDriver.findElements(locator);
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
