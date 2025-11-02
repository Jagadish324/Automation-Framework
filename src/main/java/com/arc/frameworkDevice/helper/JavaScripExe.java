package com.arc.frameworkDevice.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

public class JavaScripExe extends CommonHelper{
    private static Logger log = LogManager.getLogger(JavaScripExe.class.getName());
    /**
     * Scroll to a specific point on the web page.
     */
    public static void scrollToSpecificPoint(){
        ((JavascriptExecutor) deviceDriver).executeScript("window.setTimeout(arguments[arguments.length - 1], 500);");
    }

}
