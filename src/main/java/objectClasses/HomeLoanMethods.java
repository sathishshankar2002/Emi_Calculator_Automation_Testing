package objectClasses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonScreenShotMethod.ScreenshotUtil;
import homeLoanResourse.homeLoanMethodsInputDetails;
import sharedDrivers.DriverSetup;

public class HomeLoanMethods
{

    static WebDriver driver;
    static JavascriptExecutor js;
    static WebDriverWait wait;
    static Logger logger;
    static String browser;

    // Constructor to initialize WebDriver, JavascriptExecutor, WebDriverWait, and
    // Logger
    public HomeLoanMethods(WebDriver driver, String browser)
    {
	HomeLoanMethods.driver = driver;
	HomeLoanMethods.js = (JavascriptExecutor) driver;
	HomeLoanMethods.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	HomeLoanMethods.browser = browser;
	PageFactory.initElements(driver, this);
	logger = DriverSetup.logger;
    }

    // WebElement for the home loan logo
    @FindBy(xpath = "//a[@title='EMI Calculator']")
    public static WebElement homeLoanLogo;

    // WebElement for the dropdown menu
    @FindBy(xpath = "//*[@id=\"menu-item-2696\"]")
    public static WebElement dropdown;

    // WebElement for the home loan option in the dropdown menu
    @FindBy(id = "menu-item-3294")
    public static WebElement homeLoanOption;

    // WebElement for the home price input field
    @FindBy(xpath = "(//input[@id='homeprice'])[1]")
    public static WebElement homePriceInput;

    // WebElement for the home loan interest input field
    @FindBy(xpath = "(//input[@id='homeloaninterest'])[1]")
    public static WebElement homeLoanInterestInput;

    // WebElement for the home loan term input field
    @FindBy(xpath = "(//input[@id='homeloanterm'])[1]")
    public static WebElement homeLoanTermInput;

    // List of WebElements for the payment schedule rows
    @FindBy(xpath = "//*[@id='paymentschedule']/table/tbody/tr[contains(@class, 'yearlypaymentdetails')]")
    public static List<WebElement> paymentScheduleRows;

    // Method to navigate to the home loan section
    public static void navigateToHomeLoanSection() throws InterruptedException
    {
	try {
	    wait.until(ExpectedConditions.visibilityOf(homeLoanLogo));
	    homeLoanLogo.click();
	    DriverSetup.logger.info("Clicked on HomeLoan logo using " + browser + " browser.");
	} catch (Exception e) {
	    DriverSetup.logger
		    .error("Failed to click on HomeLoan logo using " + browser + " browser: " + e.getMessage(), e);
	}

	try {
	    wait.until(ExpectedConditions.visibilityOf(dropdown));
	    dropdown.click();
	    ScreenshotUtil.takeScreenshot(driver, "HomeLoanDropdown_" + browser);
	    DriverSetup.logger.info("Clicked on dropdown using " + browser + " browser.");
	} catch (Exception e) {
	    DriverSetup.logger.error("Failed to click on dropdown using " + browser + " browser: " + e.getMessage(), e);
	}

	try {
	    homeLoanOption.click();
	    DriverSetup.logger.info("Clicked on homeLoanOption using " + browser + " browser.");
	} catch (Exception e) {
	    DriverSetup.logger
		    .error("Failed to click on homeLoanOption using " + browser + " browser: " + e.getMessage(), e);
	}
    }

    // Method to enter home loan details
    public static void enterHomeLoanDetails() throws IOException
    {
	try {
	    HomeLoanAmountInput(homeLoanMethodsInputDetails.HomeLoanAmountInput());
	    DriverSetup.logger.info("Entered home loan amount using " + browser + " browser.");

	    HomeLoanInterestInput(homeLoanMethodsInputDetails.HomeLoanInterestRateInput());
	    DriverSetup.logger.info("Entered home loan interest rate using " + browser + " browser.");

	    HomeLoanTenureInput(homeLoanMethodsInputDetails.HomeLoanTenureInput());
	    DriverSetup.logger.info("Entered home loan tenure using " + browser + " browser.");
	} catch (IOException e) {
	    DriverSetup.logger
		    .error("Error entering home loan details using " + browser + " browser: " + e.getMessage(), e);
	}
    }

