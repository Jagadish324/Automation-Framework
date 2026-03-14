package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.options.AriaRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

public class Alerts extends CommonHelper{
    private static Logger log= LogManager.getLogger(Alerts.class.getName());
    // Switch to alert
    /**
     * Gets the current alert instance depending on the testing tool (Selenium or Playwright).
     * @return The alert instance or null if not applicable.
     */
    public static Alert getAlert() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getDriver().switchTo().alert();
        }else {
            return null;
        }
    }
    /**
     * Accepts the alert if it is present, depending on the testing tool (Selenium or Playwright).
     */
    // Accept the alert
    public static void acceptAlert() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getAlert().accept();
        }else {
            getPageInstance().onDialog(Dialog::accept);
            getPageInstance().getByRole(AriaRole.BUTTON).click();
        }
    }
    /**
     * Dismisses the alert if it is present, depending on the testing tool (Selenium or Playwright).
     */
    // Dismiss the alert
    public static void dismissAlert() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getAlert().dismiss();
        }else {
            getPageInstance().onDialog(Dialog::dismiss);
            getPageInstance().getByRole(AriaRole.BUTTON).click();
        }

    }
    /**
     * Gets the text associated with the alert, depending on the testing tool (Selenium or Playwright).
     * @return The text of the alert message.
     */
    public static String getAlertText() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitAlert();
            return getAlert().getText();
        }else {
            getPageInstance().onDialog(Dialog::message);
            return getPageInstance().getByRole(AriaRole.ALERT).textContent();
        }
    }
    /*
     * Check whether alert is present or not
     *
     * @return the boolean value
     */
    public static boolean isAlertPresent() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            try {
                getDriver().switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        } else {
            // Playwright does not have a direct "is alert present" check;
            // register a one-shot handler and check via a flag
            boolean[] present = {false};
            getPageInstance().onceDialog(dialog -> {
                present[0] = true;
                dialog.dismiss();
            });
            return present[0];
        }
    }

    /**
     * Accepts the alert if present; no-op otherwise.
     */
    public static void AcceptAlertIfPresent() {
        if (!isAlertPresent())
            return;
        acceptAlert();
    }

    /**
     * Dismisses the alert if present; no-op otherwise.
     */
    public static void DismissAlertIfPresent() {
        if (!isAlertPresent())
            return;
        dismissAlert();
    }

    /*
     * Accept Prompt alert if alert is present after sending the text or else return
     * boolean value.
     */
    public static void acceptPrompt(String text) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            if (!isAlertPresent())
                return;
            org.openqa.selenium.Alert alert = getAlert();
            alert.sendKeys(text);
            alert.accept();
        } else {
            getPageInstance().onDialog(dialog -> {
                dialog.accept(text);
            });
        }
    }
}
