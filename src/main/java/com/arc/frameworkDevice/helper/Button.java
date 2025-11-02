package com.arc.frameworkDevice.helper;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * Helper class for interacting with buttons and elements on the device screen.
 */
public class Button extends CommonHelper {
    private static Logger log = LogManager.getLogger(Button.class.getName());
    /**
     * Performs a single tap on the element located by the given locator.
     *
     * @param element The locator of the element to tap.
     */
    public static void singleTap(By element) {

        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element);
        AutoWait.buttonAutoWait(element);
//        Map<String, Object> args = new HashMap<>();
//        args.put("element", ((RemoteWebElement) getElement(element)).getId());
//        args.put("x", 2);
//        args.put("y", 2);
//        driver.executeScript("mobile: tap", args);
        Rectangle elRect = getElement(element).getRect();
        Point point = new Point(
                elRect.x + (int)(elRect.getWidth() / 2.0),
                elRect.y + (int)(elRect.getHeight() / 2.0)
        );
        tapAtPoint(point);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action on "+ element);
    }
    /**
     * Performs a single tap on the specified coordinates.
     *
     * @param x The x-coordinate of the point to tap.
     * @param y The y-coordinate of the point to tap.
     */
    public static void singleTap(int x, int y) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ x +", "+ y +")");
        Point point = new Point(x, y);
        tapAtPoint(point);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ x +", "+ y +")");
    }
    /**
     * Performs a double tap on the specified coordinates.
     *
     * @param x The x-coordinate of the point to tap.
     * @param y The y-coordinate of the point to tap.
     */
    public static void doubleTap(int x, int y) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ x +", "+ y +")");
        Point point = new Point(x, y);
        tapAtPoint(point);
        tapAtPoint(point);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ x +", "+ y +")");

    }
    /**
     * Performs a single tap on the given WebElement.
     *
     * @param element The WebElement to tap.
     */
    public static void singleTap(WebElement element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element);
        AutoWait.buttonAutoWait(element);
        Rectangle elRect = element.getRect();
        Point point = new Point(
                elRect.x + (int)(elRect.getWidth() / 2.0),
                elRect.y + (int)(elRect.getHeight() / 2.0)
        );
        tapAtPoint(point);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action on "+ element);
    }
    /**
     * Taps on the specified point.
     *
     * @param point The Point.
     */
    protected static void tapAtPoint(Point point) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ point.x +", "+ point.y +")");
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofMillis(200)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        deviceDriver.perform(ImmutableList.of(tap));
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ point.x +", "+ point.y +")");
    }
    /**
     * Performs a tap on the element located by the given locator.
     *
     * @param el The locator of the element to double tap.
     */

    protected static void tapElement(By el) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ el +")");

        Rectangle elRect = getElement(el).getRect();
        Point point = new Point(
                elRect.x + (int)(elRect.getWidth() / 2.0),
                elRect.y + (int)(elRect.getHeight() / 2.0)
        );
        tapAtPoint(point);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+el+")");

    }
    /**
     * Performs a double tap on the element located by the given locator.
     *
     * @param element The locator of the element to double tap.
     */
    public static void doubleTap(By element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ element+")");

        Map<String, Object> args = new HashMap<>();
        args.put("element", ((RemoteWebElement) getElement(element)).getId());
        args.put("x", 100);
        args.put("y", 200);
        deviceDriver.executeScript("mobile: doubleTap", args);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ element +")");

    }
    /**
     * Taps on the specified coordinates of the element located by the given locator.
     *
     * @param element The locator of the element.
     */
    public static void tapOnElementCoordinate(By element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ element +")");

        AutoWait.elementWait(element);
        PointOption pointOption = PointOption.point(ElementInfo.getXPosition(element), ElementInfo.getYPosition(element));
        TapOptions tapOptions = new TapOptions();
        tapOptions.withPosition(pointOption);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ element +")");

    }


    /**
     * Performs a double tap on the element located by the given locator.
     *
     * @param element The locator of the element to double tap.
     */
    public static void doubleTap(WebElement element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ element+")");

        Actions actions = new Actions(deviceDriver);
        actions.doubleClick(element).perform();
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ element +")");

    }
    /**
     * Taps on the specified coordinates of the element located by the given locator.
     *
     * @param element The locator of the element.
     */
    public static void tapOnElementCoordinate(WebElement element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ element+")");

        PointOption pointOption = PointOption.point(ElementInfo.getXPosition(element), ElementInfo.getYPosition(element));
        TapOptions tapOptions = new TapOptions();
        tapOptions.withPosition(pointOption);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ element+")");

    }
    /**
     * Taps on the center of the screen at the specified coordinates.
     *
     * @param pointX The x-coordinate of the point.
     * @param pointY The y-coordinate of the point.
     */
    public static void tapInCenterCoordinate(int pointX, int pointY) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ pointX +", "+ pointY+")");

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence clickPosition = new Sequence(finger, 1);
        clickPosition.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointX, pointY)).addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        deviceDriver.perform(Arrays.asList(clickPosition));
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ pointX +", "+ pointY +")");

    }
    /**
     * Taps on the element located by the given locator at its coordinates.
     *
     * @param element The locator of the element.
     */
    public static void tapByElementCoordinate(By element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+element+")");

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence clickPosition = new Sequence(finger, 1);
        clickPosition.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),ElementInfo.getXPosition(element), ElementInfo.getYPosition(element))).addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        deviceDriver.perform(Arrays.asList(clickPosition));
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ element +")");

    }
    /**
     * Clicks on the element located by the given locator using JavaScript executor.
     *
     * @param elementToClick The locator of the element to click.
     */
    public void elementClickByJavaScriptExecutor(By elementToClick) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action at ("+ elementToClick +")");

        Point p = ((Locatable) getElement(elementToClick)).getCoordinates().onPage();
        int x = p.getX();
        int y = p.getY();
        deviceDriver.executeScript("mobile: tap", new HashMap<String, Double>() {
            {
                put("tapCount", (double) 1);
                put("touchCount", (double) 1);
                put("duration", 0.5);
                put("x", (double) x);
                put("y", (double) y);
            }
        });

        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action at ("+ elementToClick +")");
    }
//    public static void clickOnKeyCode(){
//        ((AndroidDriver) deviceDriver).pressKey(AndroidKey.SEARCH);
//    }
}
