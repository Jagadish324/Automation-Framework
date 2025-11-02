package com.arc.frameworkDesktop.helper;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GestureAction extends CommonHelper {
    private static Logger log = LogManager.getLogger(GestureAction.class.getName());


    public static void swipeUp(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "up");
        desktopDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void swipeUp(int velocity){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "up");
        map.put("velocity", velocity);
        desktopDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void swipeDown(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "down");
        desktopDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void swipeDown(int velocity){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "down");
        map.put("velocity", velocity);
        desktopDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void pullToRefresh(int velocity){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        longPressIOS((int)(desktopDriver.manage().window().getSize().width * 0.5),(int)(desktopDriver.manage().window().getSize().height * 0.5));
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "down");
        map.put("velocity", velocity);
        desktopDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    //Android direction up,down,left,right
    public static void swipeToElementAndroid(By element, String direction){
        AutoWait.elementWait(element);
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: scrollGesture",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction",direction,"percent",3.0);
    }
//    public static Boolean canScrollByPositionAndroid(String direction){
////        return (Boolean)  ((JavascriptExecutor) desktopDriver).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction",direction,"percent",3.0));
//    }

    public static void swipeToElementIOS(By element,String direction){
        AutoWait.elementWait(element);
        Map<String, Object> map = new HashMap<>();
        map.put("direction", direction);
        map.put("elementId",((RemoteWebElement)getElement(element)).getId());
        map.put("percent",3.0);
        map.put("toVisible","true");
        desktopDriver.executeScript("mobile: swipe", map);

//        ((JavascriptExecutor) driver).executeScript("mobile: swipe",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction","down","percent",3.0);
    }
    public static void swipeToElementIOS(WebElement element,String direction){
        AutoWait.elementWait(element);
        Map<String, Object> map = new HashMap<>();
        map.put("direction", direction);
        map.put("elementId",((RemoteWebElement)element).getId());
        map.put("percent",3.0);
        map.put("toVisible","true");
        desktopDriver.executeScript("mobile: swipe", map);
//        AutoWait.elementWait(element);
//        ((JavascriptExecutor) driver).executeScript("mobile: swipe",ImmutableMap.of("elementId",((RemoteWebElement)element).getId()),"direction",direction,"percent",3.0);
    }
    public static void scrollToElementIOS(By element,String direction){
        AutoWait.elementWait(element);
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: scroll",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction",direction,"percent",3.0);
    }
    public static void scrollToElementIOS(WebElement element,String direction){
        AutoWait.elementWait(element);
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: scroll",ImmutableMap.of("elementId",((RemoteWebElement)element).getId()),"direction",direction,"percent",3.0);
    }
//    public static Boolean canScrollByPositionIOS(String direction){
////        return (Boolean)  ((JavascriptExecutor) desktopDriver).executeScript("mobile: scroll", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction",direction,"percent",3.0));
//    }


    public static void dragAndDropByElement(By sourceElement, By destinationElement){
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",((RemoteWebElement)getElement(sourceElement)).getId()),"endX", ElementInfo.getXPosition(destinationElement),"getY", ElementInfo.getYPosition(destinationElement));
    }

    public static void zoomPint(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
                "percent", 0.75
        ));
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());



