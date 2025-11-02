package com.arc.frameworkDevice.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;


public class TextBox extends CommonHelper {
    private static Logger log = LogManager.getLogger(TextBox.class.getName());

    /**
     * Enters text into a text field by tapping on the element's coordinates.
     * Clears the text field first.
     *
     * @param element The element to interact with.
     * @param value   The text value to enter.
     */
    public static void enterTextFieldByCoordinateTap(By element, String value) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
        AutoWait.elementWait(element);
        try {
            Button.tapOnElementCoordinate(element);
        }catch (StaleElementReferenceException e){

        }
        clearText(element);
        sendKeys(element, value);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);

    }
    /**
     * Clears the text of the specified element.
     *
     * @param element The element to clear.
     */
    public static void clearText(By element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element );
        AutoWait.elementWait(element);

        getElement(element).clear();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element );

    }
    /**
     * Sends keys to the specified element.
     *
     * @param element The element to send keys to.
     * @param value   The keys to send.
     */
    public static void sendKeys(By element, String value) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
        AutoWait.elementWait(element);
        getElement(element).sendKeys(value);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
    }
    /**
     * Sends individual character keys to the specified element.
     *
     * @param element The element to send keys to.
     * @param value   The keys to send.
     */
    public static void sendCharKeys(By element, String value) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
        AutoWait.elementWait(element);
        for(int i=0;i<value.length();i++) {
            getElement(element).sendKeys(value.charAt(i)+"");
        }
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);

    }
    /**
     * Clears the text of the specified element and sends new keys to it.
     *
     * @param element The element to clear and send keys to.
     * @param value   The keys to send.
     */
    public static void clearAndSendKeys(By element, String value) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
        AutoWait.elementWait(element);
        clearText(element);
        sendKeys(element,value);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);

    }

    //Appium WebElement
    /**
     * Enters text into a text field by tapping on the element's coordinates.
     * Clears the text field first.
     *
     * @param element The element to interact with.
     * @param value   The text value to enter.
     */

    public static void enterTextFieldByCoordinateTap(WebElement element, String value) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
        try {
            Button.tapOnElementCoordinate(element);
        }catch (StaleElementReferenceException e){

        }
        clearText(element);
        sendKeys(element, value);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
    }
    /**
     * Clears the text of the specified element.
     *
     * @param element The element to clear.
     */
    public static void clearText(WebElement element) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element);
        element.clear();
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element );
    }
    /**
     * Sends keys to the specified element.
     *
     * @param element The element to send keys to.
     * @param value   The keys to send.
     */
    public static void sendKeys(WebElement element, String value) {
        log.info("Start " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
        element.sendKeys(value);
        log.info("Stop " + Thread.currentThread().getStackTrace()[1].getMethodName() +" and perform action on "+ element +" send value as " + value);
    }

}
