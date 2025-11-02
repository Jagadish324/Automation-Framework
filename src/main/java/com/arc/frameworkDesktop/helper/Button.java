package com.arc.frameworkDesktop.helper;

import com.google.common.collect.ImmutableList;
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

public class Button extends CommonHelper {
    private static Logger log = LogManager.getLogger(Button.class.getName());

    public static void singleTap(By element) {

        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element);
        getElement(element).click();
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action on "+ element);
    }
    public static void doubleTap(By element) {

        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element);
        getElement(element).click();
        getElement(element).click();
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and performed action on "+ element);
    }
    public static void singleTap(int x, int y) {
        Point point = new Point(
                x,y
        );
        tapAtPoint(point);
    }

    public static void doubleTap(int x, int y) {
        Point point = new Point(x,y);
        tapAtPoint(point);
        tapAtPoint(point);
    }
    public static void singleTap(WebElement element) {
        AutoWait.buttonAutoWait(element);
//        Map<String, Object> args = new HashMap<>();
//        args.put("element", ((RemoteWebElement) getElement(element)).getId());
//        args.put("x", 2);
//        args.put("y", 2);
//        driver.executeScript("mobile: tap", args);
        Rectangle elRect = element.getRect();
        Point point = new Point(
                elRect.x + (int)(elRect.getWidth() / 2.0),
                elRect.y + (int)(elRect.getHeight() / 2.0)
        );
        tapAtPoint(point);
    }
    protected static void tapAtPoint(Point point) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofMillis(200)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        desktopDriver.perform(ImmutableList.of(tap));
    }


    protected static void tapElement(By el) {
        Rectangle elRect = getElement(el).getRect();
        Point point = new Point(
                elRect.x + (int)(elRect.getWidth() / 2.0),
                elRect.y + (int)(elRect.getHeight() / 2.0)
        );
        tapAtPoint(point);
    }


    public static void tapOnElementCoordinate(By element) {
        AutoWait.elementWait(element);
        PointOption pointOption = PointOption.point(ElementInfo.getXPosition(element), ElementInfo.getYPosition(element));
        TapOptions tapOptions = new TapOptions();
        tapOptions.withPosition(pointOption);
    }



    public static void doubleTap(WebElement element) {
        Actions actions = new Actions(desktopDriver);
        actions.doubleClick(element).perform();
    }

    public static void tapOnElementCoordinate(WebElement element) {
        PointOption pointOption = PointOption.point(ElementInfo.getXPosition(element), ElementInfo.getYPosition(element));
        TapOptions tapOptions = new TapOptions();
        tapOptions.withPosition(pointOption);
    }

    public static void tapInCenterCoordinate(int pointX, int pointY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence clickPosition = new Sequence(finger, 1);
        clickPosition.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointX, pointY)).addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        desktopDriver.perform(Arrays.asList(clickPosition));

    }
    public static void tapByElementCoordinate(By element) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence clickPosition = new Sequence(finger, 1);
        clickPosition.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), ElementInfo.getXPosition(element), ElementInfo.getYPosition(element))).addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        desktopDriver.perform(Arrays.asList(clickPosition));
    }
    public void elementClickByJavaScriptExecutor(By elementToClick) {
        Point p = ((Locatable) getElement(elementToClick)).getCoordinates().onPage();
        int x = p.getX();
        int y = p.getY();
        desktopDriver.executeScript("mobile: tap", new HashMap<String, Double>() {
            {
                put("tapCount", (double) 1);
                put("touchCount", (double) 1);
                put("duration", 0.5);
                put("x", (double) x);
                put("y", (double) y);
            }
        });
    }
//    public static void clickOnKeyCode(){
//        ((AndroidDriver) desktopDriver).pressKey(AndroidKey.SEARCH);
//    }
}
