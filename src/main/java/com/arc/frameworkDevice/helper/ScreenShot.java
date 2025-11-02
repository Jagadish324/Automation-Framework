package com.arc.frameworkDevice.helper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

public class ScreenShot extends  CommonHelper{
    private static Logger log = LogManager.getLogger(ScreenShot.class.getName());
    /**
     * Takes a screenshot of the current screen and returns it as a byte array.
     * @return The screenshot as a byte array.
     */
    public static byte[] getScreenShotBytes(){
        byte[] screenshot = Base64.encodeBase64(deviceDriver.getScreenshotAs(OutputType.BYTES));
        return screenshot;
    }
    /**
     * Takes a screenshot of the current screen and saves it to the specified path.
     * @param pathName The path where the screenshot should be saved.
     * @return The file object representing the saved screenshot.
     */
    public static File takeScreenShot(String pathName){
        File fileA  = ((TakesScreenshot) deviceDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(fileA, new File(pathName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       return new File(pathName);
    }
    /**
     * Compares two image files pixel by pixel to check if they are identical.
     * @param fileA The first image file.
     * @param fileB The second image file.
     * @return True if the images are identical, false otherwise.
     */
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
    /**
     * Compares two image files pixel by pixel and calculates the percentage difference.
     * @param fileA The first image file.
     * @param fileB The second image file.
     * @return The percentage difference between the two images.
     */
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
    /**
     * Cleans up all files in the specified folder path.
     * @param folderPath The path to the folder containing files to be cleaned up.
     */
    public static void cleanScreenShotDirectories(String folderPath) {
        File inDir = new File(folderPath);
        try {
            if (inDir.exists()) {
                FileUtils.cleanDirectory(inDir);
            }
        } catch (IOException e) {
        }
    }

}