//            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//            PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
//
//            Dimension size = driver.manage().window().getSize();
//            Point source = new Point(size.getWidth(), size.getHeight());
//
//            Sequence pinchAndZoom1 = new Sequence(finger, 0);
//            pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(0),
//                    PointerInput.Origin.viewport(), source.x / 2, source.y / 2));
//            pinchAndZoom1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//            pinchAndZoom1.addAction(new Pause(finger, Duration.ofMillis(100)));
//            pinchAndZoom1.addAction(finger.createPointerMove(Duration.ofMillis(600),
//                    PointerInput.Origin.viewport(), source.x / 3, source.y / 3));
//            pinchAndZoom1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        }

    public static void dragAndDropByCoordinate(int startX, int startY,int endX,int endY){
        PointerInput finger = new PointerInput(PointerInput.Kind.MOUSE, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        desktopDriver.perform(Arrays.asList(dragNDrop));



//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        Map<String, Object> params = new HashMap<>();
//        params.put("duration", 1.0);
//        params.put("fromX", startX);
//        params.put("fromY", startY);
//        params.put("toX", endX);
//        params.put("toY", endY);
////        params.put("element", ((RemoteWebElement) element).getId());
//        js.executeScript("mobile: dragFromToForDuration", params);
    }

    public static void swipeDownByParams(By element){
        AutoWait.elementWait(element);
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("velocity", 2500);
        params.put("element", ((RemoteWebElement) getElement(element)).getId());
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: swipe", params);
    }
    public static void swipeDownByParams(WebElement element){
        AutoWait.elementWait(element);
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("velocity", 2500);
        params.put("element", ((RemoteWebElement) element).getId());
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: swipe", params);
    }
    public static void longPressAndroid(By element){
        AutoWait.buttonAutoWait(element);
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: longClickGesture" , ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"duration",2000);
    }

    public static void longPressIOS(By element){
        AutoWait.buttonAutoWait(element);
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: touchAndHold" , ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"duration",2000);
    }
    public static void longPressIOS(int x, int y) {
//        Map<String, Object> args = new HashMap<>();
//        args.put("element", ((RemoteWebElement) getElement(element)).getId());
//        args.put("x", 2);
//        args.put("y", 2);
//        driver.executeScript("mobile: tap", args);
        Point point = new Point(
                x,y
        );
        tapAndHoldAtPoint(point);
    }
    public static void tapAndHoldAtPoint(Point point) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofSeconds(3)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        desktopDriver.perform(ImmutableList.of(tap));
    }
    public static void tapAndHoldAtPoint(By locator, int durationInSeconds) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), ElementInfo.getXLocation(locator), ElementInfo.getYLocation(locator)));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofSeconds(durationInSeconds)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        desktopDriver.perform(ImmutableList.of(tap));
    }

    public static void pullToRefresh()
    {
        for(int i=0;i< 2;i++) {
            GestureAction.dragAndDropByCoordinate(500,500,500,3000);
            //ElementWait.hardWait(2000);
        }
    }

    public static void swipeUpByParams(By element){
        AutoWait.elementWait(element);
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        params.put("velocity", 2500);
        params.put("element", ((RemoteWebElement) getElement(element)).getId());
        ((JavascriptExecutor) desktopDriver).executeScript("mobile: swipe", params);
    }
    public static void mobileCommandScrollDown(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        desktopDriver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void mobileCommandScrollUp(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        desktopDriver.executeScript("mobile: scroll", ImmutableMap.of("direction", "up"));
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void tapInCenter(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Button.tapInCenterCoordinate((int)(desktopDriver.manage().window().getSize().width * 0.5),(int)(desktopDriver.manage().window().getSize().height * 0.5));
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

//    /**
//     * Performs element scroll by predicate string
//     *
//     * @param el  the element to scroll
//     * @param pre the predicate string
//     * @version java-client: 7.3.0
//     **/
//    public static void mobileScrollToElementIOS(By el, String pre) {
//        System.out.println("mobileScrollToElementIOS(): pre: '" + pre + "'"); // always log your actions
//
//        // Animation default time:
//        //  - iOS: 200 ms
//        // final value depends on your app and could be greater
//        final int ANIMATION_TIME = 200; // ms
//        final HashMap scrollObject = new HashMap();
//        scrollObject.put("element", ((RemoteWebElement)getElement(el)).getId());
//        scrollObject.put("predicateString", pre);
//        try {
//            driver.executeScript("mobile:scroll", scrollObject);
//            Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
//        } catch (Exception e) {
//            System.err.println("mobileScrollToElementIOS(): FAILED\n" + e.getMessage());
//            return;
//        }
//    }
//    public static void mobileSwipeToElementIOS(By el, String pre) {
//        System.out.println("mobileScrollToElementIOS(): pre: '" + pre + "'"); // always log your actions
//
//        // Animation default time:
//        //  - iOS: 200 ms
//        // final value depends on your app and could be greater
//        final int ANIMATION_TIME = 200; // ms
//        final HashMap scrollObject = new HashMap();
//        scrollObject.put("direction", pre);
//        scrollObject.put("element", ((RemoteWebElement)getElement(el)).getId());
////        scrollObject.put("predicateString", pre);
//        try {
//            driver.executeScript("mobile:swipe", scrollObject);
//            Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
//        } catch (Exception e) {
//            System.err.println("mobileScrollToElementIOS(): FAILED\n" + e.getMessage());
//            return;
//        }
//    }



//    public void mobileScrollElementIOS(By el, Direction dir) {
//        System.out.println("mobileScrollElementIOS(): dir: '" + dir + "'"); // always log your actions
//        final int ANIMATION_TIME = 200; // ms
//        final HashMap<String, String> scrollObject = new HashMap<String, String>();
//        scrollObject.put("direction", String.valueOf(dir));
//        scrollObject.put("element", String.valueOf(driver.findElement(el)));
//        try {
//            driver.executeScript("mobile:scroll", scrollObject); // swipe faster then scroll
//            Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
//        } catch (Exception e) {
//            System.err.println("mobileScrollElementIOS(): FAILED\n" + e.getMessage());
//            return;
//        }
//    }

//    public static void scrollToElementDown(By element){
//        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction","down","percent",3.0);
//    }
//    public static void scrollToElementUp(By element){
//        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction","up","percent",3.0);
//    }
//    public static void swipeToElementLeft(By element){
//        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction","left","percent",3.0);
//    }
//    public static void swipeToElementRight(By element){
//        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction","right","percent",3.0);
//    }

//    public static Boolean canScrollDownByPosition(){
//        return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction","down","percent",3.0));
//    }
//    public static Boolean canScrollUpByPosition(){
//        return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction","up","percent",3.0));
//    }
//    public static Boolean canScrollLeftByPosition(){
//        return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction","left","percent",3.0));
//    }
//    public static Boolean canScrollRightByPosition(){
//        return (Boolean)  ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction","right","percent",3.0));
//    }
}
