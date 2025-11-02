package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
/**
 * Helper class for handling automatic waiting and synchronization operations.
 */
public class AutoWait {
    private static Logger log = LogManager.getLogger(AutoWait.class.getName());
    /**
     * Waits for a button identified by the given locator to be visible and clickable.
     *
     * @param element The locator of the button.
     */
    public static void buttonAutoWait(By element) {
//        waitForStabilityAndClickable(element);
        ElementWait.customVisibilityWait(element,"visible");
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
    /**
     * Waits for a button identified by the given WebElement to be visible and clickable.
     *
     * @param element The WebElement of the button.
     */
    public static void buttonAutoWait(WebElement element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ElementWait.customVisibilityWait(element,"visible");
    }
    /**
     * Waits for an element identified by the given locator to be visible.
     *
     * @param element The locator of the element.
     */
    public static void elementWait(By element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ElementWait.customVisibilityWait(element,"visible");
    }
    /**
     * Waits for an element identified by the given WebElement to be visible.
     *
     * @param element The WebElement of the element.
     */
    public static void elementWait(WebElement element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ElementWait.customVisibilityWait(element,"visible");
    }
    /**
     * Waits for an alert to be present.
     */

    public static void alertWait() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ElementWait.hardWait(500);
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            try {
                ElementWait.waitForAlert(1);
                break;
            } catch (Exception e) {
                System.out.println("Alert not visible retrying" + i);
            }
        }
    }
    /**
     * Waits for the presence and visibility of an element identified by the given locator.
     *
     * @param locator The locator of the element.
     */
    public static void waitForStability(By locator) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        boolean flagPresence = true;
        boolean flagVisibility = false;
//        for (int i = 1; i <= CONSTANT.STEP_RETRY; i++) {
//            try {
//                ElementWait.waitForPresence(locator, CONSTANT.AUTOWAIT_TIME);
//                flagPresence = true;
//                System.out.println("Presence  " + flagPresence);
//                break;
//            } catch (Exception e) {
//                System.out.println("Presence retry " + i);
//            }
//        }
        if (flagPresence) {
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    flagVisibility = waitForVisibility(locator);
                    System.out.println("Visibility  " + flagVisibility);
                    if (flagVisibility)
                        break;
                } catch (Exception e) {
                    System.out.println("Visibility retry " + i);
                }
            }
        }

    }
    /**
     * Waits for the presence and visibility of an element identified by the given locator.
     *
     * @param locator The WebElement of the element.
     */
    public static void waitForStability(WebElement locator) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() + "for " + locator);
        boolean flag = false;
        boolean flagVisibility = false;
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            try {
                flagVisibility = waitForVisibility(locator);
                System.out.println("Presence  " + flagVisibility);
                if (flagVisibility)
                    break;
            } catch (Exception e) {
                System.out.println("Presence retry " + i);
            }
        }

    }
    /**
     * Waits for the presence and visibility of an element identified by the given WebElement.
     * Also waits for the element to be clickable.
     *
     * @param locator The By of the element.
     */
    public static void waitForStabilityAndClickable(By locator) {
        waitForStability(locator);
//        for (int i = 1; i <= CONSTANT.STEP_RETRY; i++) {
//            try {
//                ElementWait.waitForClickable(locator, CONSTANT.AUTOWAIT_TIME);
//                System.out.println("Element Clickable ");
//                break;
//            } catch (Exception e) {
//                System.out.println("Clickable retry " + i);
//            }
//        }
    }
    /**
     * Waits for the presence and visibility of an element identified by the given WebElement.
     * Also waits for the element to be clickable.
     *
     * @param locator The WebElement of the element.
     */
    public static void waitForStabilityAndClickable(WebElement locator) {
        waitForStability(locator);
//        for (int i = 1; i <= CONSTANT.STEP_RETRY; i++) {
//            try {
//                ElementWait.waitForClickable(locator, CONSTANT.AUTOWAIT_TIME);
//                System.out.println("Element Clickable ");
//                break;
//            } catch (Exception e) {
//                System.out.println("Clickable retry " + i);
//            }
//        }
    }
    /**
     * Waits for the visibility of an element identified by the given locator.
     *
     * @param locator The locator of the element.
     * @return True if the element is visible, false otherwise.
     */
    public static boolean waitForVisibility(By locator) {
        boolean flag = false;
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            flag = Boolean.parseBoolean(ElementInfo.getAttribute(locator, "visible"));
            if (flag)
                break;
        }
        return flag;
    }
    /**
     * Waits for the visibility of an element identified by the given WebElement.
     *
     * @param locator The WebElement of the element.
     * @return True if the element is visible, false otherwise.
     */
    public static boolean waitForVisibility(WebElement locator) {
        boolean flag = false;
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            flag = Boolean.parseBoolean(ElementInfo.getAttribute(locator, "visible"));
            if (flag)
                break;
        }
        return flag;
    }

}
