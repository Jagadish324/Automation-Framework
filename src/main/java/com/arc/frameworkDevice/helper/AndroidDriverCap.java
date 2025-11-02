package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverCap extends CommonHelper{

    //App Package
    //App Activity for mac               adb shell dumpsys window | grep -E 'mCurrentFocus'
//    window     adb shell dumpsys window | find -E 'mCurrentFocus'
    private static Logger log = LogManager.getLogger(AndroidDriverCap.class.getName());
    /**
     * androidDriver is implemented to launch the app or chrome browser based on need
     *
     */
    public static void androidDriver(){
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        String appLocation = System.getProperty("user.dir") + CONSTANT.APP_LOCATION;
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setDeviceName(CONSTANT.DEVICE_NAME);
        uiAutomator2Options.setApp(appLocation);
//        for chrome
        uiAutomator2Options.setChromedriverExecutable("");
        uiAutomator2Options.setCapability("browserName","chrome");
        try {
            AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"),uiAutomator2Options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * androidScrollToElement is implemented to scroll the at particular position based on Text value
     */
    public static void androidScrollToElement(String textValue){

        deviceDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+textValue+"\"))"));
    }
    /**
     * navigateBack is implemented to scroll the at particular position based on Text value
     */
    public static void navigateBack() {
        deviceDriver.navigate().back();
    }

}
