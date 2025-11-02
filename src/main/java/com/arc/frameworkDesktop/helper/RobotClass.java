package com.arc.frameworkDesktop.helper;

import com.arc.frameworkWeb.helper.ExplicitWait;
import org.openqa.selenium.By;

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
    public static void robotCtrlA() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
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
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * Opens a new tab and closes it using the Robot class.
     */
    public static void openTabAndCloseTab() {
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

        ExplicitWait.hardWait(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_F4);
    }

    /**
     * Simulates a mouse click at the specified coordinates using the Robot class.
     *
     * @param x The x-coordinate of the click
     * @param y The y-coordinate of the click
     */
    public static void robotMouseHoverCoordinateClick(int x, int y) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.mouseMove(x, y);
        robot.delay(500);
        //Clicks Left mouse button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

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
        ExplicitWait.hardWait(1000);

    }

    /**
     * Double click using the Robot class.
     */
    public static void robotDoubleClick() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
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
     * Types the specified text using the Robot class at an element.
     *
     * @param element is type By, text The text to type
     */
    public static void typeText(By element, String text) {
        try {
            int x = ElementInfo.getXLocation(element);
            int y = ElementInfo.getYLocation(element);
            clickByCoordinate(x, y);
            Robot robot = new Robot();
            robot.delay(1000); // Delay to give you time to focus on the text box
            for (char c : text.toCharArray()) {
                int keyCode;
                if (Character.isUpperCase(c)) {
                    keyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toLowerCase(c));
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                    if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                        throw new RuntimeException("Key code not found for character '" + c + "'");
                    }
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                }
            }

        }catch (Exception e)
        {

        }
    }

    /**
     * Types the specified text using the Robot class at an element.
     *
     * @param text The text to type
     */
    public static void typeTextAfterClick(String text) {
        try {

            Robot robot = new Robot();
            robot.delay(1000); // Delay to give you time to focus on the text box
            for (char c : text.toCharArray()) {
                int keyCode;
                if (Character.isUpperCase(c)) {
                    keyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toLowerCase(c));
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                    if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                        throw new RuntimeException("Key code not found for character '" + c + "'");
                    }
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                }
            }

        }catch (Exception e)
        {

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
