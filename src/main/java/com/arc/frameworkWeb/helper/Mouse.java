package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Mouse extends  CommonHelper{
    private static Logger log= LogManager.getLogger(Mouse.class.getName());

    private static Actions action;
    /**
     * Performs a mouse click on the element located by the given locator.
     * @param locator The locator of the element to click.
     */
    public static void mouseClick(By locator)
    {
        action =new Actions(webDriver);
        action.moveToElement(webDriver.findElement(locator)).click().build().perform();
    }
    /**
     * Performs a mouse hover over the element located by the given locator.
     * @param locator The locator of the element to hover over.
     */
    public static void mouseHover(By locator) {
        action =new Actions(webDriver);
        action.moveToElement(getElement(locator)).build().perform();
    }
    /**
     * Performs a mouse hover over the given web element.
     * @param locator The web element to hover over.
     */
    public static void mouseHover(WebElement locator) {
        action =new Actions(webDriver);
        action.moveToElement(locator).build().perform();
    }
    /**
     * Scrolls to the element located by the given locator.
     * @param locator The locator of the element to scroll to.
     */
    public static void scrollToElement(By locator){
        action =new Actions(webDriver);
        action.scrollToElement(getElement(locator)).perform();
    }
    /**
     * Scrolls to the given web element.
     * @param locator The web element to scroll to.
     */
    public static void scrollToElement(WebElement locator){
        action =new Actions(webDriver);
        action.scrollToElement(locator).perform();
    }
    /**
     * Clicks and holds the element located by the given locator.
     * @param locator The locator of the element to click and hold.
     */
    public static void clickAndHold(By locator){
        action =new Actions(webDriver);
        action.clickAndHold(getElement(locator)).perform();
    }
    /**
     * Clicks and releases the element located by the given locator.
     * @param locator The locator of the element to click and release.
     */
    public static void clickAndRelease(By locator){
        action =new Actions(webDriver);
        action.click(getElement(locator)).perform();
    }
    /**
     * Performs a right-click on the element located by the given locator.
     * @param locator The locator of the element to right-click.
     */
    public static void rightClick(By locator){
        action =new Actions(webDriver);
        action.contextClick(getElement(locator)).perform();
    }
    /**
     * Performs a double-click on the element located by the given locator.
     * @param locator The locator of the element to double-click.
     */
    public static void doubleClick(By locator){
        action =new Actions(webDriver);
        action.doubleClick(getElement(locator)).perform();
    }
    /**
     * Performs a double-click on the given web element.
     * @param locator The web element to double-click.
     */
    public static void doubleClick(WebElement locator){
        action =new Actions(webDriver);
        action.doubleClick(locator).perform();
    }
    /**
     * Drags the element located by startPoint and drops it onto the element located by endPoint.
     * @param startPoint The locator of the element to start the drag from.
     * @param endPoint The locator of the element to drop onto.
     */
    public static void dragAndDrop(By startPoint, By endPoint){
        action =new Actions(webDriver);
        action.dragAndDrop(getElement(startPoint),getElement(endPoint)).perform();
    }
    /**
     * Performs a control-click action.
     */
    public static void ctrlAndClick() {
        action = new Actions(webDriver);
        action.keyDown(Keys.CONTROL).click().build().perform();
        action.keyUp(Keys.CONTROL).build().perform();
    }
    /**
     * Draws on a canvas located by the given canvas locator.
     * @param canvasLocator The locator of the canvas element.
     */
    public static void drawInCanvas(By canvasLocator){
        action = new Actions(webDriver);
        Action draw = action.contextClick(CommonHelper.getElement(canvasLocator))
                .moveToElement(CommonHelper.getElement(canvasLocator),50,100)
                .clickAndHold(CommonHelper.getElement(canvasLocator)).moveByOffset(50,110).release(CommonHelper.getElement(canvasLocator)).build();
        draw.perform();
    }
}
