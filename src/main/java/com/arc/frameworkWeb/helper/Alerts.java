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
            return webDriver.switchTo().alert();
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
            page.onDialog(Dialog::accept);
            page.getByRole(AriaRole.BUTTON).click();
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
            page.onDialog(Dialog::dismiss);
            page.getByRole(AriaRole.BUTTON).click();
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
            page.onDialog(Dialog::message);
            return page.getByRole(AriaRole.ALERT).textContent();
        }
    }
    /*
     * Check whether alert is present or not
     *
     * @return the boolean value
     */
    public static boolean isAlertPresent() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {

        }else {

        }
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    /**
     * Checks whether an alert is present, depending on the testing tool (Selenium or Playwright).
     * @return true if an alert is present, false otherwise.
     */
    public static void AcceptAlertIfPresent() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {

        }else {

        }
        if (!isAlertPresent())
            return;
        acceptAlert();
    }

    /*
     * Accept if alert is present or else return boolean value
     */
    public static void DismissAlertIfPresent() {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {

        }else {

        }
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

        }else {

        }
        if (!isAlertPresent())
            return;
        org.openqa.selenium.Alert alert = getAlert();
        alert.sendKeys(text);
        alert.accept();
    }
}
