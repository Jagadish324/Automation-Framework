package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;
import com.arc.helper.PropertyFileReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonHelper {
    private static Logger log = LogManager.getLogger(CommonHelper.class.getName());
    public static AppiumDriver deviceDriver;
    public CommonHelper(){

    }
    public CommonHelper(String path){
        PropertyFileReader propertyFileReader = new PropertyFileReader(path);
        CONSTANT.PORT_NUMBER = propertyFileReader.getData("portNumber");
        CONSTANT.REGION = propertyFileReader.getData("region");
        CONSTANT.AUTOMATION_NAME = propertyFileReader.getData("automationName");
        CONSTANT.HOST_NAME = propertyFileReader.getData("hostName");
        CONSTANT.DEVICE_NAME = propertyFileReader.getData("deviceName");
        CONSTANT.DEVICE_TYPE = propertyFileReader.getData("deviceType");
        CONSTANT.BUNDLE_ID = propertyFileReader.getData("bundleId");
        CONSTANT.ORIENTATION = propertyFileReader.getData("orientation");
        CONSTANT.UDID = propertyFileReader.getData("udid");
        CONSTANT.PLATFORM_VERSION = propertyFileReader.getData("platformVersion");
        CONSTANT.PLATFORM_NAME = propertyFileReader.getData("platformName") ;
        CONSTANT.APP_LOCATION = propertyFileReader.getData("appLocation");
        CONSTANT.IMPLICIT_WAIT=  Integer.parseInt(propertyFileReader.getData("implicitWait"));
        CONSTANT.EXPLICIT_WAIT =  Integer.parseInt(propertyFileReader.getData("explicitWait"));
        CONSTANT.DEFAULT_WAIT =  Integer.parseInt(propertyFileReader.getData("defaultWait"));
        CONSTANT.AUTOWAIT_TIME =  Integer.parseInt(propertyFileReader.getData("autoWait"));
        CONSTANT.STEP_RETRY = Integer.parseInt(propertyFileReader.getData("stepRetry"));
        CONSTANT.MAIN_JS_FILE_PATH = propertyFileReader.getData("main.js");
    }
    /**
     * Placeholder test method for experimentation with TapOptions, ElementOption, etc.
     */
    public void testMethod(){
        TapOptions tapOptions = new TapOptions();
        ElementOption elementOption = new ElementOption();
//        ActionOptions actionOptions = new ActionOptions(driver);
//        driver.findElementByImage(templateImage);

    }
    /**
     * Constructor for CommonHelper class.
     * Sets the deviceDriver for CommonHelper class.
     * @param deviceDriver The AppiumDriver instance to be set.
     */
    public CommonHelper(AppiumDriver deviceDriver){
        CommonHelper.deviceDriver = deviceDriver;
    }
    /**
     * Returns a single WebElement found by the given locator.
     * @param locator The By locator used to find the element.
     * @return The WebElement found.
     */
    public static WebElement getElement(By locator) {
        return deviceDriver.findElement(locator);
    }
    /**
     * Returns a list of WebElements found by the given locator after waiting for the element to be present.
     * @param locator The By locator used to find the elements.
     * @return The list of WebElements found.
     */
    public static List<WebElement> getElements(By locator) {
        AutoWait.elementWait(locator);
        return deviceDriver.findElements(locator);
    }
    /**
     * Reopens the app.
     */
    public static void reopenApp(){

    }
    /**
     * Placeholder method for actions to be performed before a test method.
     * @param methodName The name of the test method.
     * @param elementName The name of the element.
     */
    public static void beforeMethod(String methodName, String elementName){
//        log.info();
    }
    /**
     * Placeholder method for actions to be performed after a test method.
     */
    public static void afterMethod(){

    }

}
