package objectClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import carLoanResource.carLoanMethodsInputDetails;
import commonScreenShotMethod.ScreenshotUtil;
import sharedDrivers.DriverSetup;

public class CarLoanMethods
{
    static WebDriver driver;
    static JavascriptExecutor js;
    static Logger logger;
    static String browser;

    // Constructor to initialize WebDriver, JavascriptExecutor, and Logger
    public CarLoanMethods(WebDriver driver, String browser)
    {
	CarLoanMethods.driver = driver;
	CarLoanMethods.js = (JavascriptExecutor) driver;
	PageFactory.initElements(driver, this);
	logger = DriverSetup.logger;
	CarLoanMethods.browser = browser;
    }

    // WebElement for selecting the Car Loan section
    @FindBy(xpath = "//a[normalize-space()='Car Loan']")
    public static WebElement selectingCarLoan;

    // WebElement for the loan amount input field
    @FindBy(xpath = "//input[@id='loanamount']")
    public static WebElement loanAmountTextBox;

    // WebElement for the interest rate input field
    @FindBy(xpath = "//input[@id='loaninterest']")
    public static WebElement interestRateTextBox;

    // WebElement for the loan tenure input field
    @FindBy(xpath = "//input[@id='loanterm']")
    public static WebElement loanTenureTextBox;

    // List of WebElements for the yearly payment details
    @FindBy(xpath = "//*[@id='emipaymenttable']/table/tbody/tr[contains(@class, 'yearlypaymentdetails')]")
    public static List<WebElement> yearlyPayment;

    // List of WebElements for the monthly payment details
    @FindBy(className = "currency")
    public static List<WebElement> monthlyPayment;

    // Method to navigate to the Car Loan section
    public void navigateToCarLoanSection()
    {
	try {
	    logger.info("----------------------------------------------------------------");
	    logger.info("Successfully opened the " + browser + " browser.");

	    selectingCarLoan.click();
	    logger.info("Navigated to Car Loan section using " + browser + " browser.");
	} catch (Exception e) {
	    logger.error("Failed to navigate to Car Loan section using " + browser + " browser.", e);
	}
    }

    // Method to enter car loan details
    public static void enterLoanDetails()
    {
	try {
	    enteringTheLoanAmount(carLoanMethodsInputDetails.CarLoanAmountInput());
	    DriverSetup.logger.info("Entered loan amount using " + browser + " browser.");

	    enteringTheInterestRate(carLoanMethodsInputDetails.CarLoanInterestRateInput());
	    DriverSetup.logger.info("Entered interest rate using " + browser + " browser.");

	    enteringTheLoanTenure(carLoanMethodsInputDetails.CarLoanTenureInput());
	    DriverSetup.logger.info("Entered loan tenure using " + browser + " browser.");
	} catch (IOException e) {
	    DriverSetup.logger.error("Error entering loan details using " + browser + " browser.", e);
	}
    }

