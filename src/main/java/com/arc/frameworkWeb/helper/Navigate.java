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
            webDriver.navigate().to(url);
        } else {
            page.navigate(url);
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
            webDriver.get(url);
        } else {
            page.navigate(url);
        }
        afterPerformingAction();
    }
    /**
     * Navigates back in the browser's history.
     */
    public static void navigateBack() {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            webDriver.navigate().back();
        } else {
            page.goBack();
        }
        afterPerformingAction();
    }
    /**
     * Navigates forward in the browser's history.
     */
    public static void navigateForward() {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            webDriver.navigate().forward();
        } else {
            page.goForward();
        }
        afterPerformingAction();
    }
    /**
     * Refreshes the current page.
     */
    public static void refreshPage() {
        beforePerformingAction();
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            webDriver.navigate().refresh();
        } else {
            page.reload();
        }
        afterPerformingAction();
    }
    /**
     * Quits the browser or closes the page.
     */
    public static void quit() {
        beforePerformingAction();
        if(CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            webDriver.quit();
        }else {
            page.close();
        }
        afterPerformingAction();
    }
    /**
     * Gets the title of the current page.
     * @return The title of the current page.
     */
    public static String getTitle() {
        if(CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return  webDriver.getTitle();
        }else{
            return page.title();
        }
    }
    /**
     * Gets the current URL of the page.
     * @return The current URL of the page.
     */
    public static String getCurrentUrl() {
        if(CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return webDriver.getCurrentUrl();
        }else {
            return page.url();
        }
    }
}
