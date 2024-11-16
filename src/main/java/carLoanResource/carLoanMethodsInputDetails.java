package carLoanResource;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class carLoanMethodsInputDetails {
       //Input data of car loan amount from excel file
	public static String CarLoanAmountInput() throws IOException {
		String fileLocation = "./ExcelInputFiles/CarLoanMethodInputs.xlsx";
		XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = wbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(0);
		int value1 = (int) cell.getNumericCellValue();
		wbook.close();
		
		return String.valueOf(value1);
	}
       //Input data of car loan Intrest Rate from excel file
	public static String CarLoanInterestRateInput() throws IOException {
		String fileLocation = "./ExcelInputFiles/CarLoanMethodInputs.xlsx";
		XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = wbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(1);
		double value2 = cell.getNumericCellValue();
		wbook.close();
		return String.valueOf(value2);
	}
       //Input data of car loan Tenure from excel file
	public static String CarLoanTenureInput() throws IOException {
		String fileLocation = "./ExcelInputFiles/CarLoanMethodInputs.xlsx";
		XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = wbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(2);
		int value3 = (int) cell.getNumericCellValue();
		wbook.close();
		return String.valueOf(value3);
	}
      //Input data for month/year to be printed in the output
	public static int MonthToBePrintered() throws IOException {
		String fileLocation = "./ExcelInputFiles/CarLoanMethodInputs.xlsx";
		XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = wbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(3);
		int value4 =(int) cell.getNumericCellValue();
		wbook.close();
		return value4;
	}

}
