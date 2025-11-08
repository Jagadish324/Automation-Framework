package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class TextBox extends CommonHelper {
    private static Logger log = LogManager.getLogger(TextBox.class.getName());
    /**
     * Sends the specified text to the element located by the given locator.
     * If the tool is Selenium, it waits for the element to be interactable before sending the text.
     *
     * @param locator The locator of the element to send text to
     * @param value   The text value to send
     */
    public static void sendText(By locator, String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            beforePerformingAction();
            try {
                getElement(locator).sendKeys(value);
            } catch (ElementNotInteractableException e) {
                // AutoWait should have already handled waiting for interactability
                // Log and retry once with explicit scroll into view
                log.warn("Element not interactable after AutoWait, attempting scroll and retry: " + locator);
                JavaScriptExecutor.scrollIntoView(locator);
                getElement(locator).sendKeys(value);
            }
            afterPerformingAction();
        } else {
            page.locator(getLocator(""+locator)).first().fill(value);
        }
    }
    /**
     * Types the specified text character by character into the element located by the given locator.
     * Waits for a short delay between each character.
     *
     * @param locator The locator of the element to type text into
     * @param value   The text value to type
     */
    public static void typeText(By locator, String value) {
        AutoWait.autoWait(locator);
        beforePerformingAction();
        for (int i = 0; i < value.length(); i++) {
            getElement(locator).sendKeys(value.charAt(i) + "");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        afterPerformingAction();
    }
    /**
     * Clears the element located by the given locator and sends the specified text to it.
     * If the tool is Selenium, it waits for the element to be interactable before sending the text.
     *
     * @param locator The locator of the element to clear and send text to
     * @param value   The text value to send
     */
    public static void clearAndSendText(By locator, String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            clear(locator);
            try {
                getElement(locator).sendKeys(value);
            } catch (ElementNotInteractableException e) {
                log.warn("Element not interactable after AutoWait, attempting scroll and retry: " + locator);
                JavaScriptExecutor.scrollIntoView(locator);
                getElement(locator).sendKeys(value);
            }
            afterPerformingAction();
        } else {
            page.locator(getLocator(""+locator)).first().fill(value);
        }
    }
    /**
     * Clears the text in the element located by the given locator using keyboard actions and sends the specified text to it.
     * Waits for the element to be interactable before sending the text.
     *
     * @param locator The locator of the element to clear and send text to
     * @param value   The text value to send
     */
    public static void actionClearAndSendText(By locator, String value) {
        AutoWait.autoWait(locator);
        KeyBoard.clearText(locator);
        try {
            getElement(locator).sendKeys(value);
        } catch (ElementNotInteractableException e) {
            log.warn("Element not interactable after AutoWait, attempting scroll and retry: " + locator);
            JavaScriptExecutor.scrollIntoView(locator);
            getElement(locator).sendKeys(value);
        }
        afterPerformingAction();
    }
    /**
     * Clears the element located by the given WebElement and sends the specified text to it.
     * Waits for the element to be interactable before sending the text.
     *
     * @param locator The WebElement of the element to clear and send text to
     * @param value   The text value to send
     */
    public static void clearAndSendText(WebElement locator, String value) {
        AutoWait.autoWait(locator);
        clear(locator);
        try {
            locator.sendKeys(value);
        } catch (ElementNotInteractableException e) {
            log.warn("Element not interactable after AutoWait, attempting scroll and retry");
            JavaScriptExecutor.scrollIntoView(locator);
            locator.sendKeys(value);
        }
        afterPerformingAction();
    }
    /**
     * Clears the element located by the given locator.
     *
     * @param locator The locator of the element to clear
     */
    public static void clear(By locator) {
        beforePerformingAction();
        getElement(locator).clear();
    }
    /**
     * Clears the text in the given WebElement.
     *
     * @param locator The WebElement of the element to clear
     */
    public static void clear(WebElement locator) {
        beforePerformingAction();
        locator.clear();
    }
    /**
     * Sends the specified text to the given WebElement.
     * Waits for the element to be interactable before sending the text.
     *
     * @param locator The WebElement of the element to send text to
     * @param value   The text value to send
     */
    public static void sendText(WebElement locator, String value) {
        AutoWait.autoWait(locator);
        beforePerformingAction();
        try {
            locator.sendKeys(value);
        } catch (ElementNotInteractableException e) {
            log.warn("Element not interactable after AutoWait, attempting scroll and retry");
            JavaScriptExecutor.scrollIntoView(locator);
            locator.sendKeys(value);
        }
        afterPerformingAction();
    }
    /**
     * Clicks inside a text box identified by the given locator.
     * Waits for the element to be interactable before clicking.
     *
     * @param locator The locator of the text box to click inside
     */
    public static void clickInTextBox(By locator) {
        AutoWait.autoWait(locator);
        beforePerformingAction();
        getElement(locator).click();
        afterPerformingAction();
    }
    /**
     * Gets the text content of the element located by the given locator.
     * If the tool is Selenium, it returns the text using Selenium's getText() method.
     * Otherwise, it returns the text using the page object's textContent() method.
     *
     * @param locator The locator of the element to get text from
     * @return The text content of the element
     */
    public static String getText(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
        String str = null;
        AutoWait.autoWait(locator);
        str = getElement(locator).getText();
        return str;
        }else {
            return page.locator(getLocator(""+locator)).first().textContent();
        }
    }
    /**
     * Gets the text content of the given WebElement.
     * If the tool is Selenium, it returns the text using Selenium's getText() method.
     * Otherwise, it returns the text using the page object's textContent() method.
     *
     * @param locator The WebElement of the element to get text from
     * @return The text content of the element
     */
    public static String getText(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            String str = null;
            AutoWait.autoWait(locator);
            str = locator.getText();
            return str;
        }else {
            return page.locator(getLocator(""+locator)).first().textContent();
        }
    }
    /**
     * Clears the text in the element located by the given locator and presses the backspace button
     * to clear any remaining text.
     *
     * @param locator       The locator of the element to clear
     * @param attributeName The attribute name to get the text length
     */
    public static void clearTextBackButton(By locator, String attributeName) {
        int textLength  = ElementInfo.getAttributeValue(locator,attributeName).length();
        Button.click(locator);
        KeyBoard.pressDown();
        if(textLength > 0) {
            Actions action = new Actions(webDriver);
            for (int i = 0; i < textLength; i++) {
                action.sendKeys(Keys.BACK_SPACE).perform();
            }
        }
    }
}

