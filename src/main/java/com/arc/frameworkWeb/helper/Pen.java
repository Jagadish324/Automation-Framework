package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;

public class Pen extends CommonHelper{
    private static Logger log= LogManager.getLogger(Pen.class.getName());

    public static Actions action;
    /**
     * Constructor for initializing the Actions object for pen actions.
     */
    public Pen() {
        action =new Actions(webDriver);
    }
    /**
     * Perform a pen action on the specified element.
     *
     * @param locator  The locator of the element to perform the action on.
     * @param xOffset  The horizontal offset to move the pen.
     * @param yOffset  The vertical offset to move the pen.
     */
    public static void penAction(By locator, int xOffset, int yOffset){
        action.setActivePointer(PointerInput.Kind.PEN,"default pen").moveToElement(getElement(locator))
                .clickAndHold().moveByOffset(xOffset,yOffset).release().perform();
    }
}
