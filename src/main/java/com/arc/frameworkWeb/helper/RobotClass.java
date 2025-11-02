package com.arc.frameworkWeb.helper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotClass {
    /**
     * Simulates the key press and release of CTRL+SHIFT+U using the Robot class.
     */
    public static void robotCtrlShiftU() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_U);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_U);
    }

    /**
     * Simulates the key press and release of ENTER using the Robot class.
     */
    public static void robotEnter() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        ExplicitWait.hardWait(500);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * Mouse hover based on coordinate
     *
     * @param x The x-coordinate of the click
     * @param y The y-coordinate of the click
     */
    public static void mouseHover(int x, int y) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.mouseMove(x, y);
    }

    /**
     * Opens a new tab and closes it using the Robot class.
     */
    public static void openTabAndCloseTab() {
        openTab();
        ExplicitWait.hardWait(500);
        closeTab();
    }

    /**
     * Opens a new tab using the Robot class.
     */
    public static void openTab() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
    }

    /**
     * Close Tab using the Robot class.
     */
    public static void closeTab() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_F4);
    }

    /**
     * Simulates a mouse right click at the specified coordinates using the Robot class.
     *
     * @param x The x-coordinate of the click
     * @param y The y-coordinate of the click
     */
    public static void robotCoordinateContextClick(int x, int y) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.mouseMove(x, y);
        //Clicks Left mouse button
        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }

    /**
     * Simulates a mouse double click at the specified coordinates using the Robot class.
     *
     * @param x The x-coordinate of the click
     * @param y The y-coordinate of the click
     */
    public static void robotCoordinateDoubleClick(int x, int y) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.mouseMove(x, y);
        //Clicks Left mouse button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


    }

    /**
     * Types the specified text using the Robot class.
     *
     * @param text The text to type
     */
    public static void typeText(String text) {
        try {
            Robot robot = new Robot();
            robot.delay(1000); // Delay to give you time to focus on the text box
            for (char c : text.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                    throw new RuntimeException("Key code not found for character '" + c + "'");
                }
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simulates a mouse click at the specified coordinates using the Robot class.
     *
     * @param x The x-coordinate of the click
     * @param y The y-coordinate of the click
     */

    public static void clickByCoordinate(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pastes the specified text into a text field using the Robot class.
     *
     * @param text The text to paste
     */
    public static void pasteInTheTextField(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
