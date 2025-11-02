package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class Button extends CommonHelper {
    private static Logger log = LogManager.getLogger(Button.class.getName());
    /**
     * Clicks on an element identified by the given locator.
     * If using Selenium, handles ElementClickInterceptedException by retrying the click.
     * If using Playwright, clicks on the element identified by the given locator.
     * @param locator The locator of the element to click.
     */
    public static void click(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitButton(locator);
            beforePerformingAction();
            try {
                getElement(locator).click();
                log.info("Element clicked successfully in first attempt to locator " + locator);
            } catch (ElementClickInterceptedException e) {
                try {
                getElement(locator).click();
                log.info("Element clicked retry success to locator ElementClickInterceptedException" + locator);
                }
                catch (Exception f) {
                    JavaScriptExecutor.forceClickJSE(locator);
                    log.info("Clicked with JavaScriptExecutor.forceClickJSE {}", locator);
                }
            }
//            catch (StaleElementReferenceException e) {
//                log.info("Stale element exception---In Catch Block---- StaleElementReferenceException");
//                ExplicitWait.hardWait(3);
//                getElement(locator).click();
//            }
            afterPerformingAction();
        } else {
            page.locator(getLocator("" + locator)).first().click();
        }
    }
    /**
     * Clicks on an element identified by the given locator without waiting for auto-wait.
     * If using Selenium, handles ElementClickInterceptedException by retrying the click.
     * If using Playwright, clicks on the element identified by the given locator without waiting for auto-wait.
     * @param locator The locator of the element to click.
     */
    public static void clickWithoutAutoWait(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
//        AutoWait.autoWaitButton(locator);
            beforePerformingAction();
            try {
                getElement(locator).click();
                log.info("Element clicked successfully in first attempt");
            } catch (ElementClickInterceptedException e) {
                log.info("---In Catch Block----");
//                try {
                getElement(locator).click();
                log.info("Element clicked successfully");
//                } catch (ElementClickInterceptedException f) {
//                    JavaScriptExecutor.mouseClickJSE(locator);
//                    log.error("Element clicked successfully");
//                }
            }
//            catch (StaleElementReferenceException e) {
//                log.info("Stale element exception---In Catch Block----");
//                ExplicitWait.hardWait(3);
//                getElement(locator).click();
//            }
            afterPerformingAction();
        } else {
            page.locator(getLocator("" + locator)).first().click();
        }
    }
    /**
     * Clicks on the given WebElement.
     * If using Selenium, handles ElementClickInterceptedException by retrying the click.
     * If using Playwright, clicks on the given WebElement.
     * @param locator The WebElement to click.
     */
    public static void click(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitButton(locator);
            beforePerformingAction();
            try {
                locator.click();
            } catch (ElementClickInterceptedException e) {
//                try {
                locator.click();
//                } catch (ElementClickInterceptedException f) {
//                    JavaScriptExecutor.mouseClickJSE(locator);
//                }
            }
//            catch (StaleElementReferenceException e) {
//                log.info("Stale element exception---In Catch Block----");
//                ExplicitWait.hardWait(3);
//                locator.click();
//            }
            afterPerformingAction();
        } else {
            page.locator(getLocator("" + locator)).first().click();
        }
    }
    /**
     * Double clicks on an element identified by the given locator.
     * If using Selenium, double clicks on the element identified by the given locator.
     * If using Playwright, double clicks on the element identified by the given locator.
     * @param locator The locator of the element to double click.
     */
    public static void doubleClick(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitButton(locator);
            beforePerformingAction();
            getElement(locator).click();
            getElement(locator).click();
            afterPerformingAction();
        } else {
            page.locator(getLocator("" + locator)).first().click();
            page.locator(getLocator("" + locator)).first().click();
        }
    }
    /**
     * Double clicks on the given WebElement.
     * If using Selenium, double clicks on the given WebElement.
     * If using Playwright, double clicks on the given WebElement.
     * @param locator The WebElement to double click.
     */
    public static void doubleClick(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitButton(locator);
            beforePerformingAction();
            locator.click();
            locator.click();
            afterPerformingAction();
        } else {
            page.locator(getLocator("" + locator)).first().click();
            page.locator(getLocator("" + locator)).first().click();
        }
    }
    /**
     * Checks if a button identified by the given locator is displayed.
     * If using Selenium, checks if the button identified by the given locator is displayed.
     * If using Playwright, checks if the button identified by the given locator is displayed.
     * @param locator The locator of the button to check.
     * @return true if the button is displayed, false otherwise.
     */
    public static boolean isButtonDisplayed(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            try {
                return ElementInfo.isDisplayed(locator);
            } catch (Exception e) {
                throw e;
            }
        } else {
            boolean flag = page.locator(getLocator("" + locator)).first().isVisible();
            if (!flag) {
                ExplicitWait.hardWait(500);
                flag = page.locator(getLocator("" + locator)).first().isVisible();
            }
            return flag;
        }
    }
    /**
     * Gets the text of an element identified by the given locator.
     * If using Selenium, gets the text of the element identified by the given locator.
     * If using Playwright, gets the text of the element identified by the given locator.
     * @param locator The locator of the element to get text from.
     * @return The text of the element.
     */
    public static String getText(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitButton(locator);
            return getElement(locator).getText();
        } else {
            return page.locator(getLocator("" + locator)).first().textContent();
        }
    }
    /**
     * Gets the text of the given WebElement.
     * If using Selenium, gets the text of the given WebElement.
     * If using Playwright, gets the text of the given WebElement.
     * @param locator The WebElement to get text from.
     * @return The text of the WebElement.
     */
    public static String getText(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitButton(locator);
            return locator.getText();
        } else {
            return page.locator(getLocator("" + locator)).first().textContent();
        }
    }
}
