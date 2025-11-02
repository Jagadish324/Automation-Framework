package com.arc.frameworkDesktop.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoard extends CommonHelper{
    private static Actions action;

    /**
     * Presses the UP arrow key.
     */
    public static void pressKeyUP() {
        action = new Actions(desktopDriver);
        action.sendKeys(Keys.ARROW_UP).build().perform();
    }

    /**
     * Presses the Down arrow key.
     */
    public static void pressKeyDown() {
        action = new Actions(desktopDriver);
        action.sendKeys(Keys.ARROW_DOWN).build().perform();
    }

    /**
     * Presses the Left arrow key.
     */
    public static void pressKeyLeft() {
        action = new Actions(desktopDriver);
        action.sendKeys(Keys.ARROW_LEFT).build().perform();
    }

    /**
     * Presses the Right arrow key.
     */
    public static void pressKeyRight() {
        action = new Actions(desktopDriver);
        action.sendKeys(Keys.ARROW_RIGHT).build().perform();
    }

    /**
     * Presses the Enter key.
     */
    public static void pressEnter(){
        action = new Actions(desktopDriver);
        action.sendKeys(Keys.ENTER).build().perform();
    }

    /**
     * Presses the Tab key.
     */
    public static void pressTab() {
        action = new Actions(desktopDriver);
        action.sendKeys(Keys.TAB).build().perform();
    }

    /**
     * Clears the text in the specified input field using keyboard shortcuts.
     * It selects all text (Ctrl + A) and deletes it (Backspace).
     *
     * @param locator The locator of the element to clear.
     */
    public static void clearTextByKeyboard(By locator) {
        action = new Actions(desktopDriver);
        action.click(getElement(locator))
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .build()
                .perform();
    }

    /**
     * Performs a Ctrl + Click action on the specified element.
     *
     * @param locator The locator of the element to click while holding the Ctrl key.
     */
    public static void ctrlAndClick(By locator) {
        action = new Actions(desktopDriver);
        action.keyDown(Keys.CONTROL)
                .click(getElement(locator))
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
    }

    /**
     * Copies the specified text to the system clipboard and pastes it using the keyboard.
     *
     * This method utilizes the `Toolkit` class to copy the provided text into the system clipboard
     * and then simulates the keyboard shortcut (Ctrl + V) using the `Robot` class to paste the text
     * into the active field.
     */
    public static void copyToClipboardAndPaste(String text) {
        try {
            StringSelection selection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(100);  // Short delay to ensure paste completes
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Drags an element from its current position by a specified offset.
     * The method clicks and holds the element, moves it by the given x and y offsets,
     * and then releases it to complete the drag action.
     */
    public static void dragElement(By locator, int xOffset, int yOffset) {
        action = new Actions(desktopDriver);
        action.clickAndHold(getElement(locator))
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }
}
