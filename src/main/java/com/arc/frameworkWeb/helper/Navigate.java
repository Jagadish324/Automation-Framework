package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigate extends CommonHelper {
    private static Logger log = LogManager.getLogger(Navigate.class.getName());
    /**
     * Navigates to the given URL.
     * @param url The URL to navigate to.
     */
    public static void navigateTo(String url) {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().navigate().to(url);
        } else {
            getPageInstance().navigate(url);
        }
        afterPerformingAction();
    }
    /**
     * Loads the given URL.
     * @param url The URL to load.
     */
    public static void get(String url) {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().get(url);
        } else {
            getPageInstance().navigate(url);
        }
        afterPerformingAction();
    }
    /**
     * Navigates back in the browser's history.
     */
    public static void navigateBack() {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().navigate().back();
        } else {
            getPageInstance().goBack();
        }
        afterPerformingAction();
    }
    /**
     * Navigates forward in the browser's history.
     */
    public static void navigateForward() {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().navigate().forward();
        } else {
            getPageInstance().goForward();
        }
        afterPerformingAction();
    }
    /**
     * Refreshes the current page.
     */
    public static void refreshPage() {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().navigate().refresh();
        } else {
            getPageInstance().reload();
        }
        afterPerformingAction();
    }
    /**
     * Quits the browser or closes the page.
     */
    public static void quit() {
        beforePerformingAction();
        if(CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().quit();
        }else {
            getPageInstance().close();
        }
        afterPerformingAction();
    }
    /**
     * Gets the title of the current page.
     * @return The title of the current page.
     */
    public static String getTitle() {
        if(CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getDriver().getTitle();
        }else{
            return getPageInstance().title();
        }
    }
    /**
     * Gets the current URL of the page.
     * @return The current URL of the page.
     */
    public static String getCurrentUrl() {
        if(CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getDriver().getCurrentUrl();
        }else {
            return getPageInstance().url();
        }
    }
}
