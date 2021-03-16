package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	 public static Workbook book;
	 public static Sheet sheet;
	
	public static String TESTDAT_SHEET_PATH = "C:/Eclipse_Program/March2021RestAssuredFramework/src/main/java/com/qa/api/gorest/testdata/UserData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		try {
			System.out.println("1");
			FileInputStream ip = new FileInputStream(TESTDAT_SHEET_PATH);
			System.out.println("1A");
			try {
				book = WorkbookFactory.create(ip);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("1B");
			sheet = book.getSheet(sheetName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Object data[][]= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println("2");
		for(int i=0 ; i < sheet.getLastRowNum(); i++) {
			for(int k=0 ; k< sheet.getRow(0).getLastCellNum();k++) {
				
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
				
			}
		}
		 return data;
	}
	
	

}
