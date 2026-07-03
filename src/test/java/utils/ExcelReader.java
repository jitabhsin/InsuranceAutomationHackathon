package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
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

    public Object[][] readSheetCar() {

        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("Car");

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] =
                            sheet.getRow(i).getCell(j).toString();
                }
            }
            workbook.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}