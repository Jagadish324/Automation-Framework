package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import com.arc.frameworkWeb.utility.CONSTANT;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;

public class Window extends CommonHelper {
    private static Logger log = LogManager.getLogger(Window.class.getName());

    private static Dimension dimension;

    /**
     * Returns the current window handle.
     *
     * @return The current window handle
     */
    public static String getWindow() {
        return webDriver.getWindowHandle();
    }

    /**
     * Closes all other tabs except the original tab.
     */
    public static void closeAllOtherTab() {
        setOriginalWindow();
        for (String handle : getWindows()) {
            if (!handle.equals(CONSTANT.ORIGINAL_WINDOW)) {
                webDriver.switchTo().window(handle);
                webDriver.close();
            }
        }
        webDriver.switchTo().window(CONSTANT.ORIGINAL_WINDOW);
    }

    /**
     * Sets the original window handle.
     */
    public static void setOriginalWindow() {
        CONSTANT.ORIGINAL_WINDOW = webDriver.getWindowHandle();
    }

    /**
     * Returns a set of window handles.
     *
     * @return A set of window handles
     */
    public static Set<String> getWindows() {
        return webDriver.getWindowHandles();
    }

    /**
     * Prints a message if no other window is present.
     */
    public static void isOtherWindowPresent() {
        if (getWindows().size() == 1)
            System.out.println("No window present");
    }

    /**
     * Waits until a new window is open.
     */
    public static void waitUntilWindowOpen() {
        ExplicitWait.waitUntilWindowOpen();
    }

    /**
     * Switches to a new tab.
     */
    public static void switchTab() {
        webDriver.switchTo().newWindow(WindowType.TAB);
    }

    /**
     * Switches to a new window.
     */
    public static void switchWindow() {
        setOriginalWindow();
        AutoWait.autoWaitWindowOpen();
        Set<String> windows = getWindows();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()) {
            String childWindow = iterator.next();
            if (!CONSTANT.ORIGINAL_WINDOW.equals(childWindow)) {
                webDriver.switchTo().window(childWindow);
                break;
            }
        }
    }

    /**
     * Switches to the original window.
     */
    public static void switchToOriginalWindow() {
        webDriver.switchTo().window(CONSTANT.ORIGINAL_WINDOW);
    }

    /**
     * Returns the width of the window.
     *
     * @return The width of the window
     */
    public static int getWindowWidth(By locator) {
        dimension = webDriver.manage().window().getSize();
        return dimension.getWidth();
    }

    /**
     * Returns the height of the window.
     *
     * @return The height of the window
     */
    public static int getWindowHeight(By locator) {
        dimension = webDriver.manage().window().getSize();
        return dimension.getHeight();
    }

    /**
     * Sets the size of the window.
     *
     * @param width  The width of the window
     * @param height The height of the window
     */
    public static void setWindowsSize(int width, int height) {
        dimension = new Dimension(width, height);
        webDriver.manage().window().setSize(dimension);
    }

    /**
     * Switches to a child window.
     *
     * @param windows A set of window handles
     */
    public static void switchToChildWindow(Set<String> windows) {
        Iterator<String> iterator = windows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();
        webDriver.switchTo().window(childId);
    }

    /**
     * Switches to a parent window.
     *
     * @param windows A set of window handles
     */
    public static void switchToParentWindow(Set<String> windows) {
        Iterator<String> iterator = windows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();
        webDriver.switchTo().window(parentId);
    }

    /**
     * Switches to the current window.
     *
     * @param windows A set of window handles
     */
    public static void switchToCurrentWindow(Set<String> windows) {
        Iterator<String> iterator = windows.iterator();
        String windowId = iterator.next();
        webDriver.switchTo().window(windowId);
    }

    /**
     * Switches to the last window.
     *
     * @param windows A set of window handles
     */
    public static void switchToLastWindow(Set<String> windows) {
        String windowId = null;
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()) {
            windowId = iterator.next();
        }
        webDriver.switchTo().window(windowId);
    }

    /**
     * Switches to the nth child window.
     *
     * @param windows A set of window handles
     * @param n       The index of the child window to switch to
     */
    public static void userSwitchToTheNthChildWindow(Set<String> windows, int n) {
        // Exclude parent window
        String windowId = null;
        int i = 0;
        Iterator<String> iterator = windows.iterator();
        if (iterator.hasNext()) {//Coming to parent window
            iterator.next();
        }
        while (i < n && iterator.hasNext()) {
            windowId = iterator.next();

        }
        webDriver.switchTo().window(windowId);
    }

    /**
     * Opens a new tab.
     */
    public static void openNewTab() {
        setOriginalWindow();
        webDriver.switchTo().newWindow(WindowType.TAB);
    }

    /**
     * Opens a new window.
     */
    public static void openNewWindow() {
        setOriginalWindow();
        webDriver.switchTo().newWindow(WindowType.WINDOW);
    }

    /**
     * Opens a new tab and then closes it.
     */
    public static void openNewTabAncClose() {
        // Open a new tab
        Actions actions = new Actions(webDriver);
        actions.keyDown(Keys.CONTROL).sendKeys("T").keyUp(Keys.CONTROL).perform();

        // Switch to the new tab
        String newWindowHandle = webDriver.getWindowHandles().iterator().next();
        webDriver.switchTo().window(newWindowHandle);

        // Do something on the new tab

        // Close the new tab
        webDriver.close();

        // Switch back to the old tab
        String oldWindowHandle = webDriver.getWindowHandles().iterator().next();
        webDriver.switchTo().window(oldWindowHandle);
    }
}
