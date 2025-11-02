package com.arc.frameworkDesktop.helper;

import com.arc.frameworkDesktop.utility.CONSTANT;

import java.io.IOException;

public class FolderAndFile extends CommonHelper {
    public static void pullFileFromDeviceToRepo(String deviceFilePath,String laptopSavePath) {


//        String deviceFilePath = "/path/to/file/on/device";
//        String laptopSavePath = "/path/to/save/on/laptop";
//        byte[] fileBase64 = deviceDriver.getFileDetector("/path/to/device/foo.bar");
        // Attempt to use pullFile method (Android and iOS)
        try {
            Runtime.getRuntime().exec("adb pull " + deviceFilePath + " " + laptopSavePath);
            System.out.println("File transferred successfully using pullFile!");
        } catch (Exception e) {
            System.out.println("pullFile failed. Attempting alternative methods...");
        }

        // Android-specific fallback using adb pull
        if (CONSTANT.PLATFORM_NAME.equalsIgnoreCase("android")) {
            try {
                Runtime.getRuntime().exec("adb pull " + deviceFilePath + " " + laptopSavePath);
                System.out.println("File transferred successfully using adb pull!");
            } catch (IOException e) {
                System.out.println("adb pull failed.");
            }
        }

        // iOS-specific fallback using ideviceinstaller (requires installation)
        if (CONSTANT.PLATFORM_NAME.equalsIgnoreCase("iOS")) {
            try {
                Runtime.getRuntime().exec("ideviceinstaller -u "+ CONSTANT.UDID+" -o " + deviceFilePath + " " + laptopSavePath);
                System.out.println("File transferred successfully using ideviceinstaller!");
            } catch (IOException e) {
                System.out.println("ideviceinstaller failed.");
            }
        }
    }
}
