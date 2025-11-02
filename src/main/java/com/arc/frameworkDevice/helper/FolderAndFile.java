package com.arc.frameworkDevice.helper;

import com.arc.frameworkDevice.utility.CONSTANT;

import java.io.IOException;

public class FolderAndFile extends CommonHelper {
    /**
     * Pulls a file from a mobile device to a laptop.
     *
     * @param deviceFilePath The path of the file on the device.
     * @param laptopSavePath The path where the file should be saved on the laptop.
     */
    public static void pullFileFromDeviceToRepo(String deviceFilePath, String laptopSavePath) {
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
                Runtime.getRuntime().exec("ideviceinstaller -u " + CONSTANT.UDID + " -o " + deviceFilePath + " " + laptopSavePath);
                System.out.println("File transferred successfully using ideviceinstaller!");
            } catch (IOException e) {
                System.out.println("ideviceinstaller failed.");
            }
        }
    }
}
