package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.arc.frameworkWeb.utility.CONSTANT;
import com.arc.frameworkWeb.exceptions.ElementNotFoundException;
import com.arc.frameworkWeb.exceptions.ElementNotInteractableException;

import java.time.Duration;

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
        try {
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                ExplicitWait.waitUntilWindowOpen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void autoWaitAlert() {
        try {
            int totalWaitTime = CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME;
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(totalWaitTime));
            wait.pollingEvery(Duration.ofMillis(250))
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());
            log.info("Alert appeared within " + totalWaitTime + " seconds");
        } catch (TimeoutException e) {
            log.warn("Alert did not appear within " + (CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME) + " seconds");
        } catch (Exception e) {
            log.error("Error while waiting for alert", e);
        }
    }

    public static boolean waitForStability(By locator) {
        try {
            int totalWaitTime = CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME;
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(totalWaitTime));
            wait.pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotVisibleException.class);

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            // Verify element is truly displayed
            if (!element.isDisplayed()) {
                log.warn("Element found but not displayed: " + locator);
                return false;
            }

            // Scroll into view if element is not in viewport
            JavaScriptExecutor.scrollIntoView(locator);
            log.info("Element is stable and visible: " + locator);
            return true;

        } catch (TimeoutException e) {
            log.warn("Element not visible within " + (CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME) + " seconds: " + locator);
            return false;
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException(locator.toString(),
                "Element not found: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error while waiting for element stability: " + locator, e);
            return false;
        }
    }

    public static boolean waitForStability(WebElement locator) {
        try {
            int totalWaitTime = CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME;
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(totalWaitTime));
            wait.pollingEvery(Duration.ofMillis(250))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotVisibleException.class);

            WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));

            // Verify element is truly displayed
            if (!element.isDisplayed()) {
                log.warn("Element found but not displayed");
                return false;
            }

            // Scroll into view
            JavaScriptExecutor.scrollIntoView(locator);
            log.info("Element is stable and visible");
            return true;

        } catch (TimeoutException e) {
            log.warn("Element not visible within " + (CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME) + " seconds");
            return false;
        } catch (Exception e) {
            log.error("Unexpected error while waiting for element stability", e);
            return false;
        }
    }

    public static void waitForStabilityAndClickable(By locator) {
        try {
            // First ensure element is stable and visible
            if (!waitForStability(locator)) {
                throw new ElementNotFoundException(locator.toString(),
                    "Element is not stable or visible");
            }

            // Then wait for it to be clickable
            int totalWaitTime = CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME;
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(totalWaitTime));
            wait.pollingEvery(Duration.ofMillis(250))
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class);

            wait.until(ExpectedConditions.elementToBeClickable(locator));
            log.info("Element is stable and clickable: " + locator);

        } catch (TimeoutException e) {
            throw new ElementNotInteractableException("click", locator.toString(),
                "Element not clickable within " + (CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME) + " seconds");
        } catch (ElementNotFoundException e) {
            throw e; // Re-throw as-is
        } catch (Exception e) {
            log.error("Unexpected error while waiting for element to be clickable: " + locator, e);
            throw new ElementNotInteractableException("click", locator.toString(), e);
        }
    }

    public static void waitForStabilityAndClickable(WebElement locator) {
        try {
            // First ensure element is stable and visible
            if (!waitForStability(locator)) {
                throw new ElementNotInteractableException("click", locator.toString(),
                    "Element is not stable or visible");
            }

            // Then wait for it to be clickable
            int totalWaitTime = CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME;
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(totalWaitTime));
            wait.pollingEvery(Duration.ofMillis(250))
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class);

            wait.until(ExpectedConditions.elementToBeClickable(locator));
            log.info("Element is stable and clickable");

        } catch (TimeoutException e) {
            throw new ElementNotInteractableException("click", locator.toString(),
                "Element not clickable within " + (CONSTANT.STEP_RETRY * CONSTANT.AUTOWAIT_TIME) + " seconds");
        } catch (ElementNotFoundException e) {
            throw e; // Re-throw as-is
        } catch (Exception e) {
            log.error("Unexpected error while waiting for element to be clickable", e);
            throw new ElementNotInteractableException("click", locator.toString(), e);
        }
    }
}
