package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Actions;

public class KeyBoard extends CommonHelper{
    private static Logger log= LogManager.getLogger(KeyBoard.class.getName());

    static Actions action;
    public static Platform platformName;
    /**
     * Presses the UP arrow key.
     */
    public static void pressKeyUP() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(webDriver);
            action.sendKeys(Keys.ARROW_UP).build().perform();
        }
    }
    /**
     * Presses the specified keys with CONTROL and SHIFT keys held down.
     * @param keyValue The key value to be sent after holding down CONTROL and SHIFT keys.
     */
    public static void pressControlShift(String keyValue){
        action = new Actions(webDriver);
        action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT);
        action.sendKeys(keyValue);
        action.keyUp(Keys.CONTROL).keyUp(Keys.SHIFT);
        action.perform();
    }

    /**
     * Opens a new tab and then closes it.
     */
    public static void openTabAndCloseTab(){
        action = new Actions(webDriver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys("t");
        action.keyUp(Keys.CONTROL);
        action.perform();
        // Wait for new tab to open using proper wait
        ExplicitWait.waitForChildWindow();
        action.keyDown(Keys.CONTROL).keyDown(Keys.F4);
        action.keyUp(Keys.CONTROL).keyUp(Keys.F4);
    }
    /**
     * Presses the DOWN arrow key.
     */
    public static void pressDown() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(webDriver);
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
    }
    /**
     * Presses the ENTER key.
     */
    public static void pressEnter(){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(webDriver);
            action.sendKeys(Keys.ENTER).build().perform();
        }
    }
    /**
     * Presses the ENTER key on the specified element.
     * @param locator The locator of the element to press ENTER on.
     */
    public static void pressEnter(By locator)
    {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getElement(locator).sendKeys(Keys.ENTER);
        }
    }
    /**
     * Presses the TAB key.
     */
    public static void pressTab(){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(webDriver);
            action.sendKeys(Keys.TAB).build().perform();
        }
    }
    /**
     * Clears the text of the element located by the specified locator.
     * @param locator The locator of the element to clear.
     */
    public static void clearText(By locator){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.waitForStability(locator);
            Mouse.mouseClick(locator);
            action = new Actions(webDriver);
            if (CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
                action.click(webDriver.findElement(locator))
                        .keyDown(Keys.COMMAND)
                        .sendKeys("a")
                        .keyUp(Keys.COMMAND)
                        .sendKeys(Keys.BACK_SPACE)
                        .build()
                        .perform();
            } else {
                action.click(webDriver.findElement(locator))
                        .keyDown(Keys.CONTROL)
                        .sendKeys("a")
                        .keyUp(Keys.CONTROL)
                        .sendKeys(Keys.BACK_SPACE)
                        .build()
                        .perform();
            }
        }
    }
    /**
     * Sends the specified text to the element located by the specified locator.
     * @param locator The locator of the element to send text to.
     * @param value The text to send.
     */
    public static void sendText(By locator, String value){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(webDriver);
            action.sendKeys(getElement(locator), value).perform();
        }
    }
    /**
     * Copies the text in the specified field, then pastes the new text value.
     * @param locator The locator of the element to copy and paste text into.
     * @param value1 The text to be copied into the field.
     * @param value2 The text to be pasted into the field.
     */
    public static void copyPasteField(By locator, String value1, String value2){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(webDriver);
            Keys cmdCtrl = platformName.is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;
            action.sendKeys(getElement(locator), value1)
                    .sendKeys(Keys.ARROW_LEFT)
                    .keyDown(Keys.SHIFT)
                    .sendKeys(Keys.ARROW_UP)
                    .keyUp(Keys.SHIFT)
                    .keyDown(cmdCtrl)
                    .sendKeys(value2)
                    .keyUp(cmdCtrl)
                    .perform();
        }
    }
}
