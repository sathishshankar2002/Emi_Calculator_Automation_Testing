package allLoanCalculators;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import objectClasses.CarLoanMethods;
import objectClasses.EmiCalculatorMethods;
import objectClasses.HomeLoanMethods;
import sharedDrivers.DriverSetup;

public class LoanCalculator {

    WebDriver driver;
    CarLoanMethods methods1;
    HomeLoanMethods methods2;
    EmiCalculatorMethods methods3;

    @Test(priority = 1)
    @Parameters({ "browser" })
    public void testCarLoanCalculation(String br) throws InterruptedException, IOException {
	// Initialize the Driver
	driver = DriverSetup.getDriver(br);
	// Initialize CarLoanMethods with the WebDriver and browser type
        methods1 = new CarLoanMethods(driver, br);
        // Navigate to the Car Loan section
        methods1.navigateToCarLoanSection();
        // Enter loan details
        CarLoanMethods.enterLoanDetails();
        // Scroll and click on yearly payment details
        CarLoanMethods.scrollAndClickYears();
        // Extract yearly and monthly data
        String[][] yearlyData = CarLoanMethods.extractYearlyData();
        String[][] monthlyData = CarLoanMethods.extractMonthlyData();
        // Filter the data
        String[][] filteredData = CarLoanMethods.filterData(yearlyData, monthlyData);
        // Print the filtered data
        CarLoanMethods.printFilteredData(filteredData);
        
    }

    @Test(priority = 2, dependsOnMethods = { "testCarLoanCalculation" })
    @Parameters({ "browser" })
    public void testHomeLoanEmiCalculator(String br) throws IOException, InterruptedException {
        // Initialize HomeLoanMethods with the WebDriver and browser type
        methods2 = new HomeLoanMethods(driver, br);
        // Navigate to the Home Loan section
        HomeLoanMethods.navigateToHomeLoanSection();
        // Enter home loan details
        HomeLoanMethods.enterHomeLoanDetails();
        // Scroll and click on yearly payment details
        HomeLoanMethods.scrollAndClickYears();
        // Extract yearly data
        String[][] yearlyData = HomeLoanMethods.extractYearlyTableData();
        // Store the values in an Excel sheet
        HomeLoanMethods.storingTheValuesInExcelSheet(yearlyData);
    }

    @Test(priority = 3, dependsOnMethods = { "testHomeLoanEmiCalculator" })
    @Parameters({ "browser" })
    public void testEmiCalculator(String br) throws InterruptedException {
        // Initialize EmiCalculatorMethods with the WebDriver and browser type
        methods3 = new EmiCalculatorMethods(driver, br);
        // Navigate to the EMI Calculator section
        methods3.navigateToEmiCalculatorSection();
        // Test the EMI Calculator
        methods3.testEmiCalculator();
        // Test the Loan Amount Calculator
        methods3.testLoanAmountCalculator();
        // Test the Loan Tenure Calculator
        methods3.testLoanTenureCalculator();
        // Quit the WebDriver
        driver.quit();
    }
}