    // Method to input home loan amount
    public static void HomeLoanAmountInput(String HomeLoanAmount)
    {
	try {
	    wait.until(ExpectedConditions.visibilityOf(homePriceInput));
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
		    homePriceInput);
	    homePriceInput.sendKeys(Keys.CONTROL + "a");
	    homePriceInput.sendKeys(Keys.DELETE);
	    homePriceInput.sendKeys(HomeLoanAmount);
	    homePriceInput.sendKeys(Keys.ENTER);
	    ScreenshotUtil.takeScreenshot(driver, "EnteringHomePriceInput_" + browser);
	    DriverSetup.logger.info("Entered home loan amount: " + HomeLoanAmount + " using " + browser + " browser.");
	} catch (Exception e) {
	    DriverSetup.logger.error("Error entering home loan amount using " + browser + " browser: " + e.getMessage(),
		    e);
	}
    }

    // Method to input home loan interest rate
    public static void HomeLoanInterestInput(String HomeLoanInterest)
    {
	try {
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",homeLoanInterestInput);
	    homeLoanInterestInput.sendKeys(Keys.CONTROL + "a");
	    homeLoanInterestInput.sendKeys(Keys.DELETE);
	    homeLoanInterestInput.sendKeys(HomeLoanInterest);
	    homeLoanInterestInput.sendKeys(Keys.ENTER);
	    ScreenshotUtil.takeScreenshot(driver, "EnteringHomeLoanInterest_" + browser);
	    DriverSetup.logger
		    .info("Entered home loan interest: " + HomeLoanInterest + " using " + browser + " browser.");
	} catch (Exception e) {
	    DriverSetup.logger
		    .error("Error entering home loan interest using " + browser + " browser: " + e.getMessage(), e);
	}
    }

    public static void HomeLoanTenureInput(String HomeLoanTenure)
    {
	try {
	    // Highlight the home loan term input field
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",homeLoanTermInput);

	    // Clear the existing value and enter the new home loan tenure
	    homeLoanTermInput.sendKeys(Keys.CONTROL + "a");
	    homeLoanTermInput.sendKeys(Keys.DELETE);
	    homeLoanTermInput.sendKeys(HomeLoanTenure);
	    homeLoanTermInput.sendKeys(Keys.ENTER);

	    // Take a screenshot after entering the home loan tenure
	    ScreenshotUtil.takeScreenshot(driver, "EnteringHomeLoanLoanTerm_" + browser);

	    // Log the action
	    DriverSetup.logger.info("Entered home loan tenure: " + HomeLoanTenure + " using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger.error("Error entering home loan tenure using " + browser + " browser: " + e.getMessage(),
		    e);
	}
    }

    public static void scrollAndClickYears()
    {
	try {
	    // Scroll down the page to view the yearly payment details
	    js.executeScript("window.scrollBy(0,1700)");

	    // Take a screenshot after scrolling
	    ScreenshotUtil.takeScreenshot(driver, "HomeLoanYearlyTable_" + browser);

	    // Log the action
	    DriverSetup.logger.info("Scrolled to yearly payment details using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger.error(
		    "Error scrolling to yearly payment details using " + browser + " browser: " + e.getMessage(), e);
	}
    }

    public static String[][] extractYearlyTableData()
    {
	// Initialize a 2D array to store the yearly table data
	String[][] yearlyValues = new String[paymentScheduleRows.size()][7];
	int row = 0;

	try {
	    // Iterate through each row in the payment schedule
	    for (WebElement datas : paymentScheduleRows) {
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
	    DriverSetup.logger.info("Extracted yearly table data using " + browser + " browser.");
	} catch (Exception e) {
	    // Log any errors that occur
	    DriverSetup.logger
		    .error("Error extracting yearly table data using " + browser + " browser: " + e.getMessage(), e);
	}

	return yearlyValues;
    }

    public static void storingTheValuesInExcelSheet(String[][] yearlyData) throws IOException
    {
	try (FileOutputStream file = new FileOutputStream(
		System.getProperty("user.dir") + "\\ExcelOutput\\HomeLone.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook()) {

	    // Create a new sheet in the workbook
	    XSSFSheet sheet = workbook.createSheet("AllYearlyData");

	    // Create the header row
	    XSSFRow row1 = sheet.createRow(0);
	    row1.createCell(0).setCellValue("Year");
	    row1.createCell(1).setCellValue("Principal");
	    row1.createCell(2).setCellValue("Interest");
	    row1.createCell(3).setCellValue("Taxes, Home Insurance & Maintenance");
	    row1.createCell(4).setCellValue("Total Payment");
	    row1.createCell(5).setCellValue("Balance");
	    row1.createCell(6).setCellValue("Loan Paid To Date");

	    // Populate the sheet with the yearly data
	    for (int i = 1; i < yearlyData.length; i++) {
		XSSFRow row2 = sheet.createRow(i + 1);

		for (int j = 0; j < yearlyData[0].length; j++) {
		    row2.createCell(j).setCellValue(yearlyData[i][j]);
		}
	    }

	    // Write the data to the Excel file
	    workbook.write(file);
	    // Log the action
	    DriverSetup.logger.info("Stored yearly data in Excel sheet using " + browser + " browser.");
	    logger.info("----------------------------------------------------------------");
	} catch (IOException e) {
	    // Log any errors that occur
	    DriverSetup.logger.error(
		    "Error storing yearly data in Excel sheet using " + browser + " browser: " + e.getMessage(), e);
	}
    }
}
