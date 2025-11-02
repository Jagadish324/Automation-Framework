package com.arc.frameworkDesktop.utility;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageAction {


    public static void main(String[] args) {
        try {
            // Taking a screenshot
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            ImageIO.write(screenCapture, "png", new File("screenshot.png"));

            // Finding text on the screen (using OCR)
            ITesseract tesseract = new Tesseract();
//            System.setProperties("TESSDATA_PREFIX","");
            tesseract.setDatapath("C:\\tesseract\\tesseract\\tessdata"); // Set the path to your tessdata folder
            tesseract.setLanguage("eng"); // Set the language for OCR
            String result = tesseract.doOCR(screenCapture);

            // Getting the coordinates of the text
            // (For demonstration, we're using a hardcoded example)
            int textX = 100;
            int textY = 100;

            // Performing a mouse click on the text
            robot.mouseMove(textX, textY);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Getting the text color of a field (at the specified coordinates)
            Color textColor = robot.getPixelColor(textX, textY);

            // Detecting changes in color/text in the image (comparing with previous screenshot)
            // For simplicity, let's assume we saved the previous screenshot as "previousScreenshot.png"
            BufferedImage previousScreenCapture = ImageIO.read(new File("previousScreenshot.png"));
            boolean isDifferent = isDifferent(screenCapture, previousScreenCapture);

            // Save the current screenshot for future comparison
            ImageIO.write(screenCapture, "png", new File("previousScreenshot.png"));
        } catch (AWTException | IOException | TesseractException ex) {
            System.err.println(ex);
        }
    }

    private static boolean isDifferent(BufferedImage image1, BufferedImage image2) {
        // Compare the two images pixel by pixel
        // For simplicity, let's assume images are of the same size
        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    return true; // Images are different
                }
            }
        }
        return false; // Images are the same
    }


}
