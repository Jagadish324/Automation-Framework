package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.context.DriverManager;
import com.arc.frameworkWeb.context.PlaywrightManager;
import com.arc.frameworkWeb.utility.CONSTANT;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Window extends CommonHelper {
    private static Logger log = LogManager.getLogger(Window.class.getName());

    private static Dimension dimension;

    /**
     * Returns the current window handle.
     *
     * @return The current window handle (or URL for Playwright)
     */
    public static String getWindow() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getDriver().getWindowHandle();
        } else {
            return getPageInstance().url();
        }
    }

    /**
     * Closes all other tabs except the original tab.
     */
    public static void closeAllOtherTab() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            setOriginalWindow();
            for (String handle : getWindows()) {
                if (!handle.equals(CONSTANT.ORIGINAL_WINDOW)) {
                    getDriver().switchTo().window(handle);
                    getDriver().close();
                }
            }
            getDriver().switchTo().window(CONSTANT.ORIGINAL_WINDOW);
        } else {
            Page currentPage = getPageInstance();
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            for (Page p : pages) {
                if (p != currentPage) {
                    p.close();
                }
            }
        }
    }

    /**
     * Sets the original window handle.
     */
    public static void setOriginalWindow() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            CONSTANT.ORIGINAL_WINDOW = getDriver().getWindowHandle();
        } else {
            CONSTANT.ORIGINAL_WINDOW = getPageInstance().url();
        }
    }

    /**
     * Returns a set of window handles.
     *
     * @return A set of window handles (null for Playwright)
     */
    public static Set<String> getWindows() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getDriver().getWindowHandles();
        } else {
            log.warn("getWindows() is not directly applicable for Playwright. Use PlaywrightManager.getBrowserContext().pages() instead.");
            return null;
        }
    }

    /**
     * Prints a message if no other window is present.
     */
    public static void isOtherWindowPresent() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            if (getWindows().size() == 1)
                System.out.println("No window present");
        } else {
            if (PlaywrightManager.getBrowserContext().pages().size() == 1)
                System.out.println("No window present");
        }
    }

    /**
     * Waits until a new window is open.
     */
    public static void waitUntilWindowOpen() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            ExplicitWait.waitUntilWindowOpen();
        } else {
            ExplicitWait.waitUntilWindowOpen();
        }
    }

    /**
     * Switches to a new tab.
     */
    public static void switchTab() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().switchTo().newWindow(WindowType.TAB);
        } else {
            Page newPage = PlaywrightManager.getBrowserContext().newPage();
            DriverManager.setPage(newPage);
        }
    }

    /**
     * Switches to a new window.
     */
    public static void switchWindow() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            setOriginalWindow();
            AutoWait.autoWaitWindowOpen();
            Set<String> windows = getWindows();
            Iterator<String> iterator = windows.iterator();
            while (iterator.hasNext()) {
                String childWindow = iterator.next();
                if (!CONSTANT.ORIGINAL_WINDOW.equals(childWindow)) {
                    getDriver().switchTo().window(childWindow);
                    break;
                }
            }
        } else {
            Page currentPage = getPageInstance();
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            for (Page p : pages) {
                if (p != currentPage) {
                    DriverManager.setPage(p);
                    break;
                }
            }
        }
    }

    /**
     * Switches to the original window.
     */
    public static void switchToOriginalWindow() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().switchTo().window(CONSTANT.ORIGINAL_WINDOW);
        } else {
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            for (Page p : pages) {
                if (p.url().equals(CONSTANT.ORIGINAL_WINDOW)) {
                    DriverManager.setPage(p);
                    break;
                }
            }
        }
    }

    /**
     * Returns the width of the window.
     *
     * @return The width of the window
     */
    public static int getWindowWidth(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            dimension = getDriver().manage().window().getSize();
            return dimension.getWidth();
        } else {
            return getPageInstance().viewportSize().width;
        }
    }

    /**
     * Returns the height of the window.
     *
     * @return The height of the window
     */
    public static int getWindowHeight(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            dimension = getDriver().manage().window().getSize();
            return dimension.getHeight();
        } else {
            return getPageInstance().viewportSize().height;
        }
    }

    /**
     * Sets the size of the window.
     *
     * @param width  The width of the window
     * @param height The height of the window
     */
    public static void setWindowsSize(int width, int height) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            dimension = new Dimension(width, height);
            getDriver().manage().window().setSize(dimension);
        } else {
            getPageInstance().setViewportSize(width, height);
        }
    }

    /**
     * Switches to a child window.
     *
     * @param windows A set of window handles
     */
    public static void switchToChildWindow(Set<String> windows) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Iterator<String> iterator = windows.iterator();
            String parentId = iterator.next();
            String childId = iterator.next();
            getDriver().switchTo().window(childId);
        } else {
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            if (pages.size() > 1) {
                DriverManager.setPage(pages.get(1));
            }
        }
    }

    /**
     * Switches to a parent window.
     *
     * @param windows A set of window handles
     */
    public static void switchToParentWindow(Set<String> windows) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Iterator<String> iterator = windows.iterator();
            String parentId = iterator.next();
            String childId = iterator.next();
            getDriver().switchTo().window(parentId);
        } else {
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            if (!pages.isEmpty()) {
                DriverManager.setPage(pages.get(0));
            }
        }
    }

    /**
     * Switches to the current window.
     *
     * @param windows A set of window handles
     */
    public static void switchToCurrentWindow(Set<String> windows) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Iterator<String> iterator = windows.iterator();
            String windowId = iterator.next();
            getDriver().switchTo().window(windowId);
        } else {
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            if (!pages.isEmpty()) {
                DriverManager.setPage(pages.get(0));
            }
        }
    }

    /**
     * Switches to the last window.
     *
     * @param windows A set of window handles
     */
    public static void switchToLastWindow(Set<String> windows) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            String windowId = null;
            Iterator<String> iterator = windows.iterator();
            while (iterator.hasNext()) {
                windowId = iterator.next();
            }
            getDriver().switchTo().window(windowId);
        } else {
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            if (!pages.isEmpty()) {
                DriverManager.setPage(pages.get(pages.size() - 1));
            }
        }
    }

    /**
     * Switches to the nth child window.
     *
     * @param windows A set of window handles
     * @param n       The index of the child window to switch to
     */
    public static void userSwitchToTheNthChildWindow(Set<String> windows, int n) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            // Exclude parent window
            String windowId = null;
            int i = 0;
            Iterator<String> iterator = windows.iterator();
            if (iterator.hasNext()) { // Coming to parent window
                iterator.next();
            }
            while (i < n && iterator.hasNext()) {
                windowId = iterator.next();
            }
            getDriver().switchTo().window(windowId);
        } else {
            List<Page> pages = PlaywrightManager.getBrowserContext().pages();
            // Index 0 is the parent, so nth child is at index n
            if (pages.size() > n) {
                DriverManager.setPage(pages.get(n));
            }
        }
    }

    /**
     * Opens a new tab.
     */
    public static void openNewTab() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            setOriginalWindow();
            getDriver().switchTo().newWindow(WindowType.TAB);
        } else {
            setOriginalWindow();
            Page newPage = PlaywrightManager.getBrowserContext().newPage();
            DriverManager.setPage(newPage);
        }
    }

    /**
     * Opens a new window.
     */
    public static void openNewWindow() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            setOriginalWindow();
            getDriver().switchTo().newWindow(WindowType.WINDOW);
        } else {
            setOriginalWindow();
            Page newPage = PlaywrightManager.getBrowserContext().newPage();
            DriverManager.setPage(newPage);
        }
    }

    /**
     * Opens a new tab and then closes it.
     */
    public static void openNewTabAncClose() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            // Open a new tab
            Actions actions = new Actions(getDriver());
            actions.keyDown(Keys.CONTROL).sendKeys("T").keyUp(Keys.CONTROL).perform();

            // Switch to the new tab
            String newWindowHandle = getDriver().getWindowHandles().iterator().next();
            getDriver().switchTo().window(newWindowHandle);

            // Do something on the new tab

            // Close the new tab
            getDriver().close();

            // Switch back to the old tab
            String oldWindowHandle = getDriver().getWindowHandles().iterator().next();
            getDriver().switchTo().window(oldWindowHandle);
        } else {
            Page currentPage = getPageInstance();
            Page newPage = PlaywrightManager.getBrowserContext().newPage();
            newPage.close();
            DriverManager.setPage(currentPage);
        }
    }
}
