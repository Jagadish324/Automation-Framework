package com.arc.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {
    public static XSSFSheet sheet;

    public static String getData(String fileName, String sheetName, int rows, int column) {
        String value = "";
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet(sheetName);
            int noOfRows = sheet.getPhysicalNumberOfRows();
            value = getCellValue(rows, column);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getData(String fileName, int index, int rows, int column) {
        String value = "";
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(index);
            int noOfRows = sheet.getPhysicalNumberOfRows();
            value = getCellValue(rows, column);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getCellValue(int row, int column) {
        XSSFRow rows = sheet.getRow(row);
        XSSFCell cell = rows.getCell(column);
        return cell.getStringCellValue();
    }
    public static int getLastRowCount(String fileName){
        Workbook workbook = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        return sheet.getLastRowNum();
    }

    public static void writeDataInLastRow(String fileName, String sheetName, Map<String, Object[]> data) {
        File file = new File(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook();
        int i = workbook.getSheetIndex(sheetName);

            sheet = workbook.createSheet(sheetName);

        Set<String> keyset = data.keySet();
        for (String key : keyset) {
            int cellnum = 0;
            Object[] objArr = data.get(key);
            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (Object obj : objArr) {
                Cell cell = newRow.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            // Writing the workbook
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateExcel(String excelFilePath,int index, Object[][] bookData){
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(index);
            int rowCount = sheet.getLastRowNum();
            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);
                int columnCount = 0;
                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);
                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

public static void createExcel(String fileName, String sheetName){
    //creating an instance of Workbook class
    Workbook wb = new HSSFWorkbook();
//creates an excel file at the specified location
    OutputStream fileOut = null;
    try {
        fileOut = new FileOutputStream(fileName);
        Sheet sheet1 = wb.createSheet(sheetName);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
    System.out.println("Excel File has been created successfully.");
    try {
        wb.write(fileOut);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
    public static void writeData(String fileName, String sheetName, Map<String, Object[]> data, int row, int column) {
        File file = new File(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook();
        int i = workbook.getSheetIndex(sheetName);
        sheet = workbook.createSheet(sheetName);

        Set<String> keyset = data.keySet();
        for (String key : keyset) {
            int cellnum = column;
            Object[] objArr = data.get(key);
            Row newRow = sheet.createRow(row + 1);
            for (Object obj : objArr) {
                Cell cell = newRow.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            // Writing the workbook
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object[][] getExcelData(String filePath, String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        List<Object[]> dataList = new ArrayList<>();
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            Object[] data = new Object[colCount];
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                data[j] = getCellValue(cell);
            }
            dataList.add(data);
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
        return dataList.toArray(new Object[0][0]);
    }
    private static Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
