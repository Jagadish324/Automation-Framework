package com.arc.frameworkDesktop.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class ElementInfo extends CommonHelper {
    private static Logger log = LogManager.getLogger(ElementInfo.class.getName());

    public static int getXPosition(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().x + (getElement(element).getSize().getWidth()) - 2;
    }
    public static int getXLocation(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().x+5;
    }
    public static int getYPosition(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().y + (getElement(element).getSize().getWidth()) - 2;
    }
    public static int getYLocation(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().y+15;
    }
    public static int getXCoordinat(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().getX() ;
    }
    public static int getYCoordinate(By element){
        AutoWait.elementWait(element);
        return getElement(element).getLocation().getX()+2;
    }

    public static boolean isElementPresent(By element){
        AutoWait.elementWait(element);
        try{
            return getElement(element).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }

    }
    public static String getAttributeValue(By element,String attributeName){
        AutoWait.elementWait(element);
        try {
            return getElement(element).getAttribute(attributeName);
        }catch (StaleElementReferenceException e){
            ElementWait.hardWait(2000);
            return getElement(element).getAttribute(attributeName);
        }
    }
    public static String getText(By element) {
        AutoWait.elementWait(element);
        return getElement(element).getText();
    }
    public static String getText(WebElement element) {
        AutoWait.elementWait(element);
        return element.getText();
    }
    public static String getAttributeValue(WebElement element, String attributeName) {
        AutoWait.elementWait(element);
        return element.getAttribute(attributeName);
    }
    public static String getAttribute(By element,String attributeName){
        try {
            return getElement(element).getAttribute(attributeName);
        }catch (StaleElementReferenceException e){
            ElementWait.hardWait(2000);
            return getElement(element).getAttribute(attributeName);
        }
    }
    public static String getAttribute(WebElement element, String attributeName) {
        try {
            return element.getAttribute(attributeName);
        }catch (StaleElementReferenceException e){
            ElementWait.hardWait(2000);
            return element.getAttribute(attributeName);
        }
    }
    public static int getXPosition(WebElement element){
        return element.getLocation().x + (element.getSize().getWidth()) - 2;
    }
    public static int getYPosition(WebElement element){
        return element.getLocation().y + (element.getSize().getWidth()) - 2;
    }

}
