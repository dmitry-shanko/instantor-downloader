package com.moneyman.instantor.downloader.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderImpl implements ExcelReader {

    public List<Long> readInstantorUserDetailIdList(File file) {
        List<Long> result = new ArrayList<>();
        try {
            Workbook workBook = new XSSFWorkbook(file);
            Sheet sheet = workBook.getSheetAt(0);
            for(int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                result.add(parseRow(sheet.getRow(i)));
            }
        } catch(IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Long parseRow(Row row) {
        return (long)row.getCell(2).getNumericCellValue();
    }
}
