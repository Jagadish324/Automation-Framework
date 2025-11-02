package com.arc.frameworkDevice.helper;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class KeyBoard extends CommonHelper{


    /**
     * Hides the device keyboard.
     */
    public void hideDeviceKeyboard() {

    }

    /**
     * Presses the Enter button on the device keyboard.
     */
    public static void pressEnterButton() {
        ((AndroidDriver) deviceDriver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}
