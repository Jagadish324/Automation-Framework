package com.arc.frameworkDevice.helper;

import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;

public class Orientation extends CommonHelper{
    private static Logger log = LogManager.getLogger(Orientation.class.getName());
    /**
     * Changes the orientation of the device to landscape mode.
     * Note: This method is specific to Android devices.
     */
    public static void changeOrientation(){
        DeviceRotation deviceRotation = new DeviceRotation(0,0,90);
        ((AndroidDriver) deviceDriver).rotate(ScreenOrientation.LANDSCAPE);

    }
}
