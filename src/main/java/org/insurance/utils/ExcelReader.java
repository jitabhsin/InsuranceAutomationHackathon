package org.insurance.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    String path = "src/test/resources/data.xlsx";

    public String[][] readSheetTravel() {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Travel");

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            String[][] data = new String[rowCount - 1][colCount];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {

                    data[i - 1][j] =
                            formatter.formatCellValue(sheet.getRow(i).getCell(j));
                }
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object[] readSheetTravelFirstRow() {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Travel");

            int cols = sheet.getRow(0).getLastCellNum();
            Object[] rowData = new Object[cols];

            DataFormatter formatter = new DataFormatter();

            for (int j = 0; j < cols; j++) {
                rowData[j] = formatter.formatCellValue(sheet.getRow(1).getCell(j));
            }

            return rowData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object[][] readSheetCar() {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Car");

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rows; i++) {

                System.out.println("ROW " + i);

                for (int j = 0; j < cols; j++) {

                    data[i - 1][j] =
                            formatter.formatCellValue(
                                    sheet.getRow(i).getCell(j));

                    System.out.println(
                            "COL " + j + " = " + data[i - 1][j]);
                }
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Object[] readSheetCarFirstRow() {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Car");

            int cols = sheet.getRow(0).getLastCellNum();
            Object[] rowData = new Object[cols];

            DataFormatter formatter = new DataFormatter();

            for (int j = 0; j < cols; j++) {
                rowData[j] = formatter.formatCellValue(sheet.getRow(1).getCell(j));
                System.out.println("COL " + j + " = " + rowData[j]);
            }

            return rowData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object[][] readSheetHealth() {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Health");

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rows; i++) {

                System.out.println("ROW " + i);

                for (int j = 0; j < cols; j++) {

                    data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));

                    System.out.println(
                            "COL " + j + " = " + data[i - 1][j]);
                }
            }
            return data;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private java.util.List<String> getSheetNames(Workbook workbook) {
        java.util.List<String> names = new java.util.ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            names.add(workbook.getSheetName(i));
        }
        return names;
    }

}