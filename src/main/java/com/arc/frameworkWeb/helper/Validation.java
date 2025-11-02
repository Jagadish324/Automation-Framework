package com.arc.frameworkWeb.helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class Validation {
    private static Logger log= LogManager.getLogger(Validation.class.getName());
    /**
     * Validates that the actual text is equal to the expected text.
     *
     * @param actualText The actual text
     * @param expectedText The expected text
     */
    public static void validateText(String actualText, String expectedText){
        Assert.assertEquals(actualText,expectedText);
        System.out.println(actualText+" is equal to "+expectedText);
    }
    /**
     * Validates that the actual text is not equal to the expected text.
     *
     * @param actualText The actual text
     * @param expectedText The expected text
     */
    public static void validateTextNotEqual(String actualText, String expectedText){
        Assert.assertNotEquals(actualText,expectedText);
        System.out.println(actualText+" is equal to "+expectedText);
    }


}


