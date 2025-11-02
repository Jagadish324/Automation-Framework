package com.arc.helper;

import com.arc.frameworkDevice.helper.ElementWait;
import com.arc.frameworkWeb.helper.ExplicitWait;
import com.arc.frameworkWeb.helper.RobotClass;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class VisualActions {
    private static int x;
    private static int y;

    public static void getCoordinate(String textOrImagePath) {
        OCR ocr = new OCR();

        if (textOrImagePath.contains(".png")) {
            int[] coordinate = ocr.getElementLocationBasedOnImage(textOrImagePath, 1);
            x = coordinate[0];
            y = coordinate[1];
        } else {
            Rectangle rect = ocr.getCoordinateBasedOnText(textOrImagePath, 1);
            x = rect.x;
            y = rect.y;
        }
    }

    public static void getCoordinate(String textOrImagePath, int match) {
        OCR ocr = new OCR();

        if (textOrImagePath.contains(".png")) {
            int[] coordinate = ocr.getElementLocationBasedOnImage(textOrImagePath, match);
            x = coordinate[0];
            y = coordinate[1];
        } else {
            Rectangle rect = ocr.getCoordinateBasedOnText(textOrImagePath, match);
            x = rect.x;
            y = rect.y;
        }
    }

    public static void click(String textOrImagePath) {
        getCoordinate(textOrImagePath);
        RobotClass.clickByCoordinate(x, y);
    }

    public static void click(String textOrImagePath, int match) {
        getCoordinate(textOrImagePath, match);
        RobotClass.clickByCoordinate(x, y);
    }

    public static void doubleClick(String textOrImagePath) {
        getCoordinate(textOrImagePath);
        RobotClass.robotCoordinateDoubleClick(x, y);
    }

    public static void doubleClick(String textOrImagePath, int match) {
        getCoordinate(textOrImagePath, match);
        RobotClass.robotCoordinateDoubleClick(x, y);
    }

    public static void contextClick(String textOrImagePath) {
        getCoordinate(textOrImagePath);
        RobotClass.robotCoordinateContextClick(x, y);
    }

    public static void contextClick(String textOrImagePath, int match) {
        getCoordinate(textOrImagePath, match);
        RobotClass.robotCoordinateContextClick(x, y);
    }

    public static void typeText(String textOrImagePath, String value) {
        getCoordinate(textOrImagePath);
        RobotClass.clickByCoordinate(x, y);
        RobotClass.typeText(value);
    }

    public static void typeText(String textOrImagePath, String value, int deflectionX, int deflectionY) {
        getCoordinate(textOrImagePath);
        RobotClass.clickByCoordinate(x + deflectionX, y + deflectionY);
        ElementWait.hardWait(500);
        RobotClass.typeText(value);
    }

    public static void typeText(String textOrImagePath, String value, int match) {
        getCoordinate(textOrImagePath, match);
        RobotClass.clickByCoordinate(x, y);
        RobotClass.typeText(value);
    }

    public static void copyPasteText(String textOrImagePath, String value) {
        getCoordinate(textOrImagePath);
        RobotClass.clickByCoordinate(x, y);
        RobotClass.pasteInTheTextField(value);
    }

    public static void copyPasteText(String textOrImagePath, String value, int match) {
        getCoordinate(textOrImagePath, match);
        RobotClass.clickByCoordinate(x, y);
        RobotClass.pasteInTheTextField(value);
    }

    public static void copyPasteText(String textOrImagePath, String value, int deflectionX, int deflectionY) {
        getCoordinate(textOrImagePath);
        RobotClass.clickByCoordinate(x + deflectionX, y + deflectionY);
        ElementWait.hardWait(500);
        RobotClass.pasteInTheTextField(value);
    }

    public static void mouseHover(String textOrImagePath) {
        getCoordinate(textOrImagePath);
        RobotClass.mouseHover(x, y);
    }

    public static void mouseHover(String textOrImagePath, int match) {
        getCoordinate(textOrImagePath, match);
        RobotClass.mouseHover(x, y);
    }

    public static Color elementColourDetection(String textOrImagePath) {
        OCR ocr = new OCR();
        Rectangle rect = ocr.getCoordinateBasedOnText(textOrImagePath, 1);
        return ocr.getDominantColor(ocr.getBufferedImage(), rect);
    }
    public static void elementOcrBasedOnScale(String textOrImagePath) {
        OCR ocr = new OCR();
        Rectangle rect = ocr.getCoordinateBasedOnTextScale(textOrImagePath, 1);
    }

    public static Map<Integer, List<int[]>> getAllColoursOnPage() {
        OCR ocr = new OCR();
        return ocr.getAllColorsWithCoordinates(ocr.getBufferedImage());
    }

    //    public static void main(String[] args) {
////        //get colour
////        Color sss= elementColourDetection("New Admin");
////        ElementWait.hardWait(2000);
////        click("New Admin");
//        Color ss = elementColourDetection("New Admin");
//        Get all colour
//        Map<Integer, List<int[]>> mmm = getAllColoursOnPage();
////        copyPasteText("D:\\Automation\\FrameWork\\ArcWebFramework\\arc_automation_bdd_framework_web\\resources\\textboxfield.png","divyanshu@yopmail.com",1);
//        copyPasteText("Enter Username or Email", "divyanshu@yopmail.com", 0, 50);
//        ExplicitWait.hardWait(500);
//        copyPasteText("Enter Password", "123456", 0, 50);
//        ExplicitWait.hardWait(500);
//        click("Sign in",2);
//        System.out.println("ss");
//    }
    public static void main(String[] args) {
//        //get colour
//        elementOcrBasedOnScale("Amirul");
//        System.out.println("############################################");
//        Color cc = elementColourDetection("Amirul");
//        System.out.println("############################################");

//        ElementWait.hardWait(2000);
//        click("New Admin");
//        Color ss = elementColourDetection("New Admin");
//        System.out.println("ss");
//        Get all colour
        Map<Integer, List<int[]>> mmm = getAllColoursOnPage();
        System.out.println("Sds");
        copyPasteText("D:\\Automation\\FrameWork\\ArcWebFramework\\arc_automation_bdd_framework_web\\resources\\img_1.png","divyanshu@yopmail.com",1);


//        copyPasteText("Enter Username or Email", "divyanshu@yopmail.com", 0, 50);
        ExplicitWait.hardWait(500);
//        copyPasteText("Enter Password", "123456", 0, 50);
//        ExplicitWait.hardWait(500);
//        click("Sign in");
//        click("D:\\Automation\\FrameWork\\ArcWebFramework\\arc_automation_bdd_framework_web\\resources\\sign_in.png");
//        ExplicitWait.hardWait(15000);
//        click("Snahasis Sen");
//        ExplicitWait.hardWait(500);
//        click("Anirban");
//        ExplicitWait.hardWait(500);
//        click("Sayan Dey");
//        ExplicitWait.hardWait(500);
//        click("New Admin", 1);
//        ExplicitWait.hardWait(500);
//        click("New Admin");
//        ExplicitWait.hardWait(500);
//        click("Akash Chopra");
//        ExplicitWait.hardWait(500);
//        click("New Admin");
//        ExplicitWait.hardWait(500);
//        click("Sayan Dey", 2);
//        ExplicitWait.hardWait(500);
//        click("Akash Chopra", 2);
//        ExplicitWait.hardWait(500);
//        System.out.println("ss");
    }
}
