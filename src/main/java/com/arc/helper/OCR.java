package com.arc.helper;

import com.arc.frameworkDesktop.helper.RobotClass;
import com.arc.utility.CONSTANT;
import net.sourceforge.tess4j.*;

import nu.pattern.OpenCV;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class OCR {
    public static double scaleFactor;

    static {
        OpenCV.loadShared();
    }

    public static String getExtractedText(String tessdataPath, String pdfFilePath) {
        String tesseractDataPath = "";
        if (tessdataPath.contains("default")) {
            String userHomeDirectory = System.getProperty("user.home");
            tesseractDataPath = userHomeDirectory + "\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata";
        } else {
            tesseractDataPath = tessdataPath;
        }
        try {
            String extractedText = extractTextFromPDF(pdfFilePath, tesseractDataPath);
            System.out.println("Extracted Text:");
            System.out.println(extractedText);
            return extractedText;
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getExtractedTextFromFile(String tessdataPath, String pdfFilePath) {
        String tesseractDataPath = "";
        if (tessdataPath.contains("default")) {
            String userHomeDirectory = System.getProperty("user.home");
            tesseractDataPath = userHomeDirectory + "\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata";
        } else {
            tesseractDataPath = tessdataPath;
        }
        try {
            String extractedText = extractTextFromFile(pdfFilePath, tesseractDataPath);
            System.out.println("Extracted Text:");
            System.out.println(extractedText);
            return extractedText;
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractTextFromPDF(String pdfFilePath, String tesseractDataPath) throws IOException, TesseractException {
        File file = new File(pdfFilePath);
        PDDocument document = Loader.loadPDF(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tesseractDataPath);
        tesseract.setPageSegMode(TessAPI.TessPageSegMode.PSM_SINGLE_BLOCK);

        StringBuilder extractedText = new StringBuilder();

        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage image1 = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            BufferedImage image = preprocessImage(image1);
            if (image.getWidth() < 3 || image.getHeight() < 3) {
                System.err.println("Skipping small image on page " + page);
                continue;
            }
            try {
                String text = tesseract.doOCR(image);
                extractedText.append(text).append("\n");
            } catch (TesseractException e) {
                System.err.println("Could not extract text from page " + page + ": " + e.getMessage());
            }
        }
        document.close();
        return extractedText.toString();
    }

    public static String extractTextFromFile(String filePath, String tesseractDataPath) throws IOException, TesseractException {
        File file = new File(filePath);
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tesseractDataPath);
        tesseract.setPageSegMode(TessAPI.TessPageSegMode.PSM_SINGLE_BLOCK);

        StringBuilder extractedText = new StringBuilder();
        String fileExtension = getFileExtension(file);

        switch (fileExtension.toLowerCase()) {
            case "pdf":
                PDDocument document = Loader.loadPDF(file);
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                for (int page = 0; page < document.getNumberOfPages(); ++page) {
                    BufferedImage image1 = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                    BufferedImage image = preprocessImage(image1);
                    if (image.getWidth() < 3 || image.getHeight() < 3) {
                        System.err.println("Skipping small image on page " + page);
                        continue;
                    }
                    try {
                        String text = tesseract.doOCR(image);
                        extractedText.append(text).append("\n");
                    } catch (TesseractException e) {
                        System.err.println("Could not extract text from page " + page + ": " + e.getMessage());
                    }
                }
                document.close();
                break;
            case "png":
            case "tiff":
            case "jpeg":
            case "jpg":
            case "tif":
                BufferedImage image = ImageIO.read(file);
                BufferedImage processedImage = preprocessImage(image);
                try {
                    String text = tesseract.doOCR(processedImage);
                    extractedText.append(text).append("\n");
                } catch (TesseractException e) {
                    System.err.println("Could not extract text from image file " + file.getName() + ": " + e.getMessage());
                }
                break;
            default:
                System.err.println("Unsupported file type: " + fileExtension);
        }
        return extractedText.toString();
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }

    public static List<Word> word;

    public static Rectangle getCoordinateBasedOnText(String textToGet, int position) {
        if (word == null || CONSTANT.REINITIALIZE_OCR) {//Constant is implemented by the request of Snahasis.
            word = intializeOCR(preprocessImage(getBufferedImage()));
        }
        Rectangle rectangle = null;
        try {
            rectangle = getCoordinate(word, textToGet, position);
        } catch (Exception e) {
            word = intializeOCR(preprocessImage(getBufferedImage()));
            rectangle = getCoordinate(word, textToGet, position);
        }
        return rectangle;
    }

    public static Rectangle getCoordinateBasedOnTextScale(String textToGet, int position) {
        if (word == null) {
            word = intializeOCR(preprocessImage(getScaleBufferedImage()));
        }
        Rectangle rectangle = null;
        try {
            rectangle = getCoordinate(word, textToGet, position);
        } catch (Exception e) {
            word = intializeOCR(preprocessImage(getBufferedImage()));
            rectangle = getCoordinate(word, textToGet, position);
        }
        return rectangle;
    }

    public static BufferedImage getBufferedImage() {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            double scaleFactor = 1;
            int width = (int) (screenSize.width * scaleFactor);
            int height = (int) (screenSize.height * scaleFactor);
            Rectangle screenRect = new Rectangle(width, height);
            BufferedImage image = null;
            try {
                image = new Robot().createScreenCapture(screenRect);
            } catch (AWTException ex) {
                throw new RuntimeException(ex);
            }
            return image;
        } catch (Exception e) {
            return null;
        }
    }

    public static BufferedImage getScaleBufferedImage() {
        try {
            // Get the default toolkit
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            // Get the screen size
            Dimension screenSize = toolkit.getScreenSize();

            // Get the screen resolution
            int screenResolution = toolkit.getScreenResolution();

            // Calculate the scale factor based on the screen resolution
            scaleFactor = 300.0 / screenResolution; // Change 300 to your desired resolution
            // Create a screen rectangle
            Rectangle screenRect = new Rectangle(screenSize);

            // Capture the screen image
            BufferedImage image = new Robot().createScreenCapture(screenRect);

            // Scale the image if needed
            if (scaleFactor != 1) {
                int width = (int) (screenSize.width * scaleFactor);
                int height = (int) (screenSize.height * scaleFactor);
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                BufferedImage scaledBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = scaledBufferedImage.createGraphics();
                g2d.drawImage(scaledImage, 0, 0, null);
                g2d.dispose();
                image = scaledBufferedImage;
            }

            // Return the captured image
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<Word> intializeOCR(BufferedImage image) {
        String tessdataPath = "default";
        String tesseractDataPath = "";
        if (tessdataPath.contains("default")) {
            String userHomeDirectory = System.getProperty("user.home");
//            tesseractDataPath = userHomeDirectory + "\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata";
//            tesseractDataPath = userHomeDirectory + "\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata_best";
            tesseractDataPath = userHomeDirectory + "\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata_fast";
        } else {
            tesseractDataPath = tessdataPath;
        }
        // Initialize Tesseract OCR
        ITesseract instance = new Tesseract();
        instance.setPageSegMode(TessAPI.TessPageSegMode.PSM_SINGLE_BLOCK);
        instance.setTessVariable("preserve_interword_spaces", "1");
        instance.setDatapath(tesseractDataPath); // Set path to tessdata
        instance.setLanguage("eng");
        String result = null;
        try {
            result = instance.doOCR(image);
        } catch (TesseractException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Recognized text: " + result);
        List<Word> words = instance.getWords(image, ITessAPI.TessPageIteratorLevel.RIL_WORD);
        return words;
    }

    public static Rectangle getCoordinate(List<Word> words, String searchText, int occurrence) {
        String[] arr = searchText.split(" ");
        List<Rectangle> occurrences = new ArrayList<>();
        int phraseIndex = 0;
        int startIndex = -1;

        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            if (word.getText().equalsIgnoreCase(arr[phraseIndex])) {
                if (phraseIndex == 0) {
                    startIndex = i;
                }
                phraseIndex++;
                if (phraseIndex == arr.length) {
                    int endIndex = i;
                    int meanIndex = (startIndex + endIndex) / 2;
                    occurrences.add(words.get(meanIndex).getBoundingBox());
                    phraseIndex = 0;
                    startIndex = -1;
                }
            } else {
                phraseIndex = 0;
                startIndex = -1;
            }
        }

        if (occurrences.isEmpty()) {
            System.out.println("Phrase not found.");
            return null;
        }

        if (occurrence <= 0 || occurrence > occurrences.size()) {
            System.out.println("Invalid occurrence number. Total occurrences: " + occurrences.size());
            return null;
        }

        Rectangle rect = occurrences.get(occurrence - 1);
        return rect;
    }

    public static Rectangle getCoordinate(List<Word> words, String searchText) {
//        String searchText = "Entries per page";
        String[] arr = searchText.split(" ");
        int phraseIndex = 0;
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            if (word.getText().equalsIgnoreCase(arr[phraseIndex])) {
                if (phraseIndex == 0) {
                    startIndex = i;
                }
                phraseIndex++;
                if (phraseIndex == arr.length) {
                    endIndex = i;
                    break;
                }
            } else {
                phraseIndex = 0;
                startIndex = -1;
            }
        }
        Rectangle rect = null;
        if (startIndex != -1 && endIndex != -1) {
            int meanIndex = (startIndex + endIndex) / 2;
            Word meanWord = words.get(meanIndex);
            rect = meanWord.getBoundingBox();
            System.out.println("Coordinates of '" + searchText + "': " + rect.x + ", " + rect.y);
//            RobotClass.robotMouseHoverCoordinateClick(rect.x, rect.y);
        } else {
            System.out.println("Phrase not found.");
        }
        return rect;
    }

    public static BufferedImage preprocessImage(BufferedImage image) {
        Mat src = bufferedImageToMat(image);
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.GaussianBlur(gray, gray, new Size(5, 5), 0);
//        try {
//            ImageIO.write(image, "png", new File("screenshot.png"));
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
        return matToBufferedImage(gray);
    }

    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer); // Get all the pixels
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }

    private static Mat bufferedImageToMat(BufferedImage bi) {
        BufferedImage convertedImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        convertedImage.getGraphics().drawImage(bi, 0, 0, null);
        Mat mat = new Mat(convertedImage.getHeight(), convertedImage.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) convertedImage.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);

        return mat;
    }

    public static Color getDominantColor(BufferedImage image, Rectangle boundingBox) {
        Map<Integer, Integer> colorMap = new HashMap<>();
        int maxCount = 0;
        Color dominantColor = null;

        for (int x = boundingBox.x; x < boundingBox.x + boundingBox.width; x++) {
            for (int y = boundingBox.y; y < boundingBox.y + boundingBox.height; y++) {
                int rgb = image.getRGB(x, y);
                colorMap.put(rgb, colorMap.getOrDefault(rgb, 0) + 1);

                if (colorMap.get(rgb) > maxCount) {
                    maxCount = colorMap.get(rgb);
                    dominantColor = new Color(rgb);
                }
            }
        }
        System.out.println(dominantColor + "*******************************");
        return dominantColor;
    }

    public static Map<Integer, List<int[]>> getAllColorsWithCoordinates(BufferedImage image) {
        Map<Integer, List<int[]>> colorMap = new HashMap<>();

        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);

                if (!colorMap.containsKey(rgb)) {
                    colorMap.put(rgb, new ArrayList<>());
                }
                colorMap.get(rgb).add(new int[]{x, y});
            }
        }
        for (Map.Entry<Integer, List<int[]>> entry : colorMap.entrySet()) {
            Color color = new Color(entry.getKey());
            List<int[]> coordinates = entry.getValue();
            System.out.println("Color: " + color);
            for (int[] coord : coordinates) {
                System.out.println("  Coordinate: (" + coord[0] + ", " + coord[1] + ")");
            }
        }
        return colorMap;
    }

    public static int[] getElementLocationBasedOnImage(String image) {
        BufferedImage largeBufferedImage = getBufferedImage();
        Mat largeImage = bufferedImageToMat(largeBufferedImage);
        Mat smallImage = Imgcodecs.imread(image);
        Mat result = new Mat();
        Imgproc.matchTemplate(largeImage, smallImage, result, Imgproc.TM_CCOEFF_NORMED);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        double threshold = 0.99;
        List<Point> matchLocations = new ArrayList<>();
        Mat mask = Mat.zeros(result.size(), CvType.CV_8UC1);
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                double[] value = result.get(i, j);
                if (value[0] > threshold) {
                    if (mask.get(i, j)[0] == 0) {
                        Imgproc.floodFill(mask, new Mat(), new Point(j, i), new Scalar(1));
                        matchLocations.add(new Point(j, i));
                    }
                }
            }
        }
        System.out.println("Total matches found: " + matchLocations.size());
        for (Point matchLoc : matchLocations) {
            Imgproc.rectangle(largeImage, matchLoc, new Point(matchLoc.x + smallImage.width(), matchLoc.y + smallImage.height()), new Scalar(0, 255, 0));
        }
        try {
            ImageIO.write(matToBufferedImage(largeImage), "png", new File("screenshot.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (!matchLocations.isEmpty()) {
            Point matchLoc = matchLocations.get(0);
            int centerX = (int) (matchLoc.x + smallImage.width() / 2);
            int centerY = (int) (matchLoc.y + smallImage.height() / 2);
            int[] coordinate = {centerX, centerY};
            return coordinate;
        }
        return null;
    }

    public static int[] getElementLocationBasedOnImageTextBox(String image) {
        BufferedImage largeBufferedImage = getBufferedImage();
        Mat largeImage = bufferedImageToMat(largeBufferedImage);
        Mat smallImage = Imgcodecs.imread(image);
        Mat result = new Mat();
        Imgproc.matchTemplate(largeImage, smallImage, result, Imgproc.TM_CCOEFF_NORMED);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        double threshold = 0.99;
        List<Point> matchLocations = new ArrayList<>();
        Mat mask = Mat.zeros(result.size(), CvType.CV_8UC1);
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                double[] value = result.get(i, j);
                if (value[0] > threshold) {
                    if (mask.get(i, j)[0] == 0) {
                        Imgproc.floodFill(mask, new Mat(), new Point(j, i), new Scalar(1));
                        matchLocations.add(new Point(j, i));
                    }
                }
            }
        }
        System.out.println("Total matches found: " + matchLocations.size());
        for (Point matchLoc : matchLocations) {
            Imgproc.rectangle(largeImage, matchLoc, new Point(matchLoc.x + smallImage.width(), matchLoc.y + smallImage.height()), new Scalar(0, 255, 0));
        }
        try {
            ImageIO.write(matToBufferedImage(largeImage), "png", new File("screenshot.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (!matchLocations.isEmpty()) {
            Point matchLoc = matchLocations.get(0);
            int centerX = (int) (matchLoc.x + smallImage.width() / 2);
            int centerY = (int) (matchLoc.y + smallImage.height() + smallImage.height() / 2);
            int[] coordinate = {centerX, centerY};
            return coordinate;
        }
        return null;
    }

    public static int[] getElementLocationBasedOnImage(String image, int matchNumber) {
        BufferedImage largeBufferedImage = getBufferedImage();
        Mat largeImage = bufferedImageToMat(largeBufferedImage);
        Mat smallImage = Imgcodecs.imread(image);
        Mat result = new Mat();
        Imgproc.matchTemplate(largeImage, smallImage, result, Imgproc.TM_CCOEFF_NORMED);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        double threshold = 0.99;
        List<Point> matchLocations = new ArrayList<>();
        Mat mask = Mat.zeros(result.size(), CvType.CV_8UC1);
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.cols(); j++) {
                double[] value = result.get(i, j);
                if (value[0] > threshold) {
                    if (mask.get(i, j)[0] == 0) {
                        Imgproc.floodFill(mask, new Mat(), new Point(j, i), new Scalar(1));
                        matchLocations.add(new Point(j, i));
                    }
                }
            }
        }
        System.out.println("Total matches found: " + matchLocations.size());
        for (Point matchLoc : matchLocations) {
            Imgproc.rectangle(largeImage, matchLoc, new Point(matchLoc.x + smallImage.width(), matchLoc.y + smallImage.height()), new Scalar(0, 255, 0));
        }
        try {
            ImageIO.write(matToBufferedImage(largeImage), "png", new File("screenshot.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (!matchLocations.isEmpty() && matchNumber <= matchLocations.size()) {
            Point matchLoc = matchLocations.get(matchNumber - 1); // Get the nth match (1-based index)
            int centerX = (int) (matchLoc.x + smallImage.width() / 2);
            int centerY = (int) (matchLoc.y + smallImage.height() / 2);
            int[] coordinate = {centerX, centerY};
            return coordinate;
        }
        return null;
    }
}
