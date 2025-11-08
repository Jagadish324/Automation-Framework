package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static com.arc.frameworkWeb.helper.CommonHelper.webDriver;

public class UploadFile {
    private static Logger log= LogManager.getLogger(UploadFile.class.getName());
    /**
     * Sets the clipboard data to the specified string.
     *
     * @param string The string to set as clipboard data
     */
    public static void setClipboardData(String string) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
    /**
     * Uploads a file on macOS by simulating keyboard shortcuts and mouse clicks.
     * Opens the file upload dialog and pastes the file path into it.
     *
     * @param fileName     The name of the file to upload
     * @param uploadButton The locator of the upload button
     */
    public static void uploadFileMac(String fileName, By uploadButton)  {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        if (CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
            String path = System.getProperty("user.dir") + "/resources/testData/" + "resourcePath";
            StringSelection ss = new StringSelection(path);
            robot.delay(500); // Use Robot's built-in delay method
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            robot.delay(500); // Use Robot's built-in delay method
            String str = webDriver.getWindowHandle();

            webDriver.switchTo().window(webDriver.getWindowHandle());
            String str1 = webDriver.getWindowHandle();

            //Open Goto window
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);
//            if(handleFile.equalsIgnoreCase("done")) {
            Dimension i = webDriver
                    .manage().window().getSize();
            System.out.println("Dimension x and y :" + i.getWidth() + " " + i.getHeight());
            //3. Get the height and width of the screen
            int x = (i.getWidth() / 2) + 80;
//            int x = 600;
//            int y = 420;
            int y = (i.getHeight() / 2) + 5;
            System.out.println(x + "+++++++++++++++++++" + y);
            //4. Now, adjust the x and y coordinates with reference to the Windows popup size on the screen
            //e.g. On current screen , Windows popup displays on almost 1/4th of the screen . So with reference to the same, file name x and y position is specified.
            //Note : Please note that coordinates calculated in this sample i.e. x and y may vary as per the screen resolution settings
            robot.mouseMove(x, y);
            robot.delay(1000); // Use Robot's built-in delay method
            //Clicks Left mouse button
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(1000); // Use Robot's built-in delay method
            System.out.println("Browse button clicked");
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);

//            }
//            handleFile="done";
//            ExplicitWait.hardWait(500);
//            robot.keyPress(KeyEvent.VK_META);
//            robot.keyPress(KeyEvent.VK_A);
//            robot.keyRelease(KeyEvent.VK_META);
//            robot.keyRelease(KeyEvent.VK_A);
//            ExplicitWait.hardWait(500);
//            robot.keyPress(KeyEvent.VK_DELETE);
//            ExplicitWait.hardWait(500);
//            robot.keyRelease(KeyEvent.VK_DELETE);
//            ExplicitWait.hardWait(500);
            //Paste the clipboard value
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);


            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_V);
//
            //Press Enter key to close the Goto window and Upload window

            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }

    }
    /**
     * Uploads a file on Windows by simulating keyboard shortcuts and mouse clicks.
     * Sets the file path to the clipboard, then pastes it into the file upload dialog.
     *
     * @param fileName     The name of the file to upload
     * @param uploadButton The locator of the upload button
     */
    public static void uploadFileWindows(String fileName, By uploadButton) {
        try {
            //Setting clipboard with file location
            ExplicitWait.waitForVisibilityAndClickabilityOfElement(uploadButton);
            Button.click(uploadButton);
            Robot robot = new Robot();
            robot.delay(4000); // Use Robot's built-in delay method
            String fileLocation=System.getProperty("user.dir") + "\\uploadFile\\"+fileName;
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
            try {
                throw exp;
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
