package com.arc.helper;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Date;

public class RequestResponseLogger {
    /**
     * This method is used for creating file.
     * @param fileNameWithPath It takes file pathname as a parameter.
     * @return After successful file creation it will return the file.
     */
    public static File createLogFile(String fileNameWithPath) {
        File logFile = new File(fileNameWithPath);
        try {
            if (logFile.createNewFile()) {
                System.out.println("Log file created: " + logFile.getAbsolutePath());
            } else {
                System.out.println("Log file already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating log file: " + e.getMessage());
        }
        return logFile;
    }

    /**
     * This Java method, logRequestResponse, logs a request and its corresponding response to a file. Let’s break down what it does:
     * @param request A string representing the request data.
     * @param response A string representing the response data.
     * @Functionality
     * It constructs a file path for the log file using the current working directory and a unique name.
     * Creates a new log file (or appends to an existing one) at the specified path.
     * Writes the request data, response data, and separators to the log file.
     * Handles any exceptions related to file I/O.
     * @return
     *         The method returns a File object representing the created log file.
     */
    public static File logRequestResponse(String request, String response) {
        String LOG_FILE_PATH = System.getProperty("user.dir")+ "\\RequestResponseLog\\"+RandomTextGenerator.uniqueClientId()+".txt";
        File logFile = createLogFile(LOG_FILE_PATH);

        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write("Request:\n");
            writer.write(request + "\n");
            writer.write("------------------------------\n");
            writer.write("Response:\n");
            writer.write(response + "\n");
            writer.write("------------------------------\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
        return logFile;
    }

    /**
     * This method used to convert file to byte array.
     * @param file It takes File as input. Which you want to convert as byte array.
     * @return It will return a byte array.
     */
    public static byte[] convertFileToByteArray(File file) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (FileInputStream fis = new FileInputStream(file)
        ) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        }catch (IOException ignore){

        }
        return bos.toByteArray();
    }

    /**
     * This method used for logging purpose. This method will return some basic information of any request.
     * @param request This method will take a request object as parameter.
     * @return This method will return some basic information of any request.
     */
    public static String getAllDataFromRequest(RequestSpecification request){
        return  "Request method :" + ((RequestSpecificationImpl) request).getMethod() + "\n" +
                "Request URI :" + ((RequestSpecificationImpl) request).getURI() + "\n"+
                "Proxy :" + ((RequestSpecificationImpl) request).getProxySpecification() + "\n"+
                "Request params :" + ((RequestSpecificationImpl) request).getRequestParams() + "\n"+
                "Query params :" + ((RequestSpecificationImpl) request).getQueryParams() + "\n"+
                "Form params :" + ((RequestSpecificationImpl) request).getFormParams() + "\n"+
                "Path params :" + ((RequestSpecificationImpl) request).getPathParams() + "\n"+
                "Headers :" + ((RequestSpecificationImpl) request).getHeaders() + "\n"+
                "Cookies :" + ((RequestSpecificationImpl) request).getCookies() + "\n"+
                "Multiparts :" + ((RequestSpecificationImpl) request).getMultiPartParams() + "\n"+
                "Body :" + ((RequestSpecificationImpl) request).getBody() + "\n";
    }

    /**
     * Adds an attachment to the ReportPortal log with the specified message and file.
     *
     * @param message The message to be logged along with the attachment.
     * @param file    The file to be attached to the log entry.
     */
    public static void addAttachment(String message, File file) {
        ReportPortal.emitLog(message, LogLevel.INFO.name(), new Date(), file);
    }


}
