package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBox extends CommonHelper {
    private static Logger log = LogManager.getLogger(CheckBox.class.getName());

    /**
     * Selects a checkbox identified by the given locator.
     * @param locator The locator of the checkbox to select.
     */
    public static void selectCheckBox(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            selectCheckBox(getDriver().findElement(locator));
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().check();
        }
    }

    /**
     * Unchecks a checkbox identified by the given locator.
     * @param locator The locator of the checkbox to uncheck.
     */
    public static void unCheckCheckBox(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            unCheckCheckBox(getDriver().findElement(locator));
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().uncheck();
        }
    }

    /**
     * Checks if a checkbox identified by the given locator is selected.
     * @param locator The locator of the checkbox to check.
     * @return true if the checkbox is selected, false otherwise.
     */
    public static boolean isSelected(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            return isSelected(getDriver().findElement(locator));
        } else {
            return getPageInstance().locator(getLocator("" + locator)).first().isChecked();
        }
    }

    /**
     * Checks if the given checkbox WebElement is selected.
     * @param locator The WebElement representing the checkbox.
     * @return true if the checkbox is selected, false otherwise.
     */
    public static boolean isSelected(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            beforePerformingAction();
            boolean flag = locator.isSelected();
            afterPerformingAction();
            return flag;
        } else {
            log.warn("isSelected(WebElement) called in Playwright mode. WebElement is not applicable. Use isSelected(By) instead.");
            return false;
        }
    }

    /**
     * Selects a checkbox represented by the given WebElement if it is not already selected.
     * @param locator The WebElement representing the checkbox to select.
     */
    public static void selectCheckBox(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            beforePerformingAction();
            if (!isSelected(locator))
                locator.click();
            afterPerformingAction();
        } else {
            log.warn("selectCheckBox(WebElement) called in Playwright mode. WebElement is not applicable. Use selectCheckBox(By) instead.");
        }
    }

    /**
     * Unchecks a checkbox represented by the given WebElement if it is selected.
     * @param locator The WebElement representing the checkbox to uncheck.
     */
    public static void unCheckCheckBox(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            beforePerformingAction();
            if (isSelected(locator))
                locator.click();
            afterPerformingAction();
        } else {
            log.warn("unCheckCheckBox(WebElement) called in Playwright mode. WebElement is not applicable. Use unCheckCheckBox(By) instead.");
        }
    }

    /**
     * Gets the value of a checkbox attribute for the given locator.
     * @param locator The locator of the checkbox element.
     * @param attributeName The name of the attribute to retrieve.
     * @return The value of the checkbox attribute.
     */
    public static boolean getCheckBoxAttribute(By locator, String attributeName) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            return Boolean.parseBoolean(getElement(locator).getAttribute(attributeName));
        } else {
            return Boolean.parseBoolean(
                    getPageInstance().locator(getLocator("" + locator)).first().getAttribute(attributeName));
        }
    }
}
