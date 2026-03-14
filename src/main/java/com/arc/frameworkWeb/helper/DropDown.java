package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import com.microsoft.playwright.options.SelectOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends CommonHelper {
    private static Logger log = LogManager.getLogger(DropDown.class.getName());

    /**
     * Clicks on a dropdown element identified by the provided locator.
     * @param locator The locator for the dropdown element.
     */
    public static void clickOnDropDown(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            beforePerformingAction();
            getElement(locator).click();
            afterPerformingAction();
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().click();
        }
    }

    /**
     * Selects an option in a dropdown element by visible text.
     * @param selectTag The locator for the dropdown element.
     * @param value The visible text of the option to select.
     */
    public static void selectClassDropDownByVisibleTxt(By selectTag, String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(selectTag);
            beforePerformingAction();
            Select select = new Select(getElement(selectTag));
            select.selectByVisibleText(value);
            afterPerformingAction();
        } else {
            getPageInstance().locator(getLocator("" + selectTag)).first()
                    .selectOption(new SelectOption().setLabel(value));
        }
    }

    /**
     * Selects an option in a dropdown element by value attribute.
     * @param selectTag The locator for the dropdown element.
     * @param value The value attribute of the option to select.
     */
    public static void selectClassDropDownByValue(By selectTag, String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(selectTag);
            beforePerformingAction();
            Select select = new Select(getElement(selectTag));
            select.selectByValue(value);
            afterPerformingAction();
        } else {
            getPageInstance().locator(getLocator("" + selectTag)).first()
                    .selectOption(new SelectOption().setValue(value));
        }
    }

    /**
     * Clicks on dropdown elements identified by the provided locator values.
     * @param locatorValue The locator value for the dropdown element.
     * @param value The value to select in the dropdown.
     */
    public static void selectDropDown(String locatorValue, String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            By locator = LocatorGenerator.dynamicLocator(locatorValue);
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
        } else {
            String dropdownXpath = getLocator("" + LocatorGenerator.dynamicLocator(locatorValue));
            getPageInstance().locator(dropdownXpath).first().click();
            String optionXpath = getLocator("" + LocatorGenerator.dynamicLocator(value));
            getPageInstance().locator(optionXpath).first().click();
        }
    }

    /**
     * Selects an option in a dropdown element identified by the provided locator.
     * @param locator The locator for the dropdown element.
     * @param value The value to select in the dropdown.
     */
    public static void selectDropDown(By locator, String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
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
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().click();
            String optionXpath = getLocator("" + LocatorGenerator.dynamicLocator(value));
            getPageInstance().locator(optionXpath).first().click();
        }
    }

    /**
     * Gets the visible text of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The visible text of the element.
     */
    public static String getText(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getElement(locator).getText();
        } else {
            return getPageInstance().locator(getLocator("" + locator)).first().inputValue();
        }
    }

    /**
     * Gets the visible text of the element.
     * @param locator The element for which to retrieve the text.
     * @return The visible text of the element.
     */
    public static String getText(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return locator.getText();
        } else {
            log.warn("getText(WebElement) called in Playwright mode. WebElement is not applicable. Use getText(By) instead.");
            return null;
        }
    }
}
