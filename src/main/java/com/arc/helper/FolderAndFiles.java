package com.arc.helper;

import com.arc.frameworkWeb.helper.Navigate;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.sql.*;

public class FolderAndFiles {


    public static void deleteFolder(String folderPath) {
        Path directory = Paths.get(folderPath);
        if (Files.exists(directory)) {
            try {
                Files.walk(directory)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void clearFolder(String folderPath) throws IOException {
        Path directory = Paths.get(folderPath);
        if (Files.exists(directory) && Files.isDirectory(directory)) {
            try (Stream<Path> paths = Files.walk(directory)) {
                paths.filter(Files::isRegularFile)
                        .forEach(file -> {
                            try {
                                Files.delete(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
        }
    }

    public static String getZipFilePath(String folderPath) {
        try {
            return Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".zip"))
                    .findFirst()
                    .map(Path::toString)
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String extractZipFile(String zipFilePath, String extractFolder) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path entryPath = Paths.get(extractFolder, entry.getName());
                Files.createDirectories(entryPath.getParent());
                try (OutputStream fos = Files.newOutputStream(entryPath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = zis.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
                zis.closeEntry();
            }
        }
        return zipFilePath.substring(0, zipFilePath.length() - ".zip".length());
    }

    public static void extractZipFile(String zipFile) throws IOException {
        ZipArchiveInputStream zipIn = new ZipArchiveInputStream(new FileInputStream(zipFile));
        ZipArchiveEntry entry = null;
        while ((entry = zipIn.getNextZipEntry()) != null) {
            File destFile = new File(entry.getName());
            // Ensure parent directories exist
            destFile.getParentFile().mkdirs();
            try (FileOutputStream out = new FileOutputStream(destFile)) {
                IOUtils.copy(zipIn, out);
            }
        }
        zipIn.close();
    }

    public static String findSQLiteFile(String folder, String sqlFileName) {
        try {
            return Files.walk(Paths.get(folder))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(sqlFileName))
                    .findFirst()
                    .map(Path::toString)
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFilePath(String baseDir, String targetFile) {
        File downloadDir = new File(baseDir);
        File[] subDirs = downloadDir.listFiles(File::isDirectory);
        File targetFilePath = null;
        for (File subDir : subDirs) {
            File potentialFile = new File(subDir, targetFile);
            if (potentialFile.exists()) {
                targetFilePath = potentialFile;
                break;
            }
        }
        if (targetFilePath != null) {

            System.out.println("File path: " + targetFilePath.getAbsolutePath());
            return targetFilePath.getAbsolutePath();
        } else {
            System.out.println("File not found in any subdirectories.");
            return null;
        }
    }

    public static JSONArray runQueryOnSQLite(String sqliteFilePath, String sqlQuery) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + sqliteFilePath);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                jsonObject.put(resultSet.getMetaData().getColumnName(i), resultSet.getString(i));
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public static void createFolder(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder " + folderName + " created successfully!");
            } else {
                System.out.println("Error creating folder " + folderName);
            }
        } else {
            System.out.println("Folder " + folderName + " already exists.");
        }
    }
    public void downloadFile(String fileUrl) {
        Navigate.navigateTo(fileUrl);
    }

    public String getFileExtension(String filePath) {
        String[] arr = filePath.split("\\\\");
        String fileName = arr[arr.length - 1];
        return fileName.substring(fileName.indexOf('.'));
    }


    public String getFileName(String folderPath) {
        List<String> results = new ArrayList<String>();
        File[] files = new File(folderPath).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results.toString();
    }

    public void unzipFile(String zipFilePath, String destinationFolder) {
        try {
            ZipFile zipFile = new ZipFile(zipFilePath);
            File folder = new File(destinationFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            zipFile.stream().forEach(entry -> {
                try {
                    String entryName = entry.getName();
                    Path entryPath = Paths.get(destinationFolder, entryName);

                    if (!entry.isDirectory()) {
                        Files.copy(zipFile.getInputStream(entry), entryPath);
                    } else {
                        File dir = new File(entryPath.toString());
                        dir.mkdirs();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            zipFile.close();
            System.out.println("File unzipped successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPdfData(String pdfFilePath) {
//        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
//            PDFTextStripper pdfTextStripper = new PDFTextStripper();
//            return pdfTextStripper.getText(document);
//        } catch (IOException e) {
//            e.printStackTrace();
            return  null;
//        }
    }

    public String getDocxData(String docxFilePath) {
        try {
            FileInputStream fis = new FileInputStream((docxFilePath));
            XWPFDocument document = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            return extractor.getText();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getExcelData(String excelFilePath) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(excelFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet("Sheet1");

        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue;
                if (cell.getCellType() == CellType.STRING) {
                    cellValue = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                } else if (cell.getCellType() == CellType.BLANK) {
                    cellValue = "";
                } else {
                    cellValue = cell.toString();
                }
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTextData(String textFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPptData(String pptFilePath) {
        try (FileInputStream file = new FileInputStream(pptFilePath);
             XMLSlideShow ppt = new XMLSlideShow(file)) {
            // Get all slides from the PPT
            XSLFSlide[] slides = ppt.getSlides().toArray(new XSLFSlide[0]);

            // Process each slide
            for (XSLFSlide slide : slides) {
                System.out.println("Slide " + slide.getSlideNumber() + ":");
                // Process slide content
                for (XSLFShape shape : slide.getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        String text = textShape.getText();
                        System.out.println("Text: " + text);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllFile(String folderUrl) {
        File dir = new File(folderUrl);
        for (File file : dir.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    public String getFileSize(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            long fileSizeInBytes = file.length();
            double fileSizeInKB = (double) fileSizeInBytes / 1024;
            double fileSizeInMB = fileSizeInKB / 1024;
            System.out.println("File Size: " + fileSizeInBytes + " bytes");
            System.out.println("File Size: " + fileSizeInKB + " KB");
            System.out.println("File Size: " + fileSizeInMB + " MB");
            return fileSizeInBytes+" in bytes";
        } else {
            System.out.println("File not found.");
            return "File not found.";
        }
    }

    public String folderSize(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            long folderSizeInBytes = getFolderSize(folder);
            double folderSizeInKB = (double) folderSizeInBytes / 1024;
            double folderSizeInMB = folderSizeInKB / 1024;

            System.out.println("Folder Size: " + folderSizeInBytes + " bytes");
            System.out.println("Folder Size: " + folderSizeInKB + " KB");
            System.out.println("Folder Size: " + folderSizeInMB + " MB");
            return folderSizeInBytes+" in bytes";
        } else {
            System.out.println("Folder not found.");
            return "Folder not found.";
        }
    }

    private static long getFolderSize(File folder) {
        long size = 0;

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        size += getFolderSize(file);
                    } else {
                        size += file.length();
                    }
                }
            }
        } else {
            size = folder.length();
        }

        return size;
    }

    public String getFolderCount(String rootFolderPath) {
        File rootFolder = new File(rootFolderPath);
        if (rootFolder.exists() && rootFolder.isDirectory()) {
            int folderCount = countFolders(rootFolder);
            System.out.println("Total number of folders: " + folderCount);
            return folderCount+"";
        } else {
            System.out.println("Root directory not found.");
            return "Root directory not found.";
        }
    }

    private static int countFolders(File folder) {
        int count = 1; // Start from 1 to count the current folder itself

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        count += countFolders(file);
                    }
                }
            }
        }
        return count;
    }

    public String fileCount(String rootFolderPath) {
        File rootFolder = new File(rootFolderPath);

        if (rootFolder.exists() && rootFolder.isDirectory()) {
            int fileCount = countFiles(rootFolder);
            System.out.println("Total number of files: " + fileCount);
            return fileCount+"";
        } else {
            System.out.println("Root directory not found.");
            return "Root directory not found.";
        }
    }

    private static int countFiles(File folder) {
        int count = 0;

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        count += countFiles(file);
                    } else {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public String nestedFolderCount(String rootFolderPath) {
        File rootFolder = new File(rootFolderPath);

        if (rootFolder.exists() && rootFolder.isDirectory()) {
            int nestedFolderCount = countNestedFolders(rootFolder);
            System.out.println("Total number of nested folders: " + nestedFolderCount);
            return nestedFolderCount+"";
        } else {
            System.out.println("Root directory not found.");
            return "Root directory not found.";
        }
    }

    private static int countNestedFolders(File folder) {
        int count = 0;
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        count++; // Increment the count for each nested folder
                        count += countNestedFolders(file); // Recursively count nested folders
                    }
                }
            }
        }
        return count;
    }
}
