package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;
import io.appium.java_client.touch.WaitOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementWait extends CommonHelper {
    private static Logger log = LogManager.getLogger(ElementWait.class.getName());


    public static void implicitWait() {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CONSTANT.IMPLICIT_WAIT));
    }
    /**
     * Pauses execution for a specified duration.
     *
     * @param value The duration to wait in milliseconds.
     */
    public static void hardWait(int value) {
        try {
            if ((value + "").length() >= 3) {
                Thread.sleep(value);
                log.info("Waited for seconds " + value);
            } else {
                Thread.sleep(value * 1000);
                log.info("Waited for seconds" + value);
            }
        } catch (InterruptedException e) {
            log.info(e);
            throw new RuntimeException(e);
        }
    }

    public static void waitOption() {
        WaitOptions waitOptions = new WaitOptions();
    }

    public static boolean waitForInvisibility(WebElement targetElement) {
        return false;
    }
    /**
     * Waits for an element to be visible on the page.
     *
     * @param element The locator of the element.
     */
    public static void waitForVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitForVisibilityAttribute(By element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(getElement(element), "visible", "true"));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }

    public static void waitForVisibilityAttribute(WebElement element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(element, "visible", "true"));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }
    /**
     * Waits for an element to have a specific attribute value.
     *
     * @param element     The locator of the element.
     * @param attribute   The name of the attribute.
     * @param value       The expected value of the attribute.
     */
    public static void waitForAttribute(By element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(getElement(element), attribute, value));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }
    /**
     * Waits for the specified attribute of the element to contain the given value.
     *
     * @param element   The web element to wait for.
     * @param attribute The attribute to check.
     * @param value     The expected value of the attribute.
     */
    public static void waitForAttribute(WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }
    /**
     * Waits for the presence of an element located by the given locator.
     *
     * @param element The locator of the element to wait for.
     */
    public static void presenceOfElementLocated(By element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
    /**
     * Waits for the presence of an element located by the given locator.
     *
     * @param element The locator of the element to wait for.
     * @param pollingTime The time it will wait for the element
     */
    public static void presenceOfElementLocated(By element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
    /**
     * Waits for the presence of all elements located by the given locator.
     *
     * @param element The locator of the elements to wait for.
     */
    public static void presenceOfAllElementLocated(By element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }
    /**
     * Waits for the visibility of all elements located by the given locator.
     *
     * @param element The locator of the elements to wait for.
     */
    public static void waitForVisibilityOfElements(By element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfAllElements(getElement(element)));
    }
    /**
     * Waits for the element located by the given locator to be clickable.
     *
     * @param element The locator of the element to wait for.
     */
    public static void waitForClickable(By element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(getElement(element))));
    }
    /**
     * Waits for the element located by the given locator to be clickable with a specified polling time.
     *
     * @param element    The locator of the element to wait for.
     * @param pollingTime The polling time in seconds.
     */
    public static void waitForClickable(By element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(getElement(element))));
    }
    /**
     * Waits for the web element to be clickable with a specified polling time.
     *
     * @param element    The web element to wait for.
     * @param pollingTime The polling time in seconds.
     */
    public static void waitForClickable(WebElement element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }
    /**
     * Waits for the element located by the given locator to be visible with a specified polling time.
     * Ignores StaleElementReferenceException during the wait.
     *
     * @param element    The locator of the element to wait for.
     * @param pollingTime The polling time in seconds.
     */
    public static void waitForVisibility(By element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(getElement(element)));
    }
    /**
     * Waits for the web element to be visible with a specified polling time.
     *
     * @param element    The web element to wait for.
     * @param pollingTime The polling time in seconds.
     */
    public static void waitForVisibility(WebElement element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Waits for the presence of an element located by the given locator with a specified polling time.
     *
     * @param element    The locator of the element to wait for.
     * @param pollingTime The polling time in seconds.
     */
    public static void waitForPresence(By element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
    /**
     * Waits for the presence of an element located by the given locator with a specified polling time.
     *
     * @param element    The locator of the element to wait for.
     * @param pollingTime The polling time in seconds.
     */
    public static void waitForPresence(WebElement element, int pollingTime) {
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(pollingTime));
//        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
    /**
     * Waits for the invisibility of an element located by the given locator.
     *
     * @param element The locator of the element to wait for.
     */
    public static void waitForInvisibility(By element) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    /**
     * Waits for the invisibility of an element located by the given locator.
     *
     * @param element The locator of the element to wait for.
     */
    public static void waitForInvisibility(By element,int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    /**
     * Waits for an alert to be present.
     */
    public static void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    /**
     * Waits for an alert to be present with a specified polling time.
     *
     * @param pollingTime The polling time in seconds.
     */
    public static void waitForAlert(int pollingTime) {
        WebDriverWait wait = new WebDriverWait(deviceDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    /**
     * Custom visibility wait for an element identified by the given locator and attribute.
     *
     * @param locator      The locator of the element to wait for.
     * @param attributeName The attribute name indicating the visibility state.
     */
    public static void customVisibilityWait(By locator, String attributeName) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() + " and performing action on " + locator);
        int time = CONSTANT.AUTOWAIT_TIME * 2;
        for (int i = 1; i <= time; i++) {
            String value = "";
            try {
                value = getElement(locator).getAttribute(attributeName);
            } catch (Exception e) {
                log.info("Exception " + i + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " visibility for " + locator);
            }
            if (value.equalsIgnoreCase("true")) {
                break;
            }
            ElementWait.hardWait(500);
            log.info("Retry " + i + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " visibility for " + locator);

        }
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() + " and performed action on " + locator);
    }
    /**
     * Custom visibility wait for a web element identified by the given locator and attribute.
     *
     * @param locator      The web element to wait for.
     * @param attributeName The attribute name indicating the visibility state.
     */
    public static void customVisibilityWait(WebElement locator, String attributeName) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() + " and performing action on " + locator);
        int time = CONSTANT.AUTOWAIT_TIME * 2;
        for (int i = 1; i <= time; i++) {
            String value = "";
            try {
                value = locator.getAttribute(attributeName) + "";
            } catch (Exception e) {
                log.info("Exception " + i + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " visibility for " + locator);
            }
            if (value.equalsIgnoreCase("true")) {
                break;
            }
            ElementWait.hardWait(500);
            log.info("Retry " + i + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " visibility for " + locator);
        }
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() + " and performed action on " + locator);
    }


}
