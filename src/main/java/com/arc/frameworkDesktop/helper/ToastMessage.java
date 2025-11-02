package com.arc.frameworkDesktop.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ToastMessage extends CommonHelper {
    private static Logger log = LogManager.getLogger(ToastMessage.class.getName());

    public static String getToastMessage(){
        return desktopDriver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
    }
}
