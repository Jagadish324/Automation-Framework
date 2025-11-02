package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for managing application configuration and operations.
 */
public class AppConfig extends CommonHelper{
    private static Logger log = LogManager.getLogger(AppConfig.class.getName());

    /**
     * Checks if the application is installed on the device.
     *
     * @return True if the application is installed, false otherwise.
     */
    public static boolean isAppInstalled() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", CONSTANT.BUNDLE_ID);
        JavascriptExecutor js = (JavascriptExecutor) deviceDriver;
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return (Boolean) js.executeScript("mobile: isAppInstalled", params);
    }

    /**
     * Installs the application on the device.
     */
    public static void installApp() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        String appLocation = System.getProperty("user.dir") + CONSTANT.APP_LOCATION;
        Map<String, Object> params = new HashMap<>();
        params.put("app", appLocation);
        JavascriptExecutor js = (JavascriptExecutor) deviceDriver;
        js.executeScript("mobile: installApp", params);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    /**
     * Removes the specified application from the device.
     *
     * @param bundleId The bundle ID of the application to remove.
     */
    public static void removeApp(String bundleId) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        JavascriptExecutor js = (JavascriptExecutor) deviceDriver;
        js.executeScript("mobile: removeApp", params);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    // Methods for iOS specific operations

    /**
     * Closes the application on iOS.
     */
    public static void closeAppiOS(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ((IOSDriver) deviceDriver).terminateApp(CONSTANT.BUNDLE_ID);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    /**
     * Opens the specified application on iOS.
     *
     * @param bundleId The bundle ID of the application to open.
     */
    public static void openAppiOS(String bundleId){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ((IOSDriver) deviceDriver).activateApp(bundleId);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    // Methods for Android specific operations

    /**
     * Closes the application on Android.
     */
    public static void closeAppAndroid(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ((AndroidDriver) deviceDriver).terminateApp(CONSTANT.BUNDLE_ID);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    /**
     * Opens the specified application on Android.
     *
     * @param bundleId The bundle ID of the application to open.
     */
    public static void openAppAndroid(String bundleId){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ((AndroidDriver) deviceDriver).activateApp(bundleId);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void putAppInBackground(int timeInSeconds){
        deviceDriver.executeScript("mobile: backgroundApp", Map.ofEntries(Map.entry("seconds", timeInSeconds)));
    }
//
}
