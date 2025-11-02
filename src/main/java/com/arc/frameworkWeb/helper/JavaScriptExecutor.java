package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutor extends CommonHelper {
    private static Logger log = LogManager.getLogger(JavaScriptExecutor.class.getName());

    private static JavascriptExecutor executor;

    /**
     * Clicks on an element using JavaScript executor.
     *
     * @param locator The locator of the element to click.
     */
    public static void forceClickJSE(By locator) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", webDriver.findElement(locator));
    }

    /**
     * Clicks on a WebElement using JavaScript executor.
     *
     * @param locator The WebElement to click.
     */
    public static void forceClickJSE(WebElement locator) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", locator);
    }

    /**
     * Gets the text of an element using JavaScript executor.
     *
     * @param locator The id of the element to get text from.
     * @return The text of the element.
     */
    public static String getTextJSE(String locator) {
        executor = (JavascriptExecutor) webDriver;
        String text = (String) executor.executeScript("return document.getElementById('" + locator + "').value");
        return text;
    }

    /**
     * Gets the text of a WebElement using JavaScript executor.
     *
     * @param locator The WebElement to get text from.
     * @return The text of the WebElement.
     */
    public static String getTextJSE(WebElement locator) {
        executor = (JavascriptExecutor) webDriver;
        String text = (String) executor.executeScript("return arguments[0].value;", locator);
        return text;
    }

    /**
     * Simulates a mouse click on an element using JavaScript executor.
     *
     * @param locator The locator of the element to click.
     */
    public static void mouseClickJSE(By locator) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", getElement(locator));
    }

    /**
     * Simulates a mouse click on a WebElement using JavaScript executor.
     *
     * @param locator The WebElement to click.
     */
    public static void mouseClickJSE(WebElement locator) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", locator);
    }

    /**
     * Scrolls the element into view using JavaScript executor.
     *
     * @param locator The locator of the element to scroll into view.
     */
    public static void scrollIntoView(By locator) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"center\"});", webDriver.findElement(locator));
    }

    /**
     * Scrolls the WebElement into view using JavaScript executor.
     *
     * @param locator The WebElement to scroll into view.
     */
    public static void scrollIntoView(WebElement locator) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"center\"});", locator);
    }

    /**
     * Enters text into an input field using JavaScript executor.
     *
     * @param locator The locator of the input field.
     * @param value   The value to enter into the input field.
     */

    public static void javaScriptSendKeys(By locator, String value) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].value='" + value + "';", getElement(locator));
    }

    /**
     * Waits for the page to load completely using JavaScript executor.
     */
    public static void waitForPageToLoad() {
        executor = (JavascriptExecutor) webDriver;
        //This loop will rotate for 25 times to check If page Is ready after every 1 second.
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //To check page ready state.
            if (executor.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }

    /**
     * Gets the value of a specified attribute of an element using JavaScript executor.
     *
     * @param locator   The locator of the element.
     * @param attribute The attribute to get the value of.
     * @return The value of the attribute.
     */
    public static String getAttributeValueJS(By locator, String attribute) {
        executor = (JavascriptExecutor) webDriver;
        return ((String) executor.executeScript("return arguments[0]." + attribute + ";", getElement(locator)));
    }

    /**
     * Gets the value of a specified attribute of a WebElement using JavaScript executor.
     *
     * @param locator   The WebElement.
     * @param attribute The attribute to get the value of.
     * @return The value of the attribute.
     */
    public static String getAttributeValueJS(WebElement locator, String attribute) {
        executor = (JavascriptExecutor) webDriver;
        return ((String) executor.executeScript("return arguments[0]." + attribute + ";", locator));
    }

    public static void scrollUntilVisibleElement(By locator) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);", CommonHelper.getElement(locator));

    }

    public static void scrollUntilVisibleElement(WebElement element) {
        executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);

    }


}
