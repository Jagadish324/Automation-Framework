package com.arc.frameworkDevice.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;

import java.util.HashMap;
import java.util.List;

public class Alerts extends CommonHelper {
    private static Logger log = LogManager.getLogger(Alerts.class.getName());

    /**
     * method to Accept Alert if alert is present
     */
    public static void acceptAlert() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());

        AutoWait.alertWait();
        deviceDriver.switchTo().alert().accept();
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    /**
     * method to Dismiss Alert if alert is present
     */
    public static void dismissAlert() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());

        AutoWait.alertWait();
        deviceDriver.switchTo().alert().dismiss();
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    /**
     * method to get message test of alert
     *
     * @return message text which is displayed
     */
    public static String getAlertText() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        AutoWait.alertWait();
        Alert alert = deviceDriver.switchTo().alert();
        String alertText = alert.getText();
        log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return alertText;
    }

    /**
     * method to verify if alert is present
     *
     * @return returns true if alert is present else false
     */
    public static boolean isAlertPresent() {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName());
        AutoWait.alertWait();
        HashMap args = new HashMap<>();
        args.put("action", "getButtons");
        List buttons = (List) deviceDriver.executeScript("mobile: alert", args);
        if(buttons.size()>0){
        deviceDriver.switchTo().alert();
            log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
            return true;
        }
        else {
            log.info("End " + Thread.currentThread().getStackTrace()[1].getMethodName());
            return false;
        }

    }

}
