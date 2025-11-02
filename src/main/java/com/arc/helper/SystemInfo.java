package com.arc.helper;

public class SystemInfo {
    public static String getOsName() {
        return System.getProperty("os.name").toLowerCase();
    }
}
