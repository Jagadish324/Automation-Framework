package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBox extends CommonHelper{
    private static Logger log= LogManager.getLogger(CheckBox.class.getName());
    /**
     * Selects a checkbox identified by the given locator.
     * @param locator The locator of the checkbox to select.
     */
    public static void selectCheckBox(By locator) {
        AutoWait.autoWait(locator);
        selectCheckBox(webDriver.findElement(locator));
    }
    /**
     * Unchecks a checkbox identified by the given locator.
     * @param locator The locator of the checkbox to uncheck.
     */
    public static void unCheckCheckBox(By locator) {
        AutoWait.autoWait(locator);
        unCheckCheckBox(webDriver.findElement(locator));
    }
    /**
     * Checks if a checkbox identified by the given locator is selected.
     * @param locator The locator of the checkbox to check.
     * @return true if the checkbox is selected, false otherwise.
     */
    public static boolean isSelected(By locator) {
        AutoWait.autoWait(locator);
        return isSelected(webDriver.findElement(locator));
    }
    /**
     * Checks if the given checkbox WebElement is selected.
     * @param locator The WebElement representing the checkbox.
     * @return true if the checkbox is selected, false otherwise.
     */
    public static boolean isSelected(WebElement locator) {
        AutoWait.autoWait(locator);
        beforePerformingAction();
        boolean flag = locator.isSelected();
        afterPerformingAction();
        return flag;
    }

    /**
     * Selects a checkbox represented by the given WebElement if it is not already selected.
     * @param locator The WebElement representing the checkbox to select.
     */
    public static void selectCheckBox(WebElement locator) {
        AutoWait.autoWait(locator);
        beforePerformingAction();
        if (!isSelected(locator))
            locator.click();
        afterPerformingAction();
    }
    /**
     * Unchecks a checkbox represented by the given WebElement if it is selected.
     * @param locator The WebElement representing the checkbox to uncheck.
     */
    public static void unCheckCheckBox(WebElement locator) {
        AutoWait.autoWait(locator);
        beforePerformingAction();
        if (isSelected(locator))
            locator.click();
        afterPerformingAction();
    }
    /**
     * Gets the value of a checkbox attribute for the given locator.
     * @param locator The locator of the checkbox element.
     * @param attributeName The name of the attribute to retrieve.
     * @return The value of the checkbox attribute.
     */
    public static boolean getCheckBoxAttribute(By locator, String attributeName) {
        AutoWait.autoWait(locator);
       return Boolean.parseBoolean(getElement(locator).getAttribute(attributeName));
    }
}
