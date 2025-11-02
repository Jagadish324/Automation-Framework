package com.arc.frameworkDevice.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for retrieving information about elements on the screen.
 */
public class ElementInfo extends CommonHelper{
    private static Logger log = LogManager.getLogger(ElementInfo.class.getName());
    /**
     * Gets the x-position of the element identified by the given locator.
     *
     * @param element The locator of the element.
     * @return The x-position of the element.
     */
    public static int getXPosition(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().x + (getElement(element).getSize().getWidth()) - 2;
    }
    /**
     * Gets the x-location of the element identified by the given locator.
     *
     * @param element The locator of the element.
     * @return The x-location of the element.
     */
    public static int getXLocation(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().x+5;
    }
    /**
     * Gets the y-position of the element identified by the given locator.
     *
     * @param element The locator of the element.
     * @return The y-position of the element.
     */
    public static int getYPosition(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().y + (getElement(element).getSize().getWidth()) - 2;
    }
    /**
     * Gets the y-location of the element identified by the given locator.
     *
     * @param element The locator of the element.
     * @return The y-location of the element.
     */
    public static int getYLocation(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().y+15;
    }
    /**
     * Gets the x-coordinate of the element identified by the given locator.
     *
     * @param element The locator of the element.
     * @return The x-coordinate of the element.
     */

    public static int getXCoordinat(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().getX() ;
    }
    /**
     * Gets the y-coordinate of the element identified by the given locator.
     *
     * @param element The locator of the element.
     * @return The y-coordinate of the element.
     */
    public static int getYCoordinate(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().getX()+2;
    }
    /**
     * Checks if the element identified by the given locator is present.
     *
     * @param element The locator of the element.
     * @return True if the element is present, false otherwise.
     */
    public static boolean isElementPresent(By element){
        AutoWait.elementWait(element);
        try{
            return getElement(element).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }

    }
    /**
     * Gets the attribute value of the element identified by the given locator.
     *
     * @param element      The locator of the element.
     * @param attributeName The name of the attribute.
     * @return The value of the attribute.
     */
    public static String getAttributeValue(By element,String attributeName){
        AutoWait.elementWait(element);
        try {
            return getElement(element).getAttribute(attributeName);
        }catch (StaleElementReferenceException e){
            ElementWait.hardWait(2000);
            return getElement(element).getAttribute(attributeName);
        }
    }
    /**
     * Gets the text of the element identified by the given locator.
     *
     * @param element The locator of the element.
     * @return The text of the element.
     */
    public static String getText(By element) {
        AutoWait.elementWait(element);
        return getElement(element).getText();
    }
    /**
     * Gets the text of the given web element.
     *
     * @param element The web element.
     * @return The text of the web element.
     */
    public static String getText(WebElement element) {
        AutoWait.elementWait(element);
        return element.getText();
    }

    public static List<String> getText(List<WebElement> elements){
        List<String> text = new ArrayList<>();
        for(WebElement element:elements){
            text.add(element.getText());
        }
        return text;
    }
    /**
     * Gets the attribute value of the given web element.
     *
     * @param element      The web element.
     * @param attributeName The name of the attribute.
     * @return The value of the attribute.
     */
    public static String getAttributeValue(WebElement element, String attributeName) {
        AutoWait.elementWait(element);
        return element.getAttribute(attributeName);
    }
    /**
     * Gets the attribute value of the element identified by the given locator.
     *
     * @param element      The locator of the element.
     * @param attributeName The name of the attribute.
     * @return The value of the attribute.
     */
    public static String getAttribute(By element,String attributeName){
        try {
            return getElement(element).getAttribute(attributeName);
        }catch (StaleElementReferenceException e){
            ElementWait.hardWait(2000);
            return getElement(element).getAttribute(attributeName);
        }
    }

    public static List<String> getAttributes(List<WebElement> elements,String attributes){
        List<String> attribute = new ArrayList<>();
        for(WebElement element:elements){
            attribute.add(element.getAttribute(attributes));
        }
        return attribute;
    }
    /**
     * Gets the attribute value of the given web element.
     *
     * @param element      The web element.
     * @param attributeName The name of the attribute.
     * @return The value of the attribute.
     */
    public static String getAttribute(WebElement element, String attributeName) {
        try {
            return element.getAttribute(attributeName);
        }catch (StaleElementReferenceException e){
            ElementWait.hardWait(2000);
            return element.getAttribute(attributeName);
        }
    }
    /**
     * Gets the x-position of the given web element.
     *
     * @param element The web element.
     * @return The x-position of the web element.
     */
    public static int getXPosition(WebElement element) {
        return element.getLocation().x + (element.getSize().getWidth()) - 2;
    }

    /**
     * Gets the y-position of the given web element.
     *
     * @param element The web element.
     * @return The y-position of the web element.
     */
    public static int getYPosition(WebElement element) {
        return element.getLocation().y + (element.getSize().getWidth()) - 2;
    }

}
