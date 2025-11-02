package com.arc.frameworkDevice.helper;

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

public class GestureAction extends CommonHelper{
    private static Logger log = LogManager.getLogger(GestureAction.class.getName());

    /**
     * Swipe up on the mobile device.
     */
    public static void swipeUp(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "up");
        deviceDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    /**
     * Swipe up on the mobile device with a specified velocity.
     *
     * @param velocity The velocity of the swipe.
     */
    public static void swipeUp(int velocity){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "up");
        map.put("velocity", velocity);
        deviceDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    /**
     * Swipe down on the mobile device.
     */
    public static void swipeDown(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "down");
        deviceDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    /**
     * Swipe down on the mobile device with a specified velocity.
     *
     * @param velocity The velocity of the swipe.
     */
    public static void swipeDown(int velocity){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "down");
        map.put("velocity", velocity);
        deviceDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    /**
     * Perform a pull-to-refresh action on the mobile device with a specified velocity.
     *
     * @param velocity The velocity of the swipe.
     */
    public static void pullToRefresh(int velocity){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        longPressIOS((int)(deviceDriver.manage().window().getSize().width * 0.5),(int)(deviceDriver.manage().window().getSize().height * 0.5));
        Map<String, Object> map = new HashMap<>();
        map.put("direction", "down");
        map.put("velocity", velocity);
        deviceDriver.executeScript("mobile: swipe", map);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    /**
     * Swipe to an element on an Android device.
     *
     * @param element   The element to swipe to.
     * @param direction The direction of the swipe (up, down, left, right).
     */
    public static void swipeToElementAndroid(By element, String direction){
        AutoWait.elementWait(element);
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: scrollGesture",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction",direction,"percent",3.0);
    }
    public static Boolean canScrollByPositionAndroid(String direction){
        return (Boolean)  ((JavascriptExecutor) deviceDriver).executeScript("mobile: scrollGesture", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction",direction,"percent",3.0));
    }
    /**
     * Swipe to an element on an iOS device.
     *
     * @param element   The element to swipe to.
     * @param direction The direction of the swipe (up, down, left, right).
     */
    public static void swipeToElementIOS(By element,String direction){
        AutoWait.elementWait(element);
        Map<String, Object> map = new HashMap<>();
        map.put("direction", direction);
        map.put("elementId",((RemoteWebElement)getElement(element)).getId());
        map.put("percent",3.0);
        map.put("toVisible","true");
        deviceDriver.executeScript("mobile: swipe", map);

//        ((JavascriptExecutor) driver).executeScript("mobile: swipe",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction","down","percent",3.0);
    }
    /**
     * Swipe to an element on an Android device.
     *
     * @param element   The element to swipe to.
     * @param direction The direction of the swipe (up, down, left, right).
     */
    public static void swipeToElementIOS(WebElement element,String direction){
        AutoWait.elementWait(element);
        Map<String, Object> map = new HashMap<>();
        map.put("direction", direction);
        map.put("elementId",((RemoteWebElement)element).getId());
        map.put("percent",3.0);
        map.put("toVisible","true");
        deviceDriver.executeScript("mobile: swipe", map);
//        AutoWait.elementWait(element);
//        ((JavascriptExecutor) driver).executeScript("mobile: swipe",ImmutableMap.of("elementId",((RemoteWebElement)element).getId()),"direction",direction,"percent",3.0);
    }
    /**
     * Swipe to an element on an iOS device.
     *
     * @param element   The element to swipe to.
     * @param direction The direction of the swipe.
     */
    public static void scrollToElementIOS(By element,String direction){
        AutoWait.elementWait(element);
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: scroll",ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"direction",direction,"percent",3.0);
    }
    /**
     * Scroll to an element on an iOS device.
     *
     * @param element   The element to scroll to.
     * @param direction The direction of the scroll.
     */
    public static void scrollToElementIOS(WebElement element,String direction){
        AutoWait.elementWait(element);
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: scroll",ImmutableMap.of("elementId",((RemoteWebElement)element).getId()),"direction",direction,"percent",3.0);
    }
    public static Boolean canScrollByPositionIOS(String direction){
        return (Boolean)  ((JavascriptExecutor) deviceDriver).executeScript("mobile: scroll", ImmutableMap.of("left",100,"top",100,"width",200,"height",200,"direction",direction,"percent",3.0));
    }

// Methods for general dragging and dropping

    /**
     * Drag and drop an element to another element.
     *
     * @param sourceElement      The element to drag.
     * @param destinationElement The element to drop onto.
     */
    public static void dragAndDropByElement(By sourceElement, By destinationElement){
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",((RemoteWebElement)getElement(sourceElement)).getId()),"endX",ElementInfo.getXPosition(destinationElement),"getY",ElementInfo.getYPosition(destinationElement));
    }

    /**
     * Perform a zoom in gesture on the mobile device.
     */
    public static void zoomPint(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: pinchOpenGesture", ImmutableMap.of(
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
    /**
     * Drag and drop by coordinates on the mobile device.
     *
     * @param startX The starting X coordinate.
     * @param startY The starting Y coordinate.
     * @param endX   The ending X coordinate.
     * @param endY   The ending Y coordinate.
     */
    public static void dragAndDropByCoordinate(int startX, int startY,int endX,int endY){
        PointerInput finger = new PointerInput(PointerInput.Kind.MOUSE, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        deviceDriver.perform(Arrays.asList(dragNDrop));



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
    /**
     * Swipe down to an element on the screen by using its locator.
     *
     * @param element The element to swipe down to.
     */
    public static void swipeDownByParams(By element){
        AutoWait.elementWait(element);
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("velocity", 2500);
        params.put("element", ((RemoteWebElement) getElement(element)).getId());
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: swipe", params);
    }
    /**
     * Swipe down to an element on the screen by using the element itself.
     *
     * @param element The element to swipe down to.
     */
    public static void swipeDownByParams(WebElement element){
        AutoWait.elementWait(element);
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("velocity", 2500);
        params.put("element", ((RemoteWebElement) element).getId());
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: swipe", params);
    }
    /**
     * Perform a long press gesture on an Android device.
     *
     * @param element The element to perform the long press on.
     */
    public static void longPressAndroid(By element){
        AutoWait.buttonAutoWait(element);
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: longClickGesture" , ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"duration",2000);
    }
    /**
     * Perform a long press gesture on an iOS device.
     *
     * @param element The element to perform the long press on.
     */
    public static void longPressIOS(By element){
        AutoWait.buttonAutoWait(element);
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: touchAndHold" , ImmutableMap.of("elementId",((RemoteWebElement)getElement(element)).getId()),"duration",2000);
    }
    /**
     * Perform a long press gesture on an iOS device at a specific point.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
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
    /**
     * Tap and hold at a specific point on the screen.
     *
     * @param point The point to tap and hold.
     */
    public static void tapAndHoldAtPoint(Point point) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofSeconds(3)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        deviceDriver.perform(ImmutableList.of(tap));
    }
    /**
     * Tap and hold at a specific element on the screen for a specified duration.
     *
     * @param locator           The locator of the element to tap and hold.
     * @param durationInSeconds The duration for which to tap and hold (in seconds).
     */
    public static void tapAndHoldAtPoint(By locator, int durationInSeconds) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), ElementInfo.getXLocation(locator), ElementInfo.getYLocation(locator)));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofSeconds(durationInSeconds)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        deviceDriver.perform(ImmutableList.of(tap));
    }
    /**
     * Perform a pull-to-refresh action on the mobile device.
     */
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
        ((JavascriptExecutor) deviceDriver).executeScript("mobile: swipe", params);
    }
    public static void mobileCommandScrollDown(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        deviceDriver.executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void mobileCommandScrollUp(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        deviceDriver.executeScript("mobile: scroll", ImmutableMap.of("direction", "up"));
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void tapInCenter(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Button.tapInCenterCoordinate((int)(deviceDriver.manage().window().getSize().width * 0.5),(int)(deviceDriver.manage().window().getSize().height * 0.5));
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


    /**
     * Performs a swipe up gesture on an Android device using Selenium WebDriver.
     *
     * This method calculates the dimensions of the device's screen and performs a swipe gesture
     * from the bottom to the middle of the screen. The swipe gesture is created using the
     * `PointerInput` class to simulate touch actions.
     *
     * @throws Exception if an error occurs during the swipe action
     */
    public static void swipeUpAndroid() {
        try {
            // Get the width and height of the device screen
            int width = deviceDriver.manage().window().getSize().getWidth();
            int height = deviceDriver.manage().window().getSize().getHeight();

            // Calculate start and end points for the swipe gesture
            int startX = width / 2, endX = width / 2, startY = height - 1, endY = height / 2;

            // Create a PointerInput instance for touch actions
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            // Create a Sequence to define the swipe gesture
            Sequence dragNDrop = new Sequence(finger, 1);

            // Add actions to the sequence: move to start point, press down, move to end point, and release
            dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                    PointerInput.Origin.viewport(), startX, startY));
            dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                    PointerInput.Origin.viewport(), endX, endY));
            dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Perform the swipe gesture using the device driver
            deviceDriver.perform(Arrays.asList(dragNDrop));
        } catch (Exception e) {

        }
    }

    /**
     * Performs a swipe left gesture on an Android device using Selenium WebDriver.
     *
     * This method calculates the dimensions of the device's screen and performs a swipe gesture
     * from the right edge to the middle of the screen. The swipe gesture is created using the
     * `PointerInput` class to simulate touch actions.
     *
     * @throws Exception if an error occurs during the swipe action
     */
    public static void swipeLeftAndroid() {
        try {
            // Get the width and height of the device screen
            int width = deviceDriver.manage().window().getSize().getWidth();
            int height = deviceDriver.manage().window().getSize().getHeight();

            // Calculate start and end points for the swipe gesture (Left Swipe)
            int startX = width - 1, endX = width / 2, startY = height / 2, endY = height / 2;

            // Create a PointerInput instance for touch actions
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

            // Create a Sequence to define the swipe gesture
            Sequence dragNDrop = new Sequence(finger, 1);

            // Add actions to the sequence: move to start point, press down, move to end point, and release
            dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                    PointerInput.Origin.viewport(), startX, startY));
            dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                    PointerInput.Origin.viewport(), endX, endY));
            dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Perform the swipe gesture using the device driver
            deviceDriver.perform(Arrays.asList(dragNDrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a horizontal swipe from right to left across the specified element on an Android device.
     *
     * Steps:
     * - Calculates the start and end coordinates based on the element's position and size.
     * - Uses low-level PointerInput to simulate a swipe gesture.
     * - Swipes from the right edge of the element to the left side of the screen.
     *
     * @param element The WebElement to swipe across.
     */
    public static void swipeLeftByElementAndroid(WebElement element) {
        try {
            // Get the element's location and size
            Point elementLocation = element.getLocation();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            // Calculate swipe start and end coordinates
            int startX = elementLocation.getX() + width - 1;  // Start from the right edge of the element
            int endX = 0;  // End at the left edge of the screen
            int startY = elementLocation.getY() + height / 2;  // Vertical center of the element
            int endY = startY;  // Maintain same Y for horizontal swipe

            // Create a PointerInput object to simulate finger touch
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            // Add swipe actions
            swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Perform the swipe
            CommonHelper.deviceDriver.perform(Arrays.asList(swipe));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a swipe up gesture from the vertical center of the Android screen.
     *
     * Steps:
     * - Determines screen dimensions.
     * - Calculates a vertical swipe starting from the middle to 20% of screen height.
     * - Executes the swipe using low-level PointerInput actions.
     */
    public static void swipeUpFromMiddleOfScreenAndroid() {
        try {
            // Get screen dimensions
            int width = CommonHelper.deviceDriver.manage().window().getSize().getWidth();
            int height = CommonHelper.deviceDriver.manage().window().getSize().getHeight();

            // Set horizontal position at screen center
            int startX = width / 2;
            int endX = startX;

            // Swipe from middle of screen up to 20% height
            int startY = height / 2;
            int endY = (int) (height * 0.2);

            // Create a touch input sequence to simulate swipe
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            // Define the swipe gesture steps
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY)); // Move to start
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())); // Finger down
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), endX, endY)); // Move to end point
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Finger up

            // Perform the swipe on the device
            CommonHelper.deviceDriver.perform(Arrays.asList(swipe));
        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions
        }
    }

}
