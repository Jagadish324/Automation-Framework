package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class IosDriver extends CommonHelper {
    private static Logger log = LogManager.getLogger(IosDriver.class.getName());
    /**
     * Set up and initialize the iOS driver for running tests on an iOS device.
     */
    public static void iosDriver() {
        String appLocation = System.getProperty("user.dir") + CONSTANT.APP_LOCATION;
        XCUITestOptions xcuiTestOptions = new XCUITestOptions();
        xcuiTestOptions.setDeviceName(CONSTANT.DEVICE_NAME);
        xcuiTestOptions.setApp(appLocation);
        xcuiTestOptions.setPlatformVersion(CONSTANT.PLATFORM_VERSION);
        xcuiTestOptions.setWdaLaunchTimeout(Duration.ofSeconds(CONSTANT.EXPLICIT_WAIT));

//        for chrome
//        xcuiTestOptions.setChromedriverExecutable("");
        xcuiTestOptions.setCapability("browserName", "chrome");
        try {
            IOSDriver androidDriver = new IOSDriver(new URL("http://127.0.0.1:4723"), xcuiTestOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Launch the app with the specified bundle ID.
     *
     * @param bundleId The bundle ID of the app to launch.
     */
    public static void launchApp(String bundleId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("bundleId", bundleId);
        deviceDriver.executeScript("mobile:launchApp", params);
    }
    /**
     * Switch the Wi-Fi on/off on an iOS device.
     */
    public static void switchWifiOnOff() {
        putAppInBackGroundiOS();
        ElementWait.hardWait(4000);
        int i = CONSTANT.AUTOWAIT_TIME;
        CONSTANT.AUTOWAIT_TIME = 1;
        By batterLogo = AppiumBy.iOSNsPredicateString("value =='Not charging' or value =='Charging'");
        int x = ElementInfo.getXLocation(batterLogo);
        int y = ElementInfo.getYLocation(batterLogo);
        GestureAction.dragAndDropByCoordinate(x, y,x,y+200);
        CONSTANT.AUTOWAIT_TIME = i;
        ElementWait.hardWait(1000);
        By wifiButton = AppiumBy.iOSNsPredicateString("name == 'wifi-button'");
        Button.tapElement(wifiButton);
        GestureAction.tapInCenter();
    }
    /**
     * Put the app in the background on an iOS device.
     */
    public static void putAppInBackGroundiOS(){
        deviceDriver.executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
    }
}
