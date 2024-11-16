package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import objectClasses.CarLoanMethods;
import objectClasses.EmiCalculatorMethods;
import objectClasses.HomeLoanMethods;
import sharedDrivers.DriverSetup;

public class LoanCalculatorSteps
{

    static WebDriver driver;
    CarLoanMethods carLoanMethods;
    HomeLoanMethods homeLoanMethods;
    EmiCalculatorMethods emiCalculatorMethods;
    String[][] CarLoanyearlyData;
    String[][] CarLoanmonthlyData;
    String[][] CarLoanfilteredData;
    String[][] HomeLoanyearlyData;
    static String browser;

    @Given("the browser {string} is opened")
    public void openBrowser(String browser) throws InterruptedException, IOException
    {
	try {
	    // Initialize the WebDriver and open the specified browser
	    driver = DriverSetup.getDriver(browser);
	    carLoanMethods = new CarLoanMethods(driver, browser);
	    LoanCalculatorSteps.browser = browser;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @When("navigating to the car loan section")
    public void navigateToCarLoanSection()
    {
	try {
	    // Navigating to the car loan section
	    carLoanMethods.navigateToCarLoanSection();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @When("entering the car loan details")
    public void enterCarLoanDetails() throws InterruptedException, IOException
    {
	try {
	    // Entering the car loan details
	    CarLoanMethods.enterLoanDetails();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @When("scrolling and clicking car loan years")
    public void scrollAndClickCarLoanYears() throws NumberFormatException, InterruptedException, IOException
    {
	try {
	    // Scroll and click on car loan years like 2024 and 2025
	    CarLoanMethods.scrollAndClickYears();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Then("extracting car loan yearly data")
    public void extractCarLoanYearlyData()
    {
	try {
	    // Extracting car loan yearly data
	    CarLoanyearlyData = CarLoanMethods.extractYearlyData();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Then("extracting car loan monthly data")
    public void extractCarLoanMonthlyData()
    {
	try {
	    // Extracting car loan monthly data
	    CarLoanmonthlyData = CarLoanMethods.extractMonthlyData();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Then("filtering car loan data")
    public void filterCarLoanData() throws NumberFormatException, IOException
    {
	try {
	    // Filter car loan data removing yearly data from monthly data
	    CarLoanfilteredData = CarLoanMethods.filterData(CarLoanyearlyData, CarLoanmonthlyData);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Then("printing the filtered car loan data")
    public void printFilteredCarLoanData() throws IOException
    {
	try {
	    // Printing the this month car loan data
	    CarLoanMethods.printFilteredData(CarLoanfilteredData);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Given("the car loan calculation is completed")
    public void completedCarLoanCalculation()
    {
	// Placeholder step to ensure the order of execution
    }

    @When("navigating to the home loan section")
    public void navigateToHomeLoanSection() throws InterruptedException
    {
	try {
	    // Navigate to the home loan section
	    homeLoanMethods = new HomeLoanMethods(driver, browser);
	    HomeLoanMethods.navigateToHomeLoanSection();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @When("entering the home loan details")
    public void enterHomeLoanDetails() throws IOException
    {
	try {
	    // Enter the home loan details
	    HomeLoanMethods.enterHomeLoanDetails();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @When("scrolling and clicking home loan years")
    public void scrollAndClickHomeLoanYears()
    {
	try {
	    // Scroll and click on home loan years
	    HomeLoanMethods.scrollAndClickYears();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Then("extracting home loan yearly table data")
    public void extractHomeLoanYearlyTableData()
    {
	try {
	    // Extract home loan yearly table data
	    HomeLoanyearlyData = HomeLoanMethods.extractYearlyTableData();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Then("storing the home loan values in an Excel sheet")
    public void storeHomeLoanValuesInExcelSheet() throws IOException
    {
	try {
	    // Store the home loan values in an Excel sheet
	    HomeLoanMethods.storingTheValuesInExcelSheet(HomeLoanyearlyData);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Given("the home loan EMI calculation is completed")
    public void completedHomeLoanEmiCalculation()
    {
	// Placeholder step to ensure the order of execution
    }

    @When("navigating to the EMI calculator section")
    public void navigateToEmiCalculatorSection() {
        try {
            emiCalculatorMethods = new EmiCalculatorMethods(driver, browser);
            emiCalculatorMethods.navigateToEmiCalculatorSection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("using the EMI calculator sliders")
    public void useEmiCalculatorSliders() {
        try {
            emiCalculatorMethods.testEmiCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("checking the EMI calculator slider output")
    public void checkEmiCalculatorSliderOutput() {
        try {
            emiCalculatorMethods.testEmiCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("navigating to the loan tenure calculator")
    public void navigateToLoanTenureCalculator() {
        try {
            emiCalculatorMethods.navigateToLoanTenureCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("using the loan tenure calculator slider")
    public void useLoanTenureCalculatorSlider() {
        try {
            emiCalculatorMethods.testLoanTenureCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("checking the loan tenure calculator slider value")
    public void checkLoanTenureCalculatorSliderValue() {
        try {
            emiCalculatorMethods.testLoanTenureCalculator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("closing the browser")
    public void closeBrowser()
    {
	try {
	    // Close the browser
	    driver.quit();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
