/*
 * Copyright (c) 25/6/2021
 * All rights reserved to Gadi Engelsman.
 *  https://github.com/Gadiler
 */
package com.example.sayeretproject.helper;

import com.example.sayeretproject.beans.Point;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
  private static final String TAG = ExcelHelper.class.getSimpleName();
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String SHEET = "data_base";

  public static boolean hasExcelFormat(MultipartFile file) {
    return TYPE.equals(file.getContentType());
  }

  public static List<Point> excelToTutorials(InputStream is) {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook(is);

      XSSFSheet sheet = workbook.getSheetAt(0);

      Iterator<Row> rows = sheet.iterator();

      List<Point> tutorials = new ArrayList<>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        Point tutorial = new Point();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

          switch (cellIdx) {
            case 0:
              tutorial.setId((int) currentCell.getNumericCellValue());
              break;

            case 1:
              tutorial.setAreaId((int) currentCell.getNumericCellValue());
              break;

            case 2:
              tutorial.setAreaName(currentCell.getStringCellValue());
              break;

            case 3:
              tutorial.setName(currentCell.getStringCellValue());
              break;

            case 4:
              tutorial.setImagesPath(currentCell.getStringCellValue());
              break;

            case 5:
              tutorial.setLmWidth(currentCell.getStringCellValue());
              break;

            case 6:
              tutorial.setLmHeight(currentCell.getStringCellValue());
              break;

            case 7:
              tutorial.setNumOfImages((int) currentCell.getNumericCellValue());
              break;

            default:
              break;
          }

          cellIdx++;
        }

        tutorials.add(tutorial);
      }

      workbook.close();
      return tutorials;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}
