package com.arc.frameworkWeb.helper;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

public class ScreenShot extends CommonHelper {
    private static Logger log= LogManager.getLogger(ScreenShot.class.getName());
    /**
     * Takes a screenshot of the current web page and saves it to the specified file path.
     *
     * @param pathName The file path where the screenshot will be saved
     * @return The file object representing the saved screenshot
     */
    public static File takeScreenShot(String pathName) {
        File fileA = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(fileA, new File(pathName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new File(pathName);
    }
    /**
     * Compares two image files pixel by pixel to determine if they are identical.
     *
     * @param fileA The first image file
     * @param fileB The second image file
     * @return True if the images are identical, false otherwise
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
            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to compare image files ...");
            return false;
        }
    }
    /**
     * Compares two image files pixel by pixel and calculates the percentage difference.
     *
     * @param fileA The first image file
     * @param fileB The second image file
     * @return The percentage difference between the images
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
     * Resizes two images to the smaller image's size and compares them pixel by pixel to calculate the percentage difference.
     *
     * @param filePath1 The file path of the first image
     * @param filePath2 The file path of the second image
     */
    public static void resizeAndCompareImageWithPercentageDiff(String filePath1, String filePath2) {
        BufferedImage img1 = null;
        try {
            img1 = ImageIO.read(new File(filePath1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(new File(filePath2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int width1 = img1.getWidth();
        int height1 = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();
        int width = 0;
        int height = 0;
        // Scale down the larger image to the size of the smaller image
        if (width1 * height1 > width2 * height2) {
            BufferedImage scaledImg = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = scaledImg.createGraphics();
            g.drawImage(img1, 0, 0, width2, height2, null);
            g.dispose();
            width = width2;
            height = height2;
            img1 = scaledImg;
        } else {
            BufferedImage scaledImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = scaledImg.createGraphics();
            g.drawImage(img2, 0, 0, width1, height1, null);
            g.dispose();
            width = width1;
            height = height1;
            img2 = scaledImg;
        }

        int numPixels = width * height;
        int numSamePixels = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);
                Color color1 = new Color(rgb1);
                Color color2 = new Color(rgb2);
                if (color1.equals(color2)) {
                    numSamePixels++;
                }
            }
        }
        double percentage = (double) numSamePixels / numPixels * 100.0;
        System.out.println(percentage);
    }
    /**
     * Cleans all files in a directory.
     *
     * @param folderPath The path of the folder to clean
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
    /**
     * Crops an image to remove white background.
     */

    public static void cropImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("path/to/image.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int minX = image.getWidth();
        int minY = image.getHeight();
        int maxX = 0;
        int maxY = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);
                if (pixel != Color.WHITE.getRGB()) {
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        BufferedImage croppedImage = image.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
        try {
            ImageIO.write(croppedImage, "jpg", new File("path/to/cropped/image.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Crops an image to remove shadows and white background.
     *
     * @param filePath The file path of the image to crop
     * @throws Exception
     */
    public static void cropImageWithShadow(String filePath) throws Exception {
        // Load the image
        BufferedImage image = ImageIO.read(new File(filePath));

        // Find the bounding box of the non-white pixels
        int minX = image.getWidth();
        int minY = image.getHeight();
        int maxX = 0;
        int maxY = 0;

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);
                Color color = new Color(pixel);

                // Check if pixel is not white or not a shadow color (e.g., grayish tone)
                if (!isWhite(color) && !isShadow(color)) {
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        // Crop the image
        BufferedImage croppedImage = image.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);

        // Save the cropped image
        ImageIO.write(croppedImage, "jpg", new File("src/main/resources/image.jpg"));
    }
    /**
     * Checks if a color is white.
     *
     * @param color The color to check
     * @return True if the color is white, false otherwise
     */
    private static boolean isWhite(Color color) {
        return color.getRed() == 255 && color.getGreen() == 255 && color.getBlue() == 255;
    }
    /**
     * Checks if a color is a shadow color (e.g., grayish tone).
     *
     * @param color The color to check
     * @return True if the color is a shadow color, false otherwise
     */
    private static boolean isShadow(Color color) {
        // Adjust the threshold value based on your specific shadow color range
        int threshold = 150;
        int average = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return average <= threshold;
    }


}
