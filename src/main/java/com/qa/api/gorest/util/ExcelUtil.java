package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Dhanashri\\eclipse-workspace\\DhanashriRestAssuredFramework\\src\\main\\java\\com\\qa\\api\\gorest\\testdata\\GorestTestData.xlsx";
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
			try {
				book = WorkbookFactory.create(ip); // this method will create the local copy in the Java memory i.e. JVM 
				sheet = book.getSheet(sheetName); //this method reaches till the whole sheet of excel file.
				
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		//sheet.getLastRowNum()-----> it returns the total row numbers of data available in the excel sheet. 
													//row Count ends till Last Row Number-->getLastRowNum()
		//sheet.getRow(0).getLastCellNum()-----> it returns the total column numbers of data available in the excel sheet.
								//getRow(0)-->col count starts from 1st cell and ends at getLastCellNum()--->last cell of the sheet.
		
		
		for(int i=0; i < sheet.getLastRowNum(); i++) {
			for(int j=0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();// this getRow(i+1) method starts to count data from 2nd row
																		// till getCell(j)--> col. e.g. data[1][0] and returns data into String format
			}
				
		}
		
			return data;
	}

}
