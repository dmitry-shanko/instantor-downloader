package com.moneyman.instantor.downloader.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderImpl implements ExcelReader {

  public List<Pair<Long, String>> readInstantorUserDetailIdList(File file) {
    List<Pair<Long, String>> result = new ArrayList<>();
    try {
      Workbook workBook = new XSSFWorkbook(file);
      Sheet sheet = workBook.getSheetAt(0);
      for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
        result.add(parseRow(sheet.getRow(i)));
      }
    } catch (IOException | InvalidFormatException e) {
      e.printStackTrace();
    }
    return result;
  }

  private Pair<Long, String> parseRow(Row row) {
    return new ImmutablePair<>((long) row.getCell(3).getNumericCellValue(), row.getCell(4).getStringCellValue());
  }
}
