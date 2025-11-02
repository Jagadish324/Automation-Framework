package com.arc.frameworkDesktop.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

public class JavaScripExe extends CommonHelper {
    private static Logger log = LogManager.getLogger(JavaScripExe.class.getName());

    public static void scrollToSpecificPoint(){
        ((JavascriptExecutor) desktopDriver).executeScript("window.setTimeout(arguments[arguments.length - 1], 500);");
    }

}
