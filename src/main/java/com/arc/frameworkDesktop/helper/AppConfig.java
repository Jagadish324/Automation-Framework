package com.arc.frameworkDesktop.helper;

import com.arc.frameworkDesktop.utility.CONSTANT;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class AppConfig extends CommonHelper {
    private static Logger log = LogManager.getLogger(AppConfig.class.getName());

    public static boolean isAppInstalled() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", CONSTANT.BUNDLE_ID);
        JavascriptExecutor js = (JavascriptExecutor) desktopDriver;
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return (Boolean) js.executeScript("mobile: isAppInstalled", params);
    }
    public static void installApp() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        String appLocation = System.getProperty("user.dir") + CONSTANT.APP_LOCATION;
        Map<String, Object> params = new HashMap<>();
        params.put("app", appLocation);
        JavascriptExecutor js = (JavascriptExecutor) desktopDriver;
        js.executeScript("mobile: installApp", params);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public static void removeApp(String bundleId) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        JavascriptExecutor js = (JavascriptExecutor) desktopDriver;
        js.executeScript("mobile: removeApp", params);
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

//    public static void openAppiOS(String bundleId){
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
//        ((IOSDriver) desktopDriver).activateApp(bundleId);
//        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
//    }
//    public static void openAppAndroid(String bundleId){
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
//        ((AndroidDriver) desktopDriver).activateApp(bundleId);
//        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
//    }


}
