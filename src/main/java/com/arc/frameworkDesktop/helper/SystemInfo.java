package com.arc.frameworkDesktop.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemInfo {
    public static double memoryUsage(String processName) {
//    public static double main(String args[]){
        double memoryUsageMB = 0;
        try {
            Process processCPU = Runtime.getRuntime().exec("wmic process where name='" + processName + "' get KernelModeTime,UserModeTime");
            BufferedReader readerCPU = new BufferedReader(new InputStreamReader(processCPU.getInputStream()));
            String lineCPU;
            int countCPU = 0;
            long kernelModeTime = 0;
            long userModeTime = 0;
            while ((lineCPU = readerCPU.readLine()) != null) {
                if (countCPU++ > 0) {
                    String[] tokens = lineCPU.trim().split("\\s+");
                    if (tokens.length >= 2) {
                        kernelModeTime = Long.parseLong(tokens[0]);
                        userModeTime = Long.parseLong(tokens[1]);
                    }
                }
            }
            Process processMemory = Runtime.getRuntime().exec("wmic process where name='" + processName + "' get WorkingSetSize");
            BufferedReader readerMemory = new BufferedReader(new InputStreamReader(processMemory.getInputStream()));
            String lineMemory;
            int countMemory = 0;
            long memoryUsage = 0;
            while ((lineMemory = readerMemory.readLine()) != null) {
                if (countMemory++ > 0) {
                    try {
                        memoryUsage = Long.parseLong(lineMemory.trim());
                    } catch (NumberFormatException e) {

                    }
                }
            }
            double cpuUsage = ((double) (kernelModeTime + userModeTime)) / 10000000; // Convert to percentage
            memoryUsageMB = memoryUsage / (1024 * 1024.0);
//            System.out.println("CPU Usage: " + cpuUsage + " %");
            System.out.println("Memory Usage: " + memoryUsageMB + " MB");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return memoryUsageMB;
    }
}
