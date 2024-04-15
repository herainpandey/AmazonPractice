package com.seleniumapi.suite.utils.excelUtils;

import com.seleniumapi.suite.Constants.Constants;
import com.seleniumapi.utils.resourceLoaderUtil.ResourceLoader;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class ExcelUtils {

    private Map<Object,Map<String,String>> getSheetData;
    private ExcelUtils(){

    }
    private static final ExcelUtils instance = new ExcelUtils();

    public static ExcelUtils getInstance() {
        return instance;
    }

    public Map<String,Map<String,String>> getSheetData(String sheet_name) throws IOException {

        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFCell cell;
        Map<String,Map<String,String>> parentmap = new HashMap<>();

        FileInputStream is = ResourceLoader.getFile(Constants.EXCEL_FILE);

        try{
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheet(sheet_name);

            int total_column = sheet.getRow(0).getLastCellNum();
            int total_row = sheet.getPhysicalNumberOfRows();

            List<String> list = new ArrayList<>();
            list.add("testName");
            list.add("firstName");
            list.add("lastName");
            list.add("city");
            list.add("addressLineOne");
            list.add("postalCode");
            list.add("email");
            list.add("company");
            list.add("country");
            list.add("state");
            int testcol =0;

            for(int i=0;i<total_row-1;i++){
                Row row = sheet.getRow(i+1);
                Map<String,String> childmap = new HashMap<>();

                for(int j=1;j<total_column;j++){
                    childmap.put(list.get(j),new DataFormatter().formatCellValue(row.getCell(j)));
                }
                parentmap.put(new DataFormatter().formatCellValue(row.getCell(0)),childmap);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return parentmap;
    }


    public Map<String,String> getRowData(String sheet_name, String test_data) throws IOException {
        Map<String,String> map = null;
        try{
            map = getSheetData(sheet_name).get(test_data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }


}
