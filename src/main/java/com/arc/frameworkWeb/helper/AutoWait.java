package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.arc.frameworkWeb.utility.CONSTANT;

public class AutoWait extends CommonHelper {
    private static Logger log= LogManager.getLogger(AutoWait.class.getName());
    public static void autoWait(By locator) {
        waitForStability(locator);
    }

    public static void autoWait(WebElement locator) {
        waitForStability(locator);
    }

    public static void autoWaitButton(By locator) {
        waitForStabilityAndClickable(locator);
    }

    public static void autoWaitButton(WebElement locator) {
        waitForStabilityAndClickable(locator);
    }

    public static void autoWaitWindowOpen() {
        try {
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                ExplicitWait.waitUntilWindowOpen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void autoWaitAlert() {
        try {
            boolean flag = false;
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                if (flag == false) {
                    flag = Alerts.isAlertPresent();
                    ExplicitWait.hardWait(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean waitForStability(By locator) {
        boolean flag = false;
        boolean flagVisibility = false;
//        for (int i = 1; i <= CONSTANT.STEP_RETRY; i++) {
//            try {
//                ExplicitWait.waitForPresence(locator, CONSTANT.AUTOWAIT_TIME);
//                flagPresence = true;
//                System.out.println("Presence  " + flagPresence);
//                break;
//            } catch (Exception e) {
//                System.out.println("Presence retry " + i);
//            }
//        }

            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForVisibility(locator, CONSTANT.AUTOWAIT_TIME);
                    flagVisibility = true;
                    log.info("Element visible for " + locator + " retry " + i + " flagVisibility");
                    break;
                } catch (Exception e) {
                    log.info("Element visible for " + locator + " retry " + i);
                }
            }

        if(flagVisibility) {
            try {
                flag = getElement(locator).isDisplayed();
                log.info("Element displayed for " + locator);
            } catch (Exception e) {
                log.info("Element not displayed for " + locator);
            }
        }
        if (flag) {
            JavaScriptExecutor.scrollIntoView(locator);
        }
        return flag;
    }

    public static boolean waitForStability(WebElement locator) {
        boolean flag = false;
        boolean flagVisibility = false;
        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
            try {
                ExplicitWait.waitForVisibility(locator, CONSTANT.AUTOWAIT_TIME);
                flagVisibility = true;
                log.info("Element Presence for " + locator +" is- "+ flagVisibility);
//                System.out.println("Presence  " + flagVisibility);
                break;
            } catch (Exception e) {
                log.info("Element Presence for " + locator +" retry is " + i);

            }
        }
        if(flagVisibility) {
            try {
                flag = locator.isDisplayed();
                log.info("Element displayed for " + locator);
            } catch (Exception e) {
                log.info("Element not displayed for " + locator);
            }
        }
        if (flag) {
            JavaScriptExecutor.scrollIntoView(locator);
        }
        return flag;
    }

    public static void waitForStabilityAndClickable(By locator) {
        boolean flag = waitForStability(locator);
        if(flag) {
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForElementsToBeClickable(locator, CONSTANT.AUTOWAIT_TIME);
                    log.info("Element clickable for " + locator);
                    break;
                } catch (Exception e) {
                    log.info("Element clickable retry for " + locator + i);
                }
            }
        }
    }

    public static void waitForStabilityAndClickable(WebElement locator) {
        boolean flag = waitForStability(locator);
        if(flag) {
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    ExplicitWait.waitForElementsToBeClickable(locator, CONSTANT.AUTOWAIT_TIME);
                    log.info("Element clickable for " + locator);
                    break;
                } catch (Exception e) {
                    log.info("Element clickable retry for " + locator + i);
                }
            }
        }
    }
}
