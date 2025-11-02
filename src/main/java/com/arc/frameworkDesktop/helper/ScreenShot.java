package com.arc.frameworkDesktop.helper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

public class ScreenShot extends CommonHelper {
    private static Logger log = LogManager.getLogger(ScreenShot.class.getName());

    public static byte[] getScreenShotBytes(){
        byte[] screenshot = Base64.encodeBase64(desktopDriver.getScreenshotAs(OutputType.BYTES));
        return screenshot;
    }
    public static File takeScreenShot(String pathName){
        File fileA  = ((TakesScreenshot) desktopDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(fileA, new File(pathName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       return new File(pathName);
    }
    public static boolean compareImage(File fileA, File fileB) {
        try {
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if(sizeA == sizeB) {
                for(int i=0; i<sizeA; i++) {
                    if(dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Failed to compare image files ...");
            return  false;
        }
    }

    public static float compareImageWithPercentageDiff(File fileA, File fileB) {
        float percentage = 0;
        try {
            // take buffer data from both image files //
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            int count = 0;
            // compare data-buffer objects //
            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) == dbB.getElem(i)) {
                        count = count + 1;
                    }
                }
                percentage = (count * 100) / sizeA;
                System.out.println("Both the images are same by :" + percentage);
            } else {
                System.out.println("Both the images are not of same size");
            }
        } catch (Exception e) {
            System.out.println("Failed to compare image files ...");
        }
        return percentage;
    }
    public static void cleanScreenShotDirectories(String folderPath) {
        File inDir = new File(folderPath);
        try {
            if (inDir.exists()) {
                FileUtils.cleanDirectory(inDir);
            }
        } catch (IOException e) {
        }
    }


    /**
     * Captures a screenshot of a specific element on the screen.
     * It takes a full-screen screenshot, locates the element, and crops the image to the element's dimensions.
     * The cropped image is then saved to the specified output path.
     */
    public static File captureScreenshotByLocator(By locator, String outputPath) {
        try {
            // Locate the element using the provided locator
            WebElement element = desktopDriver.findElement(locator);

            // Capture the entire screen as a screenshot
            File screenShot = ((TakesScreenshot) desktopDriver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImage = ImageIO.read(screenShot);

            // Get the element's location and dimensions
            Point point = element.getLocation();
            int elementWidth = element.getSize().getWidth();
            int elementHeight = element.getSize().getHeight();

            // Crop the screenshot to the element's dimensions
            BufferedImage elementImage = fullImage.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);

            // Save the cropped screenshot to the specified path
            File outputFile = new File(outputPath);
            ImageIO.write(elementImage, "png", outputFile);
            System.out.println("Screenshot saved at: " + outputFile.getAbsolutePath());
            return outputFile;
        } catch (IOException | WebDriverException e) {
            throw new RuntimeException("Failed to capture screenshot of the element", e);
        }
    }

    /**
     * Captures a specific area of the screen and saves it as an image file.
     * Uses the Robot class to take a screenshot of the defined rectangular area.
     *
     */
    public static File takeScreenShot(String pathName, int x, int y, int width, int height) {
        try {
            // Create a Robot instance to capture the screen
            Robot robot = new Robot();

            // Define the area to capture using the provided coordinates and dimensions
            Rectangle captureArea = new Rectangle(x, y, width, height);

            // Capture the screen area defined by the rectangle
            BufferedImage screenShot = robot.createScreenCapture(captureArea);

            // Save the captured image to the specified path
            File file = new File(pathName);
            ImageIO.write(screenShot, "jpg", file);

            // Return the file object representing the saved screenshot
            return file;
        } catch (AWTException | IOException e) {
            // Handle exceptions by throwing a RuntimeException
            throw new RuntimeException("Error capturing screen area", e);
        }
    }

}
