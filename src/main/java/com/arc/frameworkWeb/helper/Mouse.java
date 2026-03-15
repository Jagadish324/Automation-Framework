package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.MouseButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Mouse extends CommonHelper {
    private static Logger log = LogManager.getLogger(Mouse.class.getName());

    private static Actions action;

    /**
     * Performs a mouse click on the element located by the given locator.
     * @param locator The locator of the element to click.
     */
    public static void mouseClick(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.moveToElement(getDriver().findElement(locator)).click().build().perform();
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().click();
        }
    }

    /**
     * Performs a mouse hover over the element located by the given locator.
     * @param locator The locator of the element to hover over.
     */
    public static void mouseHover(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.moveToElement(getElement(locator)).build().perform();
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().hover();
        }
    }

    /**
     * Performs a mouse hover over the given web element.
     * @param locator The web element to hover over.
     */
    public static void mouseHover(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.moveToElement(locator).build().perform();
        } else {
            log.warn("mouseHover(WebElement) called in Playwright mode. WebElement is not applicable. Use mouseHover(By) instead.");
        }
    }

    /**
     * Scrolls to the element located by the given locator.
     * @param locator The locator of the element to scroll to.
     */
    public static void scrollToElement(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.scrollToElement(getElement(locator)).perform();
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().scrollIntoViewIfNeeded();
        }
    }

    /**
     * Scrolls to the given web element.
     * @param locator The web element to scroll to.
     */
    public static void scrollToElement(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.scrollToElement(locator).perform();
        } else {
            log.warn("scrollToElement(WebElement) called in Playwright mode. WebElement is not applicable. Use scrollToElement(By) instead.");
        }
    }

    /**
     * Clicks and holds the element located by the given locator.
     * @param locator The locator of the element to click and hold.
     */
    public static void clickAndHold(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.clickAndHold(getElement(locator)).perform();
        } else {
            Locator playwrightLocator = getPageInstance().locator(getLocator("" + locator)).first();
            BoundingBox box = playwrightLocator.boundingBox();
            if (box != null) {
                double x = box.x + box.width / 2;
                double y = box.y + box.height / 2;
                getPageInstance().mouse().move(x, y);
                getPageInstance().mouse().down();
            }
        }
    }

    /**
     * Clicks and releases the element located by the given locator.
     * @param locator The locator of the element to click and release.
     */
    public static void clickAndRelease(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.click(getElement(locator)).perform();
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().click();
        }
    }

    /**
     * Performs a right-click on the element located by the given locator.
     * @param locator The locator of the element to right-click.
     */
    public static void rightClick(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.contextClick(getElement(locator)).perform();
        } else {
            getPageInstance().locator(getLocator("" + locator)).first()
                    .click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        }
    }

    /**
     * Performs a double-click on the element located by the given locator.
     * @param locator The locator of the element to double-click.
     */
    public static void doubleClick(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.doubleClick(getElement(locator)).perform();
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().dblclick();
        }
    }

    /**
     * Performs a double-click on the given web element.
     * @param locator The web element to double-click.
     */
    public static void doubleClick(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.doubleClick(locator).perform();
        } else {
            log.warn("doubleClick(WebElement) called in Playwright mode. WebElement is not applicable. Use doubleClick(By) instead.");
        }
    }

    /**
     * Drags the element located by startPoint and drops it onto the element located by endPoint.
     * @param startPoint The locator of the element to start the drag from.
     * @param endPoint The locator of the element to drop onto.
     */
    public static void dragAndDrop(By startPoint, By endPoint) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.dragAndDrop(getElement(startPoint), getElement(endPoint)).perform();
        } else {
            getPageInstance().locator(getLocator("" + startPoint)).first()
                    .dragTo(getPageInstance().locator(getLocator("" + endPoint)).first());
        }
    }

    /**
     * Performs a control-click action.
     */
    public static void ctrlAndClick() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            action.keyDown(Keys.CONTROL).click().build().perform();
            action.keyUp(Keys.CONTROL).build().perform();
        } else {
            getPageInstance().keyboard().down("Control");
            getPageInstance().mouse().click(0, 0);
            getPageInstance().keyboard().up("Control");
        }
    }

    /**
     * Draws on a canvas located by the given canvas locator.
     * @param canvasLocator The locator of the canvas element.
     */
    public static void drawInCanvas(By canvasLocator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            action = new Actions(getDriver());
            Action draw = action.contextClick(CommonHelper.getElement(canvasLocator))
                    .moveToElement(CommonHelper.getElement(canvasLocator), 50, 100)
                    .clickAndHold(CommonHelper.getElement(canvasLocator)).moveByOffset(50, 110)
                    .release(CommonHelper.getElement(canvasLocator)).build();
            draw.perform();
        } else {
            Locator canvasLoc = getPageInstance().locator(getLocator("" + canvasLocator)).first();
            BoundingBox box = canvasLoc.boundingBox();
            if (box != null) {
                double startX = box.x + box.width / 2;
                double startY = box.y + box.height / 2;
                getPageInstance().mouse().move(startX, startY);
                getPageInstance().mouse().down();
                getPageInstance().mouse().move(startX + 50, startY + 100);
                getPageInstance().mouse().move(startX + 50, startY + 110);
                getPageInstance().mouse().up();
            }
        }
    }
}
