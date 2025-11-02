package com.arc.frameworkDesktop.helper;

import com.arc.frameworkDesktop.utility.CONSTANT;
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

    public static void hardWait(int value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitOption() {
        WaitOptions waitOptions = new WaitOptions();
    }

    public static boolean waitForInvisibility(WebElement targetElement) {
        return false;
    }

    public static void waitForVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOf(getElement(element)));
    }

    public static void waitForVisibilityAttribute(By element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(getElement(element), "visible", "true"));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }

    public static void waitForVisibilityAttribute(WebElement element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(element, "visible", "true"));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }

    public static void waitForAttribute(By element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(getElement(element), attribute, value));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }

    public static void waitForAttribute(WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (StaleElementReferenceException e) {
            hardWait(1000);
        }
    }

    public static void presenceOfElementLocated(By element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void presenceOfAllElementLocated(By element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    public static void waitForVisibilityOfElements(By element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfAllElements(getElement(element)));
    }

    public static void waitForClickable(By element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(getElement(element))));
    }

    public static void waitForClickable(By element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(getElement(element))));
    }

    public static void waitForClickable(WebElement element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public static void waitForVisibility(By element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(pollingTime));
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(getElement(element)));
    }

    public static void waitForVisibility(WebElement element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForPresence(By element, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void waitForPresence(WebElement element, int pollingTime) {
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(pollingTime));
//        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void waitForInvisibility(By element) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.invisibilityOf(getElement(element)));
    }

    public static void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForAlert(int pollingTime) {
        WebDriverWait wait = new WebDriverWait(desktopDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.alertIsPresent());
    }

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
