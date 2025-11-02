package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class Frames extends CommonHelper{
    private static Logger log= LogManager.getLogger(Frames.class.getName());
    /**
     * Switches to a frame using the specified locator.
     * @param locator The locator of the frame to switch to.
     */
    public static void switchToFrame(By locator){
        AutoWait.autoWait(locator);
        webDriver.switchTo().frame(getElement(locator));
    }
    /**
     * Waits for a frame to switch and then switches to it.
     * @param frameLocator The locator of the frame to wait for and switch to.
     */
    public static void waitForFrameToSwitch(By frameLocator)
    {
        ExplicitWait.waitAndSwitchToFrame(frameLocator);
    }
    /**
     * Switches to a frame using the specified frame name or id.
     * @param value The name or id of the frame to switch to.
     */
    public static void switchToFrame(String value){
        webDriver.switchTo().frame(value);
    }
    /**
     * Switches to a frame using the frame index.
     * @param value The index of the frame to switch to.
     */

    public static void switchToFrame(int value){
        webDriver.switchTo().frame(value);
    }
    /**
     * Switches to the default content (main document).
     */
    public static void switchToDefaultContent(){
        webDriver.switchTo().defaultContent();
    }
    /**
     * Switches to the parent frame of the current frame.
     */
    public static void switchToParentFrame() {
        webDriver.switchTo().parentFrame();
    }
}
