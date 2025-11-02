package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Link extends CommonHelper{
    private static Logger log= LogManager.getLogger(Link.class.getName());
    /**
     * Clicks the element located by the specified locator.
     * @param locator The locator of the element to click.
     */
    public static void click(By locator){
        AutoWait.autoWaitButton(locator);
        getElement(locator).click();
    }
    /**
     * Clicks the specified web element.
     * @param locator The web element to click.
     */
    public static void click(WebElement locator){
        AutoWait.autoWaitButton(locator);
        locator.click();
    }
    /**
     * Checks if a link is displayed using the specified locator.
     * @param locator The locator of the link to check.
     * @return true if the link is displayed, false otherwise.
     */
    public static boolean isLinkDisplayed(By locator)
    {
        try {
            return ElementInfo.isDisplayed(locator);
        }
        catch(Exception e)
        {
            return false;
        }
    }/**
     * Gets the visible text of the element located by the specified locator.
     * @param locator The locator of the element to get text from.
     * @return The visible text of the element.
     */

    public static String getText(By locator) {
        AutoWait.autoWaitButton(locator);
        return getElement(locator).getText();
    }
    /**
     * Gets the visible text of the specified web element.
     * @param locator The web element to get text from.
     * @return The visible text of the element.
     */
    public static String getText(WebElement locator) {
        AutoWait.autoWaitButton(locator);
        return locator.getText();
    }
}
