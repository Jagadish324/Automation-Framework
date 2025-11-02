package com.arc.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RunPythonCommand {
    public static String runPythonScript(String command, String scriptPath, String pdfPath, String workingDir) {
        ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command, scriptPath, pdfPath));
        processBuilder.directory(new java.io.File(workingDir));
        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                output.append("ERROR: ").append(line).append("\n");
            }
            int exitCode = process.waitFor();
            output.append("Exited with code: ").append(exitCode).append("\n");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            output.append("Exception: ").append(e.getMessage()).append("\n");
        }

        return output.toString();
    }
}
