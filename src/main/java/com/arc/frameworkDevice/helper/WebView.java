package com.arc.frameworkDevice.helper;

import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class WebView extends CommonHelper{
    //for hybrid app// get context and change view
    private static Logger log = LogManager.getLogger(WebView.class.getName());
    /**
     * Prints all available contexts and switches to a context if its name is empty.
     */
    public static void getContext(){
        Set<String> contexts = ((AndroidDriver) deviceDriver).getContextHandles();
        for(String contextName : contexts){
            System.out.println(contextName);
            if(contextName.equalsIgnoreCase("")) {
                ((AndroidDriver) deviceDriver).context(contextName);
            }

        }
    }
}
