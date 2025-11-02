package com.arc.frameworkDevice.helper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigate extends CommonHelper{
    private static Logger log = LogManager.getLogger(Navigate.class.getName());
    /**
     * Simulates pressing the back button on an Android device.
     */
    public static void goBackAndroid(){
        ((AndroidDriver) deviceDriver).pressKey(new KeyEvent(AndroidKey.BACK));
    }
    /**
     * Simulates pressing the home button on an Android device.
     */
    public static void pressHomeAndroid(){
        ((AndroidDriver) deviceDriver).pressKey(new KeyEvent(AndroidKey.HOME));
    }
    /**
     * Simulates pressing the enter button on an Android device.
     */
    public static void pressEnterAndroid(){
        ((AndroidDriver) deviceDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
    /**
     * Simulates navigating back in the app on an iOS device.
     */
    public static void goBackIos(){
        deviceDriver.navigate().back();
    }

}
