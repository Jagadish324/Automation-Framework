package com.arc.helper;

import com.arc.frameworkWeb.helper.CommonHelper;
import com.arc.frameworkWeb.helper.ExplicitWait;

import com.arc.frameworkWeb.utility.CONSTANT;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static com.arc.frameworkWeb.helper.UploadFile.setClipboardData;

public class FileUpload extends CommonHelper {
    public static void uploadFile(String resourcePath) throws AWTException {
        Robot robot = new Robot();
        if (CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
//            String path = System.getProperty("user.dir") + "/resources/testData/" + resourcePath;
            String path = System.getProperty("user.dir") +"/"+  resourcePath;
            StringSelection ss = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);
            ExplicitWait.hardWait(500);
            Dimension i = webDriver
                    .manage().window().getSize();
            System.out.println("Dimension x and y :" + i.getWidth() + " " + i.getHeight());

            int x = (i.getWidth() / 2) + 80;
            int y = (i.getHeight() / 2) + 5;
            System.out.println(x + "+++++++++++++++++++" + y);
//4. Now, adjust the x and y coordinates with reference to the Windows popup size on the screen
//e.g. On current screen , Windows popup displays on almost 1/4th of the screen . So with reference to the same, file name x and y position is specified.
//Note : Please note that coordinates calculated in this sample i.e. x and y may vary as per the screen resolution settings
            robot.mouseMove(x, y);
//Clicks Left mouse button
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            System.out.println("Browse button clicked");
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_A);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_V);
//Press Enter key to close the Goto window and Upload window
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } else {
            String path = System.getProperty("user.dir")  +"\\"+ resourcePath;
            StringSelection ss = new StringSelection(path);
            ExplicitWait.hardWait(500);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            ExplicitWait.hardWait(500);
            //imitate mouse events like ENTER, CTRL+C, CTRL+V
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(500);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }


    public static void uploadAllFilesAndFolder(String resourcePath) throws AWTException {
        Robot robot = new Robot();
        if (CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
//            String path = System.getProperty("user.dir") + "/resources/testData/" + resourcePath;
            String path = System.getProperty("user.dir") +"/"+  resourcePath;
            StringSelection ss = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);
            ExplicitWait.hardWait(500);
            Dimension i = webDriver
                    .manage().window().getSize();
            System.out.println("Dimension x and y :" + i.getWidth() + " " + i.getHeight());

            int x = (i.getWidth() / 2) + 80;
            int y = (i.getHeight() / 2) + 5;
            System.out.println(x + "+++++++++++++++++++" + y);
//4. Now, adjust the x and y coordinates with reference to the Windows popup size on the screen
//e.g. On current screen , Windows popup displays on almost 1/4th of the screen . So with reference to the same, file name x and y position is specified.
//Note : Please note that coordinates calculated in this sample i.e. x and y may vary as per the screen resolution settings
            robot.mouseMove(x, y);
//Clicks Left mouse button
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            System.out.println("Browse button clicked");
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_A);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_DELETE);
            robot.keyRelease(KeyEvent.VK_DELETE);
            ExplicitWait.hardWait(500);
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } else {
            String path = System.getProperty("user.dir")  + "\\"+resourcePath;
            StringSelection ss = new StringSelection(path);
            ExplicitWait.hardWait(500);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            ExplicitWait.hardWait(500);
            //imitate mouse events like ENTER, CTRL+C, CTRL+V
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(500);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }

    public static boolean uploadMultipleFile(String fileName) {
        boolean status = false;
        try {
            String finalPath = "";
            char ch = '"';
            List<String> allFileNames = new ArrayList<>();
            if (fileName.contains(",")) {
                String[] files = fileName.split(",");
                for (int i = 0; i < files.length; i++) {
                    allFileNames.add(files[i]);
                }
            } else {
                allFileNames.add(fileName);
            }
            finalPath = System.getProperty("user.dir");
            for (int l = 0; l < allFileNames.size(); l++) {
                //finalPath=finalPath+System.getProperty("user.dir") + "\\uploadFile\\"+allFileNames.get(l)+ "\n ";
                finalPath = finalPath + ch + allFileNames.get(l) + ch + " ";
            }
            System.out.println("Final path " + finalPath);
            setClipboardData(finalPath);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            status = true;

        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return status;

    }

    public static boolean uploadMultipleFile(String folderPath,String fileName) {
        boolean status = false;
        try {
            String finalPath = "";
            char ch = '"';
            List<String> allFileNames = new ArrayList<>();
            if (fileName.contains(",")) {
                String[] files = fileName.split(",");
                for (int i = 0; i < files.length; i++) {
                    allFileNames.add(files[i]);
                }
            } else {
                allFileNames.add(fileName);
            }
            finalPath = System.getProperty("user.dir") +"\\"+ folderPath + "\\";
            for (int l = 0; l < allFileNames.size(); l++) {
                //finalPath=finalPath+System.getProperty("user.dir") + "\\uploadFile\\"+allFileNames.get(l)+ "\n ";
                finalPath = finalPath + ch + allFileNames.get(l) + ch + " ";
            }
            System.out.println("Final path " + finalPath);
            setClipboardData(finalPath);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            status = true;

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return status;
    }

    public static void deleteFolderAndFilesInAppData(String filePath) {
        String folderPath = System.getenv("APPDATA") + File.separator + filePath;
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder does not exist or is not a directory: " + folderPath);
            return;
        }
        deleteContents(folder);
        System.out.println("Contents of folder have been deleted successfully.");
    }

    private static void deleteContents(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursively delete contents of subfolders
                    deleteContents(file);
                }
                // Delete file or empty subfolder
                file.delete();
            }
        }
    }

    public static void uploadFileUsingSendKeys(By locator, String fileName, String path) {
        String finalPath = "";
        String contantPath = "";
        List<String> allFileNames = new ArrayList<>();
        if (fileName.contains(",")) {
            String[] files = fileName.split(",");
            for (int i = 0; i < files.length; i++) {
                allFileNames.add(files[i]);
            }
        } else {
            allFileNames.add(fileName);
        }

        contantPath = System.getProperty("user.dir") + "\\"+path+"\\";
        for (int l = 0; l < allFileNames.size(); l++) {
            //finalPath=finalPath+System.getProperty("user.dir") + "\\uploadFile\\"+allFileNames.get(l)+ "\n ";
            if (allFileNames.size() == 1) {
                finalPath = contantPath + allFileNames.get(l);
            } else {
                if (!(l == allFileNames.size() - 1)) {
                    finalPath = finalPath + contantPath + allFileNames.get(l) + "\n";
                } else {
                    finalPath = finalPath + contantPath + allFileNames.get(l);
                }
            }
        }
        //DynamicWait.visibilityOfElement(locator);
        System.out.println("final path " + finalPath);
        CommonHelper.webDriver.findElement(locator).sendKeys(finalPath);

    }
}
