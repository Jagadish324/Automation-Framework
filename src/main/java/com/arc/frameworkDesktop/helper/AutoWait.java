package com.arc.frameworkDesktop.helper;

import com.arc.frameworkDesktop.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AutoWait {
    private static Logger log = LogManager.getLogger(AutoWait.class.getName());

    public static void buttonAutoWait(By element) {
//        waitForStabilityAndClickable(element);
//        ElementWait.customVisibilityWait(element,"visible");
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public static void buttonAutoWait(WebElement element) {
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
//        ElementWait.customVisibilityWait(element,"visible");
    }

    public static void elementWait(By element) {
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
//        ElementWait.customVisibilityWait(element,"visible");
    }

    public static void elementWait(WebElement element) {
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
//        ElementWait.customVisibilityWait(element,"visible");
    }



    public static void alertWait() {
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
//        ElementWait.hardWait(500);
//        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
//            try {
//                ElementWait.waitForAlert(1);
//                break;
//            } catch (Exception e) {
//                System.out.println("Alert not visible retrying" + i);
//            }
//        }
    }

    public static void waitForStability(By locator) {
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
//        boolean flagPresence = true;
//        boolean flagVisibility = false;
////        for (int i = 1; i <= CONSTANT.STEP_RETRY; i++) {
////            try {
////                ElementWait.waitForPresence(locator, CONSTANT.AUTOWAIT_TIME);
////                flagPresence = true;
////                System.out.println("Presence  " + flagPresence);
////                break;
////            } catch (Exception e) {
////                System.out.println("Presence retry " + i);
////            }
////        }
//        if (flagPresence) {
//            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
//                try {
//                    flagVisibility = waitForVisibility(locator);
//                    System.out.println("Visibility  " + flagVisibility);
//                    if (flagVisibility)
//                        break;
//                } catch (Exception e) {
//                    System.out.println("Visibility retry " + i);
//                }
//            }
//        }

    }

    public static void waitForStability(WebElement locator) {
//        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() + "for " + locator);
//        boolean flag = false;
//        boolean flagVisibility = false;
//        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
//            try {
//                flagVisibility = waitForVisibility(locator);
//                System.out.println("Presence  " + flagVisibility);
//                if (flagVisibility)
//                    break;
//            } catch (Exception e) {
//                System.out.println("Presence retry " + i);
//            }
//        }

    }

    public static void waitForStabilityAndClickable(By locator) {
//        waitForStability(locator);
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

    public static void waitForStabilityAndClickable(WebElement locator) {
//        waitForStability(locator);
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

//    public static boolean waitForVisibility(By locator) {
//        boolean flag = false;
//        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
//            flag = Boolean.parseBoolean(ElementInfo.getAttribute(locator, "visible"));
//            if (flag)
//                break;
//        }
//        return flag;
//    }

//    public static boolean waitForVisibility(WebElement locator) {
//        boolean flag = false;
//        for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
//            flag = Boolean.parseBoolean(ElementInfo.getAttribute(locator, "visible"));
//            if (flag)
//                break;
//        }
//        return flag;
//    }

}
