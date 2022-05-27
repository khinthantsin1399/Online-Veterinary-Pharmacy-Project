package scm.ojt.project.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import scm.ojt.project.persistence.entity.Medicine;

/**
 * <h2>ExcelImport Class</h2>
 * <p>
 * Process for Displaying ExcelImport
 * </p>
 * 
 * @author khinthantsin
 *
 */
public class ExcelImport {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Code", "Name", "Description", "Category", "Price", "Unit In Stock" };
    static String SHEET = "Medicine Details";

    /**
     * <h2>tutorialsToExcel</h2>
     * <p>
     * Method to save data from database as excel data
     * </p>
     * 
     * @param theMedicines
     * @return
     * @return ByteArrayInputStream
     */
    public static ByteArrayInputStream tutorialsToExcel(List<Medicine> theMedicines) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }
            int rowIdx = 1;
            for (Medicine m : theMedicines) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(m.getMedicine_code());
                row.createCell(1).setCellValue(m.getMedicine_name());
                row.createCell(2).setCellValue(m.getMedicine_description());
                row.createCell(3).setCellValue(m.category.getCategory_name());
                row.createCell(4).setCellValue(m.getAmount());
                row.createCell(5).setCellValue(m.getUnit_in_stock());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}