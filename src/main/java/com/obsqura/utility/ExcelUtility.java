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
    List<String> list = new ArrayList<String>();
    public List<String> readDataFromExcel(String excelName, String sheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        FileInputStream file = new FileInputStream(excelName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        for(Row r : sheet) {
            for (Cell c : r) {
                list.add(formatter.formatCellValue(c));
            }
        }
        return list;
    }
}
