package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.context.PlaywrightManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.arc.frameworkWeb.utility.CONSTANT;

public class AutoWait extends CommonHelper {
    private static Logger log= LogManager.getLogger(AutoWait.class.getName());
    public static void autoWait(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            waitForStability(locator);
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().waitFor(
                    new com.microsoft.playwright.Locator.WaitForOptions()
                            .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        }
    }

    public static void autoWait(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            waitForStability(locator);
        } else {
            log.info("Playwright has built-in auto-waiting; WebElement-based autoWait is not applicable.");
        }
    }

    public static void autoWaitButton(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            waitForStabilityAndClickable(locator);
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().waitFor(
                    new com.microsoft.playwright.Locator.WaitForOptions()
                            .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        }
    }

    public static void autoWaitButton(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            waitForStabilityAndClickable(locator);
        } else {
            log.info("Playwright has built-in auto-waiting; WebElement-based autoWaitButton is not applicable.");
        }
    }

    public static void autoWaitWindowOpen() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            try {
                for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                    ExplicitWait.waitUntilWindowOpen();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                long timeout = CONSTANT.EXPLICIT_WAIT * 1000L;
                long start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < timeout) {
                    if (PlaywrightManager.getBrowserContext().pages().size() > 1) {
                        return;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
                log.info("Timed out waiting for a new window/tab to open in Playwright.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void autoWaitAlert() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            try {
                boolean flag = false;
                for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                    if (flag == false) {
                        flag = Alerts.isAlertPresent();
                        ExplicitWait.hardWait(1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info("Playwright handles dialogs via page.onDialog(); autoWaitAlert is not directly applicable.");
        }
    }

    public static boolean waitForStability(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            boolean flag = false;
            boolean flagVisibility = false;

            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForVisibility(locator, CONSTANT.AUTOWAIT_TIME);
                    flagVisibility = true;
                    log.info("Element visible for " + locator + " retry " + i + " flagVisibility");
                    break;
                } catch (Exception e) {
                    log.info("Element visible for " + locator + " retry " + i);
                }
            }

            if (flagVisibility) {
                try {
                    flag = getElement(locator).isDisplayed();
                    log.info("Element displayed for " + locator);
                } catch (Exception e) {
                    log.info("Element not displayed for " + locator);
                }
            }
            if (flag) {
                JavaScriptExecutor.scrollIntoView(locator);
            }
            return flag;
        } else {
            try {
                getPageInstance().locator(getLocator("" + locator)).first().waitFor(
                        new com.microsoft.playwright.Locator.WaitForOptions()
                                .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
                return true;
            } catch (Exception e) {
                log.info("Element not visible for " + locator + " in Playwright");
                return false;
            }
        }
    }

    public static boolean waitForStability(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            boolean flag = false;
            boolean flagVisibility = false;
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForVisibility(locator, CONSTANT.AUTOWAIT_TIME);
                    flagVisibility = true;
                    log.info("Element Presence for " + locator + " is- " + flagVisibility);
                    break;
                } catch (Exception e) {
                    log.info("Element Presence for " + locator + " retry is " + i);
                }
            }
            if (flagVisibility) {
                try {
                    flag = locator.isDisplayed();
                    log.info("Element displayed for " + locator);
                } catch (Exception e) {
                    log.info("Element not displayed for " + locator);
                }
            }
            if (flag) {
                JavaScriptExecutor.scrollIntoView(locator);
            }
            return flag;
        } else {
            log.info("Playwright has built-in auto-waiting; WebElement-based waitForStability is not applicable.");
            return true;
        }
    }

    public static void waitForStabilityAndClickable(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            boolean flag = waitForStability(locator);
            if (flag) {
                for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                    try {
                        ExplicitWait.waitForElementsToBeClickable(locator, CONSTANT.AUTOWAIT_TIME);
                        log.info("Element clickable for " + locator);
                        break;
                    } catch (Exception e) {
                        log.info("Element clickable retry for " + locator + i);
                    }
                }
            }
        } else {
            getPageInstance().locator(getLocator("" + locator)).first().waitFor(
                    new com.microsoft.playwright.Locator.WaitForOptions()
                            .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        }
    }

    public static void waitForStabilityAndClickable(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            boolean flag = waitForStability(locator);
            if (flag) {
                for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                    try {
                        ExplicitWait.waitForElementsToBeClickable(locator, CONSTANT.AUTOWAIT_TIME);
                        log.info("Element clickable for " + locator);
                        break;
                    } catch (Exception e) {
                        log.info("Element clickable retry for " + locator + i);
                    }
                }
            }
        } else {
            log.info("Playwright has built-in auto-waiting; WebElement-based waitForStabilityAndClickable is not applicable.");
        }
    }
}
