package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.util.LinkedList;
import java.util.List;

public class ElementInfo extends CommonHelper {
    private static Logger log = LogManager.getLogger(ElementInfo.class.getName());

    private static Rectangle rectangle;
    /**
     * Checks if an element identified by the provided locator is displayed.
     * @param locator The locator for the element.
     * @return true if the element is displayed, false otherwise.
     */
    public static Boolean isDisplayed(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            boolean flag = false;
            AutoWait.autoWait(locator);
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    flag = getElement(locator).isDisplayed();
                    break;
                } catch (Exception e) {
                    System.out.println("is display retry" + i);
                }
            }
            return flag;
        } else {
            return page.isVisible(getLocator("" + locator));
        }
    }
    /**
     * Checks if an element identified by the provided locator is displayed without retry.
     * @param locator The locator for the element.
     * @return true if the element is displayed, false otherwise.
     */
    public static Boolean isDisplayedWithoutRetry(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getElement(locator).isDisplayed();
        } else {
            return page.isVisible(getLocator("" + locator));
        }
    }
    /**
     * Checks if an element identified by the provided WebElement is displayed.
     * @param locator The WebElement for the element.
     * @return true if the element is displayed, false otherwise.
     */
    public static Boolean isDisplayed(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            boolean flag = false;
            for (int i = 0; i < CONSTANT.STEP_RETRY; i++) {
                try {
                    AutoWait.autoWait(locator);
                    break;
                } catch (Exception e) {
                    System.out.println("is display retry" + i);
                    throw e;
                }
            }
            return flag;
        } else {
            return page.isVisible(getLocator("" + locator));
        }
    }
    /**
     * Checks if an element identified by the provided locator is enabled.
     * @param locator The locator for the element.
     * @return true if the element is enabled, false otherwise.
     */
    public static Boolean isEnabled(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getElement(locator).isEnabled();
        } else {
            return page.isEnabled(getLocator("" + locator));
        }
    }
    /**
     * Checks if an element identified by the provided locator is selected.
     * @param locator The locator for the element.
     * @return true if the element is selected, false otherwise.
     */
    public static Boolean isSelected(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getElement(locator).isSelected();
        } else {
            return page.isChecked(getLocator("" + locator));
        }
    }
    /**
     * Gets the tag name of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The tag name of the element.
     */
    public static String getTagName(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getElement(locator).getTagName();
        } else {
//            return page.getByLabel(getLocator(""+locator));
            return "";
        }
    }
    /**
     * Gets the dimension of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The dimension of the element.
     */
    public static Dimension getDimension(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            rectangle = getElement(locator).getRect();
            return rectangle.getDimension();
        } else {
            return null;
        }
    }
    /**
     * Gets the width of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The width of the element.
     */
    public static int getElementWidth(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            rectangle = getElement(locator).getRect();
            return rectangle.getWidth();
        } else {
            return 0;
        }
    }
    /**
     * Gets the height of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The height of the element.
     */
    public static int getElementHeight(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            rectangle = getElement(locator).getRect();
            return rectangle.getHeight();
        } else {
            return 0;
        }
    }
    /**
     * Gets the x-coordinate of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The x-coordinate of the element.
     */
    public static int getXCoordinate(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            rectangle = getElement(locator).getRect();
            return rectangle.getX();
        } else {
            return 0;
        }
    }
    /**
     * Gets the y-coordinate of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The y-coordinate of the element.
     */
    public static int getYCoordinate(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            rectangle = getElement(locator).getRect();
            return rectangle.getY();
        } else {
            return 0;
        }
    }
    /**
     * Gets the value of a CSS attribute of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @param cssAttribute The CSS attribute to get the value of.
     * @return The value of the CSS attribute.
     */
    public static String getCssValue(By locator, String cssAttribute) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getElement(locator).getCssValue(cssAttribute);
        } else {
            return page.locator(getLocator("" + locator)).first().getAttribute(cssAttribute);
        }
    }
    /**
     * Gets the value of a CSS attribute of the element identified by the provided WebElement.
     * @param locator The WebElement for the element.
     * @param cssAttribute The CSS attribute to get the value of.
     * @return The value of the CSS attribute.
     */
    public static String getCssValue(WebElement locator, String cssAttribute) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return locator.getCssValue(cssAttribute);
        } else {
            return page.getAttribute(getLocator("" + locator), cssAttribute);
        }
    }
    /**
     * Gets the value of an attribute of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @param attribute The attribute to get the value of.
     * @return The value of the attribute.
     */
    public static String getAttributeValue(By locator, String attribute) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWaitButton(locator);
            return getElement(locator).getAttribute(attribute);
        } else {
            return page.locator(getLocator("" + locator)).first().getAttribute(attribute);
        }
    }
    /**
     * Gets the value of an attribute of the element identified by the provided WebElement.
     * @param locator The WebElement for the element.
     * @param attribute The attribute to get the value of.
     * @return The value of the attribute.
     */
    public static String getAttributeValue(WebElement locator, String attribute) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return locator.getAttribute(attribute);
        } else {
            return page.locator(getLocator("" + locator)).first().getAttribute(attribute);
        }
    }
    /**
     * Gets the text of the element identified by the provided locator.
     * @param locator The locator for the element.
     * @return The text of the element.
     */
    public static String getText(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            AutoWait.autoWait(locator);
            return webDriver.findElement(locator).getText();
        } else {
            return page.locator(getLocator("" + locator)).first().textContent();
        }
    }
    /**
     * Gets the list of text values of the provided list of WebElements.
     * @param webElement The list of WebElements.
     * @return The list of text values.
     */
    public static List<String> getListOfText(List<WebElement> webElement) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            List<String> valueList = new LinkedList<String>();
            for (WebElement element : webElement) {
                valueList.add(element.getText());
            }
            return valueList;
        } else {
            return null;
        }
    }
    /**
     * Gets the count of elements identified by the provided locator.
     * @param locator The locator for the elements.
     * @return The count of elements.
     */
    public static int getElementCount(By locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            int size = 0;
            List<WebElement> elementSize = getListOfWebElements(locator);
            size = elementSize.size();
            return size;
        } else {
            return 0;
        }
    }
    /**
     * Checks if an attribute is present in the element identified by the provided locator.
     * @param element The locator for the element.
     * @param attribute The attribute to check.
     * @return true if the attribute is present, false otherwise.
     */
    public static Boolean isAttributePresent(By element, String attribute) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Boolean result = false;
            try {
                String value = getAttributeValue(element, attribute);
                if (value != null) {
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            return result;
        } else {
            String val = page.locator(getLocator("" + element)).first().getAttribute(attribute);
//            boolean flag =  ;
            return !(val==null);
        }
    }
    /**
     * Checks if an attribute is present in the element identified by the provided WebElement.
     * @param element The WebElement for the element.
     * @param attribute The attribute to check.
     * @return true if the attribute is present, false otherwise.
     */
    public static Boolean isAttributePresent(WebElement element, String attribute) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            Boolean result = false;
            try {
                String value = getAttributeValue(element, attribute);
                if (value != null) {
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            return result;
        } else {
            return null;
        }
    }
    /**
     * Gets the text of the element identified by the provided WebElement.
     * @param locator The WebElement for the element.
     * @return The text of the element.
     */
    public static String getText(WebElement locator) {
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return locator.getText();
        } else {
            return null;
        }
    }
}
