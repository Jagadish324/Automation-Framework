package com.arc.frameworkWeb.helper.multithreading;

import com.arc.frameworkWeb.helper.ExplicitWait;
import org.openqa.selenium.By;

public class ElementsToBeClickable extends Thread {
    private By locator;
    private int pollTime;
    public void setLocator(By locator) {
        this.locator = locator;
    }
    public void setPollTime(int pollTime) {
        this.pollTime = pollTime;
    }
    @Override
    synchronized public void run() {
            ExplicitWait.waitForElementsToBeClickable(locator, pollTime);
    }
}
