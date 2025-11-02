package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends CommonHelper {
    private static Logger log= LogManager.getLogger(DropDown.class.getName());
    /**
     * Clicks on a dropdown element identified by the provided locator.
     * @param locator The locator for the dropdown element.
     */
    public static void clickOnDropDown(By locator){
        AutoWait.autoWait(locator);
        beforePerformingAction();
        getElement(locator).click();
        afterPerformingAction();
    }
    /**
     * Selects an option in a dropdown element by visible text.
     * @param selectTag The locator for the dropdown element.
     * @param value The visible text of the option to select.
     */
    public static void selectClassDropDownByVisibleTxt(By selectTag,String value){
        AutoWait.autoWait(selectTag);
        beforePerformingAction();
        Select select = new Select(getElement(selectTag));
        select.selectByVisibleText(value);
        afterPerformingAction();
    }
    /**
     * Selects an option in a dropdown element by value attribute.
     * @param selectTag The locator for the dropdown element.
     * @param value The value attribute of the option to select.
     */
    public static void selectClassDropDownByValue(By selectTag,String value){
        AutoWait.autoWait(selectTag);
        beforePerformingAction();
        Select select = new Select(getElement(selectTag));
        select.selectByValue(value);
        afterPerformingAction();
    }
    /**
     * Clicks on dropdown elements identified by the provided locator values.
     * @param locatorValue The locator value for the dropdown element.
     * @param value The value to select in the dropdown.
     */
    public static void selectDropDown(String locatorValue, String value){
        By locator = LocatorGenerator.dynamicLocator(locatorValue);
        ExplicitWait.waitForElementsToBeClickable(locator);
        try {
            getElement(locator).click();
        }
        catch (ElementClickInterceptedException e) {
            JavaScriptExecutor.forceClickJSE(locator);
        }

        locator = LocatorGenerator.dynamicLocator(value);
        try {
            getElement(locator).click();
        }
        catch (ElementClickInterceptedException e) {
            JavaScriptExecutor.forceClickJSE(locator);
        }
    }
    /**
     * Selects an option in a dropdown element identified by the provided locator.
     * @param locator The locator for the dropdown element.
     * @param value The value to select in the dropdown.
     */
    public static void selectDropDown(By locator, String value) {
        ExplicitWait.waitForElementsToBeClickable(locator);
        try {
            getElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            JavaScriptExecutor.forceClickJSE(locator);
        }
        locator = LocatorGenerator.dynamicLocator(value);
        try {
            getElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            JavaScriptExecutor.forceClickJSE(locator);
        }
    }
    /**
     * Gets the visible text of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The visible text of the element.
     */
    public static String getText(By locator) {
            return getElement(locator).getText();
    }
    /**
     * Gets the visible text of the element.
     * @param locator The element for which to retrieve the text.
     * @return The visible text of the element.
     */
    public static String getText(WebElement locator) {
        return locator.getText();
    }

}
