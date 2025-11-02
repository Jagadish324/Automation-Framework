package com.arc.helper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DragAndDrop {
    public static void dragAndDropFile(String count, String startPoint, String endPoint) {
//        start = start.toLowerCase();
//        end = end.toLowerCase();

        try {
            boolean flag = count.toLowerCase().contains("folder");
            String extension = flag ? "\\" : "\\A_File_1.pdf";
            String command = "explorer.exe /select," + System.getProperty("user.dir") +
                    "\\uploadFile\\" + count + extension;
            Runtime.getRuntime().exec(command);
            Robot robot = new Robot();
            Thread.sleep(3000);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
            if (!flag) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_A);

                robot.keyRelease(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }

            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_LEFT);
            robot.keyRelease(KeyEvent.VK_LEFT);
            robot.keyRelease(KeyEvent.VK_WINDOWS);

            Thread.sleep(3000);

            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);

            Thread.sleep(3000);

            SikuliClass.sikuliDraw(startPoint, endPoint);

            Thread.sleep(3000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_TAB);

            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_F4);

            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_F4);

        } catch (IOException | AWTException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
