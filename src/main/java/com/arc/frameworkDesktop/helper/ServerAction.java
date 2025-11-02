package com.arc.frameworkDesktop.helper;

import com.arc.frameworkDesktop.utility.CONSTANT;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ServerAction extends CommonHelper {
    private static Logger log = LogManager.getLogger(ServerAction.class.getName());

    private static AppiumDriverLocalService appiumServiceBuilder;
    public static void startServer(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        appiumServiceBuilder = new AppiumServiceBuilder().withAppiumJS(new File(CONSTANT.MAIN_JS_FILE_PATH)).withIPAddress(CONSTANT.HOST_NAME).usingPort(Integer.parseInt(CONSTANT.PORT_NUMBER)).withArgument(()->"--base-path", "/wd/hub").build();
        appiumServiceBuilder.start();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void terminateAppiumService(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        appiumServiceBuilder.stop();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void getServerCapability(){
//        Map<String, Object> caps = driver.getSessionDetails();
    }
    public static void getServerStatus(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        desktopDriver.getStatus();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    public static void terminateDriver(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        desktopDriver.quit();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
