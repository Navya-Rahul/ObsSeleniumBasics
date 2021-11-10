package com.obsqura.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static FileInputStream f;
    public List<String> readDataFromExcel(String filePath, String sheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        f = new FileInputStream(System.getProperty("user.dir") + filePath);
        wb = new XSSFWorkbook(f);
        sh = wb.getSheet(sheetName);
        ArrayList<String> excelRows = new ArrayList<String>();
        for(Row r : sh) {
            for (Cell c : r) {
                excelRows.add(formatter.formatCellValue(c));
            }
        }
        return excelRows;
    }
}
