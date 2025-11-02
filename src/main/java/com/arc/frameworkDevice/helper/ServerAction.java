package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ServerAction extends CommonHelper{
    private static Logger log = LogManager.getLogger(ServerAction.class.getName());

    private static AppiumDriverLocalService appiumServiceBuilder;
    /**
     * Starts the Appium server.
     */
    public static void startServer(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        appiumServiceBuilder = new AppiumServiceBuilder().withAppiumJS(new File(CONSTANT.MAIN_JS_FILE_PATH)).withIPAddress(CONSTANT.HOST_NAME).usingPort(Integer.parseInt(CONSTANT.PORT_NUMBER)).withArgument(()->"--base-path", "/wd/hub").build();
        appiumServiceBuilder.start();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    /**
     * Stops the Appium server.
     */
    public static void terminateAppiumService(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        appiumServiceBuilder.stop();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    /**
     * Gets the server capabilities.
     */
    public static void getServerCapability(){
//        Map<String, Object> caps = driver.getSessionDetails();
    }
    /**
     * Gets the server status.
     */
    public static void getServerStatus(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        deviceDriver.getStatus();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    /**
     * Terminates the driver.
     */
    public static void terminateDriver(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        deviceDriver.quit();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
