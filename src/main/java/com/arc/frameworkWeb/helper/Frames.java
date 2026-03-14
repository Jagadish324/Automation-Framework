package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class Frames extends CommonHelper {
    private static Logger log = LogManager.getLogger(Frames.class.getName());

    /**
     * Switches to a frame using the specified locator.
     * @param locator The locator of the frame to switch to.
     */
    public static void switchToFrame(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            getDriver().switchTo().frame(getElement(locator));
        } else {
            log.warn("Playwright does not support imperative frame switching. " +
                    "Use page.frameLocator(selector) directly to interact with elements inside frames. " +
                    "Example: getPageInstance().frameLocator(\"" + getLocator("" + locator) + "\").locator(\"selector\").click()");
        }
    }

    /**
     * Waits for a frame to switch and then switches to it.
     * @param frameLocator The locator of the frame to wait for and switch to.
     */
    public static void waitForFrameToSwitch(By frameLocator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            ExplicitWait.waitAndSwitchToFrame(frameLocator);
        } else {
            log.warn("Playwright does not support imperative frame switching. " +
                    "Use page.frameLocator(selector) directly to interact with elements inside frames. " +
                    "The frame will be available when the frameLocator resolves.");
        }
    }

    /**
     * Switches to a frame using the specified frame name or id.
     * @param value The name or id of the frame to switch to.
     */
    public static void switchToFrame(String value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().switchTo().frame(value);
        } else {
            log.warn("Playwright does not support imperative frame switching. " +
                    "Use page.frameLocator(\"[name='\" + value + \"']\") or page.frame(\"" + value + "\") " +
                    "to interact with elements inside frames.");
        }
    }

    /**
     * Switches to a frame using the frame index.
     * @param value The index of the frame to switch to.
     */
    public static void switchToFrame(int value) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().switchTo().frame(value);
        } else {
            log.warn("Playwright does not support imperative frame switching by index. " +
                    "Use page.frames().get(" + value + ") to access the frame at a specific index, " +
                    "or use page.frameLocator(selector) for element interaction.");
        }
    }

    /**
     * Switches to the default content (main document).
     */
    public static void switchToDefaultContent() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().switchTo().defaultContent();
        } else {
            // No-op for Playwright. The Page object always represents the top-level context.
            // Frame interactions are scoped via FrameLocator and do not change the Page context.
            log.info("switchToDefaultContent() is a no-op for Playwright. " +
                    "The Page object always represents the top-level context.");
        }
    }

    /**
     * Switches to the parent frame of the current frame.
     */
    public static void switchToParentFrame() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().switchTo().parentFrame();
        } else {
            // No-op for Playwright. Frame interactions are scoped via FrameLocator.
            log.info("switchToParentFrame() is a no-op for Playwright. " +
                    "Frame interactions are scoped via FrameLocator and do not change the Page context.");
        }
    }
}
