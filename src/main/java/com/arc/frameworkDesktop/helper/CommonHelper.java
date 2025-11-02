package com.arc.frameworkDesktop.helper;

import com.arc.frameworkDesktop.utility.CONSTANT;
import com.arc.helper.PropertyFileReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonHelper {
    private static Logger log = LogManager.getLogger(CommonHelper.class.getName());
    public static WindowsDriver desktopDriver;

    public CommonHelper() {

    }
//    public CommonHelper(String path){
//        PropertyFileReader propertyFileReader = new PropertyFileReader(path);
//        CONSTANT.PORT_NUMBER = propertyFileReader.getData("portNumber");
//        CONSTANT.REGION = propertyFileReader.getData("region");
//        CONSTANT.AUTOMATION_NAME = propertyFileReader.getData("automationName");
//        CONSTANT.HOST_NAME = propertyFileReader.getData("hostName");
//        CONSTANT.DEVICE_NAME = propertyFileReader.getData("deviceName");
//        CONSTANT.DEVICE_TYPE = propertyFileReader.getData("deviceType");
//        CONSTANT.BUNDLE_ID = propertyFileReader.getData("bundleId");
//        CONSTANT.ORIENTATION = propertyFileReader.getData("orientation");
//        CONSTANT.UDID = propertyFileReader.getData("udid");
//        CONSTANT.PLATFORM_VERSION = propertyFileReader.getData("platformVersion");
//        CONSTANT.PLATFORM_NAME = propertyFileReader.getData("platformName") ;
//        CONSTANT.APP_LOCATION = propertyFileReader.getData("appLocation");
//        CONSTANT.IMPLICIT_WAIT=  Integer.parseInt(propertyFileReader.getData("implicitWait"));
//        CONSTANT.EXPLICIT_WAIT =  Integer.parseInt(propertyFileReader.getData("explicitWait"));
//        CONSTANT.DEFAULT_WAIT =  Integer.parseInt(propertyFileReader.getData("defaultWait"));
//        CONSTANT.AUTOWAIT_TIME =  Integer.parseInt(propertyFileReader.getData("autoWait"));
//        CONSTANT.STEP_RETRY = Integer.parseInt(propertyFileReader.getData("stepRetry"));
//        CONSTANT.MAIN_JS_FILE_PATH = propertyFileReader.getData("main.js");
//    }

    public CommonHelper(WindowsDriver desktopDriver) {
        CommonHelper.desktopDriver = desktopDriver;
    }

    public static WebElement getElement(By locator) {
        return desktopDriver.findElement(locator);
    }

    public static List<WebElement> getElements(By locator) {
        AutoWait.elementWait(locator);
        return desktopDriver.findElements(locator);
    }

    public static void reopenApp() {

    }

    public static void beforeMethod(String methodName, String elementName) {
//        log.info();
    }

    public static void afterMethod() {

    }

}
