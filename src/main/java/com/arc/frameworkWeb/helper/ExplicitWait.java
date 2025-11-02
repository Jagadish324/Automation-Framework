package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.arc.frameworkWeb.utility.CONSTANT;

import java.time.Duration;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class ExplicitWait extends CommonHelper {
    private static Logger log = LogManager.getLogger(ExplicitWait.class.getName());
    /**
     * Pauses the execution for the specified time.
     * @param timeValue The time to wait in milliseconds or seconds.
     */
    public static void hardWait(int timeValue) {
        try {
            if ((timeValue + "").length() >= 3) {
                Thread.sleep(timeValue);
                log.info("Waited for seconds " + timeValue);
            } else {
                Thread.sleep(timeValue * 1000);
                log.info("Waited for seconds" + timeValue);
            }
        } catch (InterruptedException e) {
            log.info(e);
            throw new RuntimeException(e);
        }
    }
    /**
     * Waits for the visibility of an element identified by the provided locator.
     * @param locator The locator for the element.
     */
    public static void waitForVisibility(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            log.info("Waiting for visibility of elements located by " + locator + " at interval of 500ms for " + CONSTANT.EXPLICIT_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.info("element is visible in ");
        }
    }
    /**
     * Waits for the presence of an element identified by the provided locator.
     * @param locator The locator for the element.
     */
    public static void waitForPresence(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
    }
    /**
     * Waits for the presence of an attribute in an element identified by the provided locator, containing the specified value.
     * @param locator The locator for the element.
     * @param attribute The attribute to check.
     * @param value The value to contain in the attribute.
     */
    public static void waitForAttributeContains(By locator, String attribute,  String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.attributeContains(locator,attribute,value));
        }
    }
    /**
     * Waits for the presence of an attribute in an element identified by the provided WebElement, containing the specified value.
     * @param locator The WebElement for the element.
     * @param attribute The attribute to check.
     * @param value The value to contain in the attribute.
     */
    public static void waitForAttributeContains(WebElement locator, String attribute,  String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.attributeContains(locator,attribute,value));
        }
    }
    /**
     * Waits for the presence of an element identified by the provided locator using fluent wait.
     * @param locator The locator for the element.
     */
    public static void fluentWaitForPresence(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Wait wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT)).pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
    }
    /**
     * Waits for the presence of an element identified by the provided locator using fluent wait.
     * @param locator The locator for the element.
     * @param pollingTime The polling time is seconds
     */
    public static void fluentWaitForPresence(By locator,int pollingTime) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Wait wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(Exception.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
    }
    /**
     * Waits for an element identified by the provided locator to be clickable using fluent wait.
     * @param locator The locator for the element.
     */
    public static void fluentWaitForClickable(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Wait wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT)).pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }
    /**
     * Waits for an element identified by the provided locator to be clickable using fluent wait.
     * @param locator The locator for the element.
     * @param pollingTime The polling time is seconds
     */
    public static void fluentWaitForClickable(By locator, int pollingTime) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Wait wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(Exception.class);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }
    /**
     * Waits for the visibility of the given WebElement.
     * @param locator The WebElement to wait for.
     */
    public static void waitForVisibility(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.visibilityOf(locator));
        }
    }
    /**
     * Waits for the visibility and clickability of the given WebElement.
     * @param locator The WebElement to wait for.
     */
    public static void waitForVisibilityAndClickabilityOfElement(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.visibilityOf(locator));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }
    /**
     * Waits for the visibility and clickAbility of the element specified by the locator.
     * @param locator The locator of the element to wait for.
     */
    public static void waitForVisibilityAndClickabilityOfElement(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }
    /**
     * Waits for the visibility and invisibility of the element specified by the locator.
     * @param locator The locator of the element to wait for.
     */
    public static void waitForVisibilityAndInvisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    /**
     * Waits for the visibility and invisibility of the given WebElement.
     * @param locator The WebElement to wait for.
     */
    public static void waitForVisibilityAndInvisibility(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOf(locator));
        wait.until(ExpectedConditions.invisibilityOf(locator));
    }
    /**
     * Waits for the given element specified by the locator to be clickable.
     * @param locator The locator of the element to wait for.
     */
    public static void waitForElementsToBeClickable(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }
    /**
     * Waits for the given WebElement to be clickable.
     * @param locator The WebElement to wait for.
     */
    public static void waitForElementsToBeClickable(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }

    /*
     *Auto wait
     */
    /**
     * Waits for the visibility of the element specified by the locator with a custom polling time.
     * @param locator The locator of the element to wait for.
     * @param pollingTime The custom polling time in seconds.
     */
    public static void waitForVisibility(By locator, int pollingTime) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(pollingTime));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }
    /**
     * Waits for the presence of the element specified by the locator with a custom polling time.
     * @param locator The locator of the element to wait for.
     * @param pollingTime The custom polling time in seconds.
     */
    public static void waitForPresence(By locator, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    /**
     * Waits for the given element specified by the locator to be clickable with a custom polling time.
     * @param locator The locator of the element to wait for.
     * @param pollingTime The custom polling time in seconds.
     */
    public static void waitForElementsToBeClickable(By locator, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    /**
     * Waits for the visibility of the given WebElement with a custom polling time.
     * @param locator The WebElement to wait for.
     * @param pollingTime The custom polling time in seconds.
     */
    public static void waitForVisibility(WebElement locator, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.visibilityOf(locator));
    }
    /**
     * Waits for the given WebElement to be clickable with a custom polling time.
     * @param locator The WebElement to wait for.
     * @param pollingTime The custom polling time in seconds.
     */
    public static void waitForElementsToBeClickable(WebElement locator, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(pollingTime));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    /**
     * Waits until the number of windows is equal to the specified count.
     */
    public static void waitUntilWindowOpen() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(numberOfWindowsToBe(2));
    }
    /**
     * Waits for the element specified by the locator to become invisible.
     * @param locator The locator of the element to wait for.
     */
    public static void waitForInvisibilityOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    /**
     * Waits for the element specified by the locator to become invisible.
     * @param locator The locator of the element to wait for.
     * @param pollingTime The custom polling time in seconds
     */
    public static void waitForInvisibilityOfElement(By locator, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(pollingTime));
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    /**
     * Turns on implicit waits with the specified timeout.
     */
    public static void turnOnImplicitWaits() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CONSTANT.IMPLICIT_WAIT));
    }
    /**
     * Waits until a frame with the specified locator is available and switches to it.
     * @param frameLocator The locator of the frame to wait for.
     */
    public static void waitAndSwitchToFrame(By frameLocator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

    }
    /**
     * Waits until the page is fully loaded.
     */
    public static void waitUntilThePageIsLoaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));
        //JavascriptExecutor jsExecutor = (JavascriptExecutor) WebDriverFactory.getDriver();
        wait.until((webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")));

    }
    /**
     * Turns off implicit waits.
     */
    public static void turnOffImplicitWaits() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }
    /**
     * Waits for a child window to open.
     * @return true if the child window is opened within the specified number of retries, false otherwise.
     */
    public boolean waitForChildWindow() {
        boolean status = false;
        for (int i = 0; i < 5; i++) {
            Set<String> windowHandles = webDriver.getWindowHandles();
            if (windowHandles.size() > 1) {
                status = true;
                break;
            } else {
                ExplicitWait.hardWait(1000);
            }
        }
        return status;
    }

}