    // Method to enter the loan amount
    public static void enteringTheLoanAmount(String finalAmount)
    {
	try {
	    // Highlight the loan amount input field
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
		    loanAmountTextBox);

	    // Clear the existing value and enter the new loan amount
	    loanAmountTextBox.sendKeys(Keys.CONTROL + "a");
	    loanAmountTextBox.sendKeys(Keys.DELETE);
	    loanAmountTextBox.sendKeys(finalAmount);
	    loanAmountTextBox.sendKeys(Keys.ENTER);

	    // Take a screenshot after entering the loan amount
	    ScreenshotUtil.takeScreenshot(driver, "CarLoanAmount_" + browser);

	    // Log the action
	    DriverSetup.logger.info("Entered loan amount: " + finalAmount + " using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error entering loan amount using " + browser + " browser.", e);
	}
    }

    // Method to enter the interest rate
    public static void enteringTheInterestRate(String interest)
    {
	try {
	    // Highlight the interest rate input field
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
		    interestRateTextBox);

	    // Clear the existing value and enter the new interest rate
	    interestRateTextBox.sendKeys(Keys.CONTROL + "a");
	    interestRateTextBox.sendKeys(Keys.BACK_SPACE);
	    interestRateTextBox.sendKeys(interest);
	    interestRateTextBox.sendKeys(Keys.ENTER);

	    // Take a screenshot after entering the interest rate
	    ScreenshotUtil.takeScreenshot(driver, "CarLoanInterest_" + browser);

	    // Log the action
	    DriverSetup.logger.info("Entered interest rate: " + interest + " using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error entering interest rate using " + browser + " browser.", e);
	}
    }

    // Method to enter the loan tenure
    public static void enteringTheLoanTenure(String loanTenure)
    {
	try {
	    // Highlight the loan tenure input field
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
		    loanTenureTextBox);

	    // Clear the existing value and enter the new loan tenure
	    loanTenureTextBox.sendKeys(Keys.CONTROL + "a");
	    loanTenureTextBox.sendKeys(Keys.BACK_SPACE);
	    loanTenureTextBox.sendKeys(loanTenure);
	    loanTenureTextBox.sendKeys(Keys.ENTER);

	    // Take a screenshot after entering the loan tenure
	    ScreenshotUtil.takeScreenshot(driver, "CarLoanTenure_" + browser);

	    // Log the action
	    DriverSetup.logger.info("Entered loan tenure: " + loanTenure + " using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error entering loan tenure using " + browser + " browser.", e);
	}
    }

    // Method to scroll down and click on yearly payment details
    public static void scrollAndClickYears()
    {
	try {
	    // Scroll down the page to view the yearly payment details
	    js.executeScript("window.scrollBy(0,1700)");
	    DriverSetup.logger.info("Scrolled down to view yearly payment details using " + browser + " browser.");

	    // Take a screenshot after scrolling
	    ScreenshotUtil.takeScreenshot(driver, "CarLoanTable_" + browser);

	    // Click on each year in the payment schedule
	    for (int i = 0; i < Integer.parseInt(carLoanMethodsInputDetails.CarLoanTenureInput()) + 1; i++) {
		driver.findElement(By.xpath("(//*[@id='year" + (2024 + i) + "'])")).click();
		Thread.sleep(2000);

		// Take a screenshot after clicking on each year
		ScreenshotUtil.takeScreenshot(driver, "CarLoanTable " + (2024 + i) + "_" + browser);

		// Log the action
		DriverSetup.logger.info("Clicked on year: " + (2024 + i) + " using " + browser + " browser.");
	    }
	} catch (InterruptedException | NumberFormatException | IOException e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error scrolling and clicking years using " + browser + " browser.", e);
	}
    }

    public static String[][] extractYearlyData()
    {
	// Initialize a 2D array to store the yearly data
	String[][] yearlyValues = new String[yearlyPayment.size()][6];
	int row = 0;

	try {
	    // Iterate through each row in the yearly payment details
	    for (WebElement datas : yearlyPayment) {
		// Get the text from the row and clean it
		String text = datas.getText();
		String cleanedText = text.replaceAll("[₹]", "");
		String[] splitData = cleanedText.split("\\s+");

		// Store the cleaned data in the 2D array
		for (int col = 0; col < splitData.length; col++) {
		    yearlyValues[row][col] = splitData[col];
		}
		row++;
	    }
	    // Log the action
	    DriverSetup.logger.info("Extracted yearly data using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error extracting yearly data using " + browser + " browser.", e);
	}
	return yearlyValues;
    }

    public static String[][] extractMonthlyData()
    {
	// Calculate the number of rows for the monthly data
	int monthlyPaymentSize = monthlyPayment.size() / 4;
	// Initialize a 2D array to store the monthly data
	String[][] allvalues = new String[monthlyPaymentSize][5];
	int z = 0;

	try {
	    // Iterate through each row in the monthly payment details
	    for (int i = 0; i < monthlyPaymentSize; i++) {
		for (int j = 1; j < 5; j++) {
		    // Get the text from the element, clean it, and store it in the 2D array
		    String cleanedText = monthlyPayment.get(z++).getText().replaceAll("[₹]", "").trim();
		    allvalues[i][j] = cleanedText;
		}
	    }
	    // Log the action
	    DriverSetup.logger.info("Extracted monthly data using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error extracting monthly data using " + browser + " browser.", e);
	}
	return allvalues;
    }

    public static String[][] filterData(String[][] yearlyData, String[][] monthlyData) throws IOException
    {
	// Determine the size based on CarLoanTenureInput
	int tenure = Integer.parseInt(carLoanMethodsInputDetails.CarLoanTenureInput()) + 1;
	
	String[] yearlyPrincipleAmount = new String[tenure];
	try {
	    // Extract the relevant yearly data
	    for (int i = 0; i < tenure; i++) 
	    {
		yearlyPrincipleAmount[i] = yearlyData[i][1];
	    }

	    // Initialize a list to store the filtered monthly data
	    List<String[]> filteredValues = new ArrayList<>();

	    // Iterate through the monthly data to filter out matching yearly values
	    for (int i = 0; i < monthlyData.length; i++) {
		boolean matchFound = false;
		for (int j = 1; j < 3; j++) {
		    for (int k = 0; k < tenure; k++) {
			if (yearlyPrincipleAmount[k] != null && monthlyData[i][j].contentEquals(yearlyPrincipleAmount[k])) {
			    matchFound = true;
			    break;
			}
		    }
		}
		if (!matchFound) {
		    filteredValues.add(monthlyData[i]);
		}
	    }

	    // Convert the filtered list back to a 2D array
	    String[][] newAllValues = new String[filteredValues.size()][5];
	    for (int i = 0; i < filteredValues.size(); i++) {
		newAllValues[i] = filteredValues.get(i);
	    }

	    // Add row numbers to the filtered data
	    for (int i = 0; i < newAllValues.length; i++) {
		newAllValues[i][0] = String.valueOf(i + 1);
	    }

	    // Log the action
	    DriverSetup.logger.info("Filtered data successfully using " + browser + " browser.");
	    return newAllValues;
	} catch (NumberFormatException e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error filtering data using " + browser + " browser.", e);
	}
	return new String[0][0];
    }

    public static void printFilteredData(String[][] filteredData)
    {
	try {
	    // Get the month to be printed from the input details
	    String themonth = String.valueOf(carLoanMethodsInputDetails.MonthToBePrintered());

	    // Iterate through the filtered data to find the matching month
	    for (int i = 0; i < filteredData.length; i++) {
		if (themonth.equalsIgnoreCase(filteredData[i][0])) {
		    System.out.println("***************************************************************");

		    // Print the principal and interest amounts for the matching month
		    System.out.println("The principal amount for the car loan for this month is ₹" + filteredData[i][1]
			    + " in " + browser + ".");
		    System.out.println("The interest amount for the car loan for this month is ₹" + filteredData[i][2]
			    + " in " + browser + ".");

		    System.out.println("***************************************************************");
		}
	    }
	    // Log the action
	    DriverSetup.logger.info("Printed filtered data successfully in " + browser + ".");
	    logger.info("----------------------------------------------------------------");
	} catch (IOException e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error printing filtered data in " + browser + ".", e);
	}
    }
}