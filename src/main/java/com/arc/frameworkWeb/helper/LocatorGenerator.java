package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LocatorGenerator {
    private static Logger log= LogManager.getLogger(LocatorGenerator.class.getName());

    static String generateXpathFromPartialLinkedText = "Replace";
    /**
     * Generates a locator from a string with a placeholder for a value to be replaced.
     * @param locatorString The string containing the locator with a placeholder.
     * @param replacedValue The value to replace the placeholder in the locatorString.
     * @return The By locator generated from the string with the replaced value.
     */
    public static By generateLocatorFromString(String locatorString,String replacedValue){
       String locator = locatorString.replaceAll("replace",replacedValue);
       return By.xpath(locator);
    }
    /**
     * Generates a dynamic locator using the provided string.
     * @param string The string to use in the dynamic locator.
     * @return The By locator generated.
     */
    public static By dynamicLocator(String string)
    {
        //Not required as not using partial link test
        String locatorString = generateXpathFromPartialLinkedText.replaceAll("Replace", string);
        return xpathLocatorGernator(locatorString);
    }
    /**
     * Generates a dynamic locator using the provided string.
     * @param string The string to use in the dynamic locator.
     * @return The By locator generated.
     */
    public static By dynamicLocatorLast(String string)
    {
        //Not required as not using partial link test
        String locatorString = generateXpathFromPartialLinkedText.replaceAll("Replace", string);
        return xpathLocatorGernatorLast(locatorString);
    }
    /**
     * Generates a By locator for an element containing the specified text.
     * @param arg The text to search for in the element.
     * @return The By locator generated.
     */
    public static By xpathLocatorGernator(String arg)
    {
        return By.xpath("//*[contains(text(),'"+arg+"')]");
    }
    /**
     * Generates a By locator for the last element containing the specified text.
     * @param arg The text to search for in the element.
     * @return The By locator generated.
     */
    public static By xpathLocatorGernatorLast(String arg)
    {
        return By.xpath("(//*[contains(text(),'"+arg+"')])[last()]");
    }
    /**
     * Generates a By locator for an anchor element with the specified text.
     * @param textValue The text value of the anchor element.
     * @return The By locator generated.
     */
    public static By generateLinkLocatorFromText(String textValue){
        return By.xpath("//a[text()='"+textValue+"']");
    }
    /**
     * Generates a By locator for a label element with the specified text.
     * @param textValue The text value of the label element.
     * @return The By locator generated.
     */
    public static By generateLabelLocatorFromText(String textValue){
        return By.xpath("//label[text()='"+textValue+"']");
    }
    /**
     * Generates a By locator for a span element with the specified text.
     * @param textValue The text value of the span element.
     * @return The By locator generated.
     */
    public static By generateSpanLocatorFromText(String textValue){
        return By.xpath("//span[text()='"+textValue+"']");
    }
    /**
     * Generates a By locator for an element with the specified text.
     * @param textValue The text value of the element.
     * @return The By locator generated.
     */
    public static By generateLocatorFromText(String textValue){
        return By.xpath("//*[text()='"+textValue+"']");
    }
    /**
     * Generates a dynamic locator using double quotes for the provided string.
     * @param string The string to use in the dynamic locator.
     * @return The By locator generated.
     */
    public static By dynamicLocatorDoubleQuotes(String string)
    {
        //Not required as not using partial link test
        String locatorString = generateXpathFromPartialLinkedText.replaceAll("Replace", string);
        return xpathLocatorGernatorDoubleQuotes(locatorString);
    }
    /**
     * Generates a By locator using double quotes for an element containing the specified text.
     * @param arg The text to search for in the element.
     * @return The By locator generated.
     */
    public static By xpathLocatorGernatorDoubleQuotes(String arg)
    {
        return By.xpath("//*[contains(text(),\""+arg+"\")]");
    }
    /**
     * Generates a By locator from a given string for a specified attribute with contains.
     * @param attribute The attribute to search for in the element.
     * @param arg The value to match in the attribute.
     * @return The By locator generated.
     */
    public static By xpathLocatorByAttributeContains(String attribute,String arg)
    {
        return By.xpath("//*[contains(@"+attribute+",\""+arg+"\")]");
    }
    /**
     * Generates a By locator from a given attribute and value.
     * @param attribute The attribute to search for in the element.
     * @param arg The value of the attribute to match.
     * @return The By locator generated.
     */
    public static By xpathLocatorByAttribute(String attribute,String arg)
    {
        return By.xpath("//*[@"+attribute+"=\""+arg+"\"]");
    }
    /**
     * Generates a By locator from a given tag name, attribute, and value with contains.
     * @param tagName The tag name of the element.
     * @param attribute The attribute to search for in the element.
     * @param arg The value to match in the attribute.
     * @return The By locator generated.
     */
    public static By xpathLocatorByTagAndAttributeContains(String tagName,String attribute,String arg)
    {
        return By.xpath("//"+tagName+"[contains(@"+attribute+",\""+arg+"\")]");
    }
    /**
     * Generates a By locator from a given tag name, attribute, and value.
     * @param tagName The tag name of the element.
     * @param attribute The attribute to search for in the element.
     * @param arg The value of the attribute to match.
     * @return The By locator generated.
     */
    public static By xpathLocatorByTagAndAttribute(String tagName,String attribute,String arg)
    {
        return By.xpath("//"+tagName+"[@"+attribute+"=\""+arg+"\"]");
    }
    /**
     * Generates a dynamic locator for a dropdown using the provided string.
     * @param string The string to use in the dynamic locator.
     * @return The By locator generated.
     */
    public static By dynamicSelectDropDown(String string)
    {
        String locatorString = generateXpathFromPartialLinkedText.replaceAll("Replace", string);
        return matoptionXpathLocatorGernatorDropDown(locatorString);
    }
    /**
     * Generates a By locator for a dropdown option from a given string.
     * @param arg The value of the dropdown option.
     * @return The By locator generated.
     */
    public static By matoptionXpathLocatorGernatorDropDown(String arg) {
        return By.xpath("//mat-option//*[contains(text(),'"+arg+"')]");
    }
    /**
     * Generates a By locator for an element with text and containing nodes.
     * @param arg The text to search for in the element.
     * @return The By locator generated.
     */
    public static By xpathGeneratorTextWithNodes(String arg){
        return By.xpath("//*[text()[contains(.,'"+arg+"')]]");
    }
    /**
     * Generates a By locator for a button with the specified text.
     * @param textValue The text value of the button.
     * @return The By locator generated.
     */
    public static By generateLocatorByButtonText(String textValue){
        return By.xpath("//button[text()='"+textValue+"']");
    }
}
