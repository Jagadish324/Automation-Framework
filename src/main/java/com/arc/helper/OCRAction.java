//package com.arc.helper;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import net.sourceforge.tess4j.util.ImageIOHelper;
//import org.bytedeco.javacpp.lept.PIX;
//import org.bytedeco.opencv.global.opencv_core;
//import org.bytedeco.opencv.global.opencv_imgcodecs;
//import org.bytedeco.opencv.global.opencv_imgproc;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Rect;
//import org.bytedeco.opencv.opencv_core.RectVector;
//
//import javax.imageio.ImageIO;
//import java.awt.AWTException;
//import java.awt.Rectangle;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//public class OCRAction {
//    public static class TextCoordinates {
//        private String text;
//        private int x;
//        private int y;
//        private int width;
//        private int height;
//
//        public TextCoordinates(String text, int x, int y, int width, int height) {
//            this.text = text;
//            this.x = x;
//            this.y = y;
//            this.width = width;
//            this.height = height;
//        }
//
//        @Override
//        public String toString() {
//            return "TextCoordinates{" +
//                    "text='" + text + '\'' +
//                    ", x=" + x +
//                    ", y=" + y +
//                    ", width=" + width +
//                    ", height=" + height +
//                    '}';
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            // Take a screenshot
//            BufferedImage screenshot = takeScreenshot();
//
//            // Save the screenshot to a temporary file
//            File screenshotFile = new File("screenshot.png");
//            ImageIO.write(screenshot, "png", screenshotFile);
//
//            // Detect text coordinates using Tesseract
//            List<TextCoordinates> textCoordinatesList = detectTextCoordinates(screenshotFile);
//
//            // Fallback: Text segmentation using image analysis (potentially less accurate)
//            if (textCoordinatesList.isEmpty()) {
//                textCoordinatesList = fallbackTextSegmentation(screenshotFile);
//            }
//
//            // Delete the screenshot file
//            if (screenshotFile.delete()) {
//                System.out.println("Screenshot deleted successfully.");
//            } else {
//                System.out.println("Failed to delete the screenshot.");
//            }
//
//            // Print detected text coordinates
//            for (TextCoordinates textCoordinates : textCoordinatesList) {
//                System.out.println(textCoordinates);
//            }
//
//        } catch (IOException | AWTException | TesseractException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static BufferedImage takeScreenshot() throws AWTException {
//        Robot robot = new Robot();
//        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//        return robot.createScreenCapture(screenRect);
//    }
//
//    private static List<TextCoordinates> detectTextCoordinates(File imageFile) throws TesseractException, IOException {
//        ITesseract tesseract = new Tesseract();
//        // Set the Tesseract data path to the tessdata folder containing the language files
//        tesseract.setDatapath("tessdata");
//        tesseract.setLanguage("eng"); // Set the language to English
//
//        List<TextCoordinates> textCoordinatesList = new ArrayList<>();
//
//        BufferedImage image = ImageIO.read(imageFile);
//        PIX imagePix = ImageIOHelper.convertBufferedImageToPix(image);
//
//        try (var resultIterator = tesseract.getAPI().GetIterator(imagePix, 0)) {
//            resultIterator.Begin();
//            do {
//                String word = resultIterator.GetUTF8Text(ITesseract.PageIteratorLevel.RIL_WORD);
//                int[] boundingBox = resultIterator.BoundingBox(ITesseract.PageIteratorLevel.RIL_WORD);
//
//                if (word != null) {
//                    int x = boundingBox[0];
//                    int y = boundingBox[1];
//                    int width = boundingBox[2] - boundingBox[0];
//                    int height = boundingBox[3] - boundingBox[1];
//
//                    textCoordinatesList.add(new TextCoordinates(word, x, y, width, height));
//                }
//            } while (resultIterator.Next(ITesseract.PageIteratorLevel.RIL_WORD));
//        }
//
//        return textCoordinatesList;
//    }
//
//    private static List<TextCoordinates> fallbackTextSegmentation(File imageFile) {
//        List<TextCoordinates> textCoordinatesList = new ArrayList<>();
//
//        Mat image = opencv_imgcodecs.imread(imageFile.getAbsolutePath(), opencv_imgcodecs.IMREAD_GRAYSCALE);
//
//        // Apply adaptive threshold to invert the image
//        Mat binaryImage = new Mat();
//        opencv_imgproc.adaptiveThreshold(image, binaryImage, 255,
//                opencv_imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, opencv_imgproc.THRESH_BINARY_INV, 11, 2);
//
//        // Find contours
//        RectVector contours = new RectVector();
//        Mat hierarchy = new Mat();
//        opencv_imgproc.findContours(binaryImage, contours, hierarchy, opencv_imgproc.RETR_EXTERNAL, opencv_imgproc.CHAIN_APPROX_SIMPLE);
//
//        for (int i = 0; i < contours.size(); i++) {
//            Rect rect = contours.get(i);
//
//            // Add bounding box for each detected region
//            textCoordinatesList.add(new TextCoordinates("DetectedText", rect.x(), rect.y(), rect.width(), rect.height()));
//        }
//
//        return textCoordinatesList;
//    }
//}
