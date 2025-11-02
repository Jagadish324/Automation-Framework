package com.arc.frameworkDesktop.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBox extends CommonHelper{

    /**
     * Checks if a checkbox identified by the given locator is selected.
     * @param locator The locator of the checkbox to check.
     * @return true if the checkbox is selected, false otherwise.
     */
    public static boolean isSelected(By locator) {
        return getElement(locator).isSelected();
    }

    /**
     * Selects a checkbox identified by the given locator.
     * @param locator The locator of the checkbox to select.
     */
    public static void selectCheckBox(By locator) {
        if(!isSelected(locator)) {
            Button.singleTap(locator);
        }
        if (!isSelected(locator)) {
            throw new RuntimeException("Checkbox at " + locator.toString() + " could not be selected.");
        }
    }

    /**
     * Unchecks a checkbox identified by the given locator.
     * @param locator The locator of the checkbox to uncheck.
     */
    public static void deselectCheckBox(By locator) {
        if (isSelected(locator)) {
            Button.singleTap(locator);
        }
        if (isSelected(locator)) {
            throw new RuntimeException("Checkbox at " + locator.toString() + " could not be deselected.");
        }
    }

    /**
     * Checks if the given checkbox WebElement is selected.
     * @param locator The WebElement representing the checkbox.
     * @return true if the checkbox is selected, false otherwise.
     */
    public static boolean isSelected(WebElement locator) {
        return locator.isSelected();
    }

    /**
     * Selects a checkbox represented by the given WebElement if it is not already selected.
     * @param locator The WebElement representing the checkbox to select.
     */
    public static void selectCheckBox(WebElement locator) {
        if(!isSelected(locator)) {
            Button.singleTap(locator);
        }
        if (!isSelected(locator)) {
            throw new RuntimeException("Checkbox at " + locator.toString() + " could not be selected.");
        }
    }

    /**
     * Unchecks a checkbox represented by the given WebElement if it is selected.
     * @param locator The WebElement representing the checkbox to uncheck.
     */
    public static void deselectCheckBox(WebElement locator) {
        if (isSelected(locator)) {
            Button.singleTap(locator);
        }
        if (isSelected(locator)) {
            throw new RuntimeException("Checkbox at " + locator.toString() + " could not be deselected.");
        }
    }
}
