package homeLoanResourse;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class homeLoanMethodsInputDetails 
{
        // Method to read the home loan amount from the Excel file
	public static String HomeLoanAmountInput() throws IOException {
		String fileLocation = "./ExcelInputFiles/HomeLoanMethodsInputs.xlsx";
		XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = wbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(0);
		int value1 = (int) cell.getNumericCellValue();
		wbook.close();
		
		return String.valueOf(value1);
	}

	// Method to read the home loan interest rate from the Excel file
	public static String HomeLoanInterestRateInput() throws IOException {
		String fileLocation = "./ExcelInputFiles/HomeLoanMethodsInputs.xlsx";
		XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = wbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(1);
		double value2 = cell.getNumericCellValue();
		wbook.close();
		
		return String.valueOf(value2);
	}

	// Method to read the home loan tenure from the Excel file
	public static String HomeLoanTenureInput() throws IOException {
		String fileLocation = "./ExcelInputFiles/HomeLoanMethodsInputs.xlsx";
		XSSFWorkbook wbook = new XSSFWorkbook(fileLocation);
		XSSFSheet sheet = wbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		XSSFCell cell = row.getCell(2);
		int value3 = (int) cell.getNumericCellValue();
		wbook.close();
		
		return String.valueOf(value3);
	}

}
