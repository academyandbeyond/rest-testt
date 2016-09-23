package com.resttest.framework.excel.model;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;


public class Excel {

    public static void main(String[] args) throws InvalidFormatException, IOException{

    String excelFilePath = "workbook.xlsx" ;
    FileInputStream inp = new FileInputStream(excelFilePath);
    Workbook workbook = new XSSFWorkbook(inp);
    Sheet sheet = workbook.getSheetAt(0);

        int rowNum = sheet.getLastRowNum() + 1;
        int colNum = sheet.getRow(0).getLastCellNum();

        System.out.println(rowNum);
        System.out.println(sheet.getRow(0).getCell(0).getStringCellValue());


    }
}