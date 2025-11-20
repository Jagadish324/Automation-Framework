package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import com.arc.frameworkWeb.exception.ElementNotClickableException;
import com.arc.frameworkWeb.exception.ElementNotStableException;
import com.arc.frameworkWeb.utility.CONSTANT;

public class AutoWait extends CommonHelper {
    private static Logger log= LogManager.getLogger(AutoWait.class.getName());
    public static void autoWait(By locator) {
        waitForStability(locator);
    }

    public static void autoWait(WebElement locator) {
        waitForStability(locator);
    }

    public static void autoWaitButton(By locator) {
        waitForStabilityAndClickable(locator);
    }

    public static void autoWaitButton(WebElement locator) {
        waitForStabilityAndClickable(locator);
    }

    public static void autoWaitWindowOpen() {
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            try {
                ExplicitWait.waitUntilWindowOpen();
                log.info("New window opened successfully on attempt {}/{}", i + 1, CONSTANT.STEP_RETRY);
                return;
            } catch (TimeoutException e) {
                log.warn("Window not opened on attempt {}/{}: {}", i + 1, CONSTANT.STEP_RETRY, e.getMessage());
                if (i == CONSTANT.STEP_RETRY - 1) {
                    throw new TimeoutException("New window did not open after " + CONSTANT.STEP_RETRY + " retry attempts", e);
                }
            }
        }
    }

    public static void autoWaitAlert() {
        boolean flag = false;
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            try {
                if (!flag) {
                    flag = Alerts.isAlertPresent();
                    if (flag) {
                        log.info("Alert present on attempt {}/{}", i + 1, CONSTANT.STEP_RETRY);
                        return;
                    }
                    ExplicitWait.hardWait(1000);
                }
            } catch (Exception e) {
                log.warn("Error checking for alert on attempt {}/{}: {}", i + 1, CONSTANT.STEP_RETRY, e.getMessage());
                if (i == CONSTANT.STEP_RETRY - 1) {
                    throw new TimeoutException("Alert did not appear after " + CONSTANT.STEP_RETRY + " retry attempts", e);
                }
            }
        }
    }

    public static boolean waitForStability(By locator) {
        boolean flag = false;
        boolean flagVisibility = false;
//        for (int i = 1; i <= CONSTANT.STEP_RETRY; i++) {
//            try {
//                ExplicitWait.waitForPresence(locator, CONSTANT.AUTOWAIT_TIME);
//                flagPresence = true;
//                System.out.println("Presence  " + flagPresence);
//                break;
//            } catch (Exception e) {
//                System.out.println("Presence retry " + i);
//            }
//        }

            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForVisibility(locator, CONSTANT.AUTOWAIT_TIME);
                    flagVisibility = true;
                    log.info("Element visible for {} on attempt {}/{}", locator, i + 1, CONSTANT.STEP_RETRY);
                    break;
                } catch (TimeoutException | NoSuchElementException e) {
                    log.warn("Element not visible for {} on attempt {}/{}: {}", locator, i + 1, CONSTANT.STEP_RETRY, e.getClass().getSimpleName());
                    if (i == CONSTANT.STEP_RETRY - 1) {
                        throw new ElementNotStableException("Element not visible after " + CONSTANT.STEP_RETRY + " retry attempts: " + locator, e);
                    }
                }
            }

        if(flagVisibility) {
            try {
                flag = getElement(locator).isDisplayed();
                log.info("Element displayed for {}", locator);
            } catch (NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
                log.warn("Element not displayed for {}: {}", locator, e.getClass().getSimpleName());
                throw new ElementNotStableException("Element not displayed for: " + locator, e);
            }
        }
        if (flag) {
            try {
                JavaScriptExecutor.scrollIntoView(locator);
                log.debug("Scrolled element into view: {}", locator);
            } catch (Exception e) {
                log.warn("Failed to scroll element into view {}: {}", locator, e.getMessage());
            }
        }
        return flag;
    }

    public static boolean waitForStability(WebElement locator) {
        boolean flag = false;
        boolean flagVisibility = false;
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            try {
                ExplicitWait.waitForVisibility(locator, CONSTANT.AUTOWAIT_TIME);
                flagVisibility = true;
                log.info("Element visible on attempt {}/{}", i + 1, CONSTANT.STEP_RETRY);
                break;
            } catch (TimeoutException | NoSuchElementException e) {
                log.warn("Element not visible on attempt {}/{}: {}", i + 1, CONSTANT.STEP_RETRY, e.getClass().getSimpleName());
                if (i == CONSTANT.STEP_RETRY - 1) {
                    throw new ElementNotStableException("Element not visible after " + CONSTANT.STEP_RETRY + " retry attempts", e);
                }
            }
        }
        if(flagVisibility) {
            try {
                flag = locator.isDisplayed();
                log.info("Element displayed successfully");
            } catch (NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
                log.warn("Element not displayed: {}", e.getClass().getSimpleName());
                throw new ElementNotStableException("Element not displayed", e);
            }
        }
        if (flag) {
            try {
                JavaScriptExecutor.scrollIntoView(locator);
                log.debug("Scrolled element into view");
            } catch (Exception e) {
                log.warn("Failed to scroll element into view: {}", e.getMessage());
            }
        }
        return flag;
    }

    public static void waitForStabilityAndClickable(By locator) {
        boolean flag = waitForStability(locator);
        if(flag) {
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForElementsToBeClickable(locator, CONSTANT.AUTOWAIT_TIME);
                    log.info("Element clickable for {} on attempt {}/{}", locator, i + 1, CONSTANT.STEP_RETRY);
                    return;
                } catch (TimeoutException | org.openqa.selenium.ElementNotInteractableException e) {
                    log.warn("Element not clickable for {} on attempt {}/{}: {}", locator, i + 1, CONSTANT.STEP_RETRY, e.getClass().getSimpleName());
                    if (i == CONSTANT.STEP_RETRY - 1) {
                        throw new ElementNotClickableException("Element not clickable after " + CONSTANT.STEP_RETRY + " retry attempts: " + locator, e);
                    }
                }
            }
        }
    }

    public static void waitForStabilityAndClickable(WebElement locator) {
        boolean flag = waitForStability(locator);
        if(flag) {
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForElementsToBeClickable(locator, CONSTANT.AUTOWAIT_TIME);
                    log.info("Element clickable on attempt {}/{}", i + 1, CONSTANT.STEP_RETRY);
                    return;
                } catch (TimeoutException | org.openqa.selenium.ElementNotInteractableException e) {
                    log.warn("Element not clickable on attempt {}/{}: {}", i + 1, CONSTANT.STEP_RETRY, e.getClass().getSimpleName());
                    if (i == CONSTANT.STEP_RETRY - 1) {
                        throw new ElementNotClickableException("Element not clickable after " + CONSTANT.STEP_RETRY + " retry attempts", e);
                    }
                }
            }
        }
    }
}
