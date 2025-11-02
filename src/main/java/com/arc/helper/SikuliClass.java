package com.arc.helper;

import com.arc.frameworkWeb.helper.ExplicitWait;
import com.arc.frameworkWeb.utility.CONSTANT;
import org.sikuli.script.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.List;

public class SikuliClass {
    public static void sikuliDoubleClick(String fileName){
        String path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/" + fileName;
        Screen screen=new Screen();

        ExplicitWait.hardWait(3000);
        try {
            screen.find(path); //identify pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        try {
            screen.doubleClick(path); //click pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);

        }
    }
    public static void sikuliClick(String fileName){
        String path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/" + fileName;
        Screen screen=new Screen();
        ExplicitWait.hardWait(3000);
        try {
            screen.find(path); //identify pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        try {
            screen.click(path); //click pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);

        }
    }
    public static void sikuliClickByText(String textValue){
//        String path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/" + fileName;
        Screen screen=new Screen();
        ExplicitWait.hardWait(3000);
        try {
            screen.findText(textValue).click(); //click pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);

        }
    }
    public static void sikuliRightClick(String fileName){
        String path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/" + fileName;
        Screen screen=new Screen();
        ExplicitWait.hardWait(3000);
        try {
            screen.find(path); //identify pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        try {
            screen.rightClick(path); //click pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);

        }
    }
    public static void sikuliDraw(String startFile, String endFile){
        String path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/";
        Screen screen=new Screen();
        ExplicitWait.hardWait(3000);
        try {
            screen.find(path+startFile); //identify pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        Pattern startPoint = new Pattern(path+startFile);
        Pattern endPoint = new Pattern(path +endFile);
        try {
            screen.drag(startPoint);
            screen.dropAt(endPoint);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
    }
    public static void dragAndDrop(String fileLocation, String dropLocation){
        String path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/";
        Screen screen=new Screen();
        ExplicitWait.hardWait(3000);
        try {
            screen.find(path+fileLocation); //identify pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        Pattern startPoint = new Pattern(path+fileLocation);
        Pattern endPoint = new Pattern(path +dropLocation);
        try {
            screen.drag(startPoint);
            screen.dropAt(endPoint);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
    }
    public static void dragAndDropByText(String startPoint, String endPoint){
        String path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/";
        Screen screen=new Screen();
        ExplicitWait.hardWait(3000);

        try {
            screen.drag(screen.findText(startPoint));
            screen.dropAt(screen.findText(endPoint));
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
    }
    public static void sikuliHover(String fileName){
        String path;
        if(CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
            path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/" + fileName;
        }else{
            path = System.getProperty("user.dir") + "\\src\\main\\resources\\sikuliImage\\" + fileName;
        }

        Screen screen=new Screen();
        ExplicitWait.hardWait(5000);
        try {
            screen.find(path); //identify pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        try {
            if(CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
                screen.click(path);
            }else {
                screen.mouseMove(path);
            }
            //click pause button
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Match> textPresent(String textAvaliable){
        Screen screen=new Screen();
        return screen.findAllText(textAvaliable);
    }
    public static void zoomOut(String fileName, int zoomLevel){
        String path;
        if(CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
            path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/" + fileName;
        }else{
            path = System.getProperty("user.dir") + "\\src\\main\\resources\\sikuliImage\\" + fileName;
        }
        Screen screen=new Screen();
        try {
            screen.wheel(screen.find(path), Button.WHEEL_DOWN,  zoomLevel);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }

    }
    public static void zoomIn(String fileName,  int zoomLevel){
        String path;
        if(CONSTANT.PLATFORM.equalsIgnoreCase("mac")) {
            path = System.getProperty("user.dir") + "/src/main/resources/sikuliImage/" + fileName;
        }else{
            path = System.getProperty("user.dir") + "\\src\\main\\resources\\sikuliImage\\" + fileName;
        }
        Screen screen=new Screen();
        try {
            screen.wheel(screen.find(path), Button.WHEEL_UP,  zoomLevel);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
    }
    public static void zoomOutByText(String text, int zoomLevel){

        Screen screen=new Screen();
        try {
            screen.wheel(screen.findText(text), Button.WHEEL_DOWN,  zoomLevel);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }

    }
    public static void zoomInByText(String text,  int zoomLevel){

        Screen screen=new Screen();
        try {
            screen.wheel(screen.findText(text), Button.WHEEL_UP,  zoomLevel);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
    }
    public static int getCoordinateByTextX(String text){
        Screen screen = new Screen();
        Match match = null;
        try {
            match = screen.findText(text);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        return match.getTarget().getX();
    }
    public static int getCoordinateByTextY(String text){
        Screen screen = new Screen();
        Match match = null;
        try {
            match = screen.findText(text);
        } catch (FindFailed e) {
            throw new RuntimeException(e);
        }
        return match.getTarget().getY();
    }
}
