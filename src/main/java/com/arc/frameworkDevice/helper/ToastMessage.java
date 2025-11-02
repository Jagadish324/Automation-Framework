package com.arc.frameworkDevice.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ToastMessage extends CommonHelper{
    private static Logger log = LogManager.getLogger(ToastMessage.class.getName());
    /**
     * Retrieves the text of the first toast message displayed on the screen.
     *
     * @return The text of the toast message.
     */
    public static String getToastMessage(){
        return deviceDriver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
    }
}
