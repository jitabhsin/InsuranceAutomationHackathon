package utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    String path = "src/test/java/resources/data.xlsx";
    public Object[][] readSheetTravel() {
        return null;
    }
    public Object[][] readSheetHealth() {
        return null;
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