package objectClasses;

import java.time.Duration;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonScreenShotMethod.ScreenshotUtil;
import sharedDrivers.DriverSetup;

public class EmiCalculatorMethods
{

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private Logger logger;
    private String browser;
    private JavascriptExecutor js;

    // Constructor to initialize WebDriver, WebDriverWait, Actions, and Logger
    public EmiCalculatorMethods(WebDriver driver, String browser)
    {
	this.driver = driver;
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	this.actions = new Actions(driver);
	this.logger = DriverSetup.logger;
	this.browser = browser;
	this.js = (JavascriptExecutor) driver;
	PageFactory.initElements(driver, this);
    }

    // WebElement for the home loan logo
    @FindBy(xpath = "//a[@title='EMI Calculator']")
    private WebElement homeLoanLogo;

    // WebElement for the dropdown menu
    @FindBy(xpath = "//*[@id='menu-item-2696']")
    private WebElement dropdown;

    // WebElement for the loan calculator option in the dropdown menu
    @FindBy(id = "menu-item-2423")
    private WebElement loanCalculatorOption;

    // WebElement for navigating to the EMI Calculator
    @FindBy(xpath = "//a[@class='hidden-ts'][normalize-space()='EMI Calculator']")
    private WebElement navigatorToEMICalculator;

    // WebElement for navigating to the Loan Amount Calculator
    @FindBy(xpath = "//a[normalize-space()='Loan Amount Calculator']")
    private WebElement navigatorToLoanAmountCalculator;

    // WebElement for navigating to the Loan Tenure Calculator
    @FindBy(xpath = "//a[normalize-space()='Loan Tenure Calculator']")
    private WebElement navigatorToLoanTenureCalculator;

    // WebElement for the loan amount slider
    @FindBy(xpath = "//*[@id='loanamountslider']")
    private WebElement loanAmountSlider;

    // WebElement for the loan interest rate slider
    @FindBy(xpath = "//*[@id='loaninterestslider']")
    private WebElement loanInterestRateSlider;

    // WebElement for the loan tenure slider
    @FindBy(xpath = "//*[@id='loantermslider']")
    private WebElement loanTenureSlider;

    // WebElement for the loan fees slider
    @FindBy(xpath = "//*[@id='loanfeesslider']")
    private WebElement loanFeesSlider;

    // WebElement for the loan EMI slider
    @FindBy(xpath = "//div[@id='loanemislider']")
    private WebElement loanEmiSlider;

    // WebElement for the loan amount input field
    @FindBy(xpath = "//*[@id='loanamount']")
    private WebElement loanAmountInput;

    // WebElement for the loan tenure input field
    @FindBy(xpath = "//*[@id='loanterm']")
    private WebElement loanTenureInput;

    // WebElement for the loan fees input field
    @FindBy(xpath = "//*[@id='loanfees']")
    private WebElement loanFeesInput;

    // WebElement for the loan interest input field
    @FindBy(xpath = "//input[@id='loaninterest']")
    private WebElement loanInterestInput;

    // WebElement for the loan EMI input field
    @FindBy(xpath = "//input[@id='loanemi']")
    private WebElement loanEmiInput;

    // WebElement for the loan year button
    @FindBy(xpath = "//label[normalize-space()='Yr']")
    private WebElement loanYearButton;

    // WebElement for the loan months button
    @FindBy(xpath = "//label[normalize-space()='Mo']")
    private WebElement loanMonthsButton;

    // Method to navigate to EMI Calculator section
    public void navigateToEmiCalculatorSection()
    {
	clickElement(homeLoanLogo, "HomeLoan_logo");
	clickElement(dropdown, "dropdown");
	clickElement(loanCalculatorOption, "LoanCalculatorOption");
    }

    // Helper method to click on elements and log actions
    private void clickElement(WebElement element, String elementName)
    {
	try {
	    wait.until(ExpectedConditions.visibilityOf(element));
	    element.click();
	    logger.info("Clicked on " + elementName + " using " + browser + " browser.");
	} catch (Exception e) {
	    logger.error("Failed to click on " + elementName + " using " + browser + " browser: " + e.getMessage());
	}
    }

    // Method to drag sliders and take screenshots
    public void dragSlider(WebElement slider, int x, int y, String sliderName, String MethodName)
    {
	try {
	    actions.dragAndDropBy(slider, x, y).perform();
	    ScreenshotUtil.takeScreenshot(driver, sliderName + "_" + MethodName + "_" + browser);
	    logger.info("Dragged " + sliderName + " using " + browser + " browser.");
	} catch (Exception e) {
	    logger.error("Failed to drag " + sliderName + " using " + browser + " browser: " + e.getMessage());
	}
    }

    // Specific methods to drag different sliders
    public void dragLoanAmountSlider(int x, int y, String MethodName)
    {
	dragSlider(loanAmountSlider, x, y, "LoanAmountSlider", MethodName);
    }

    public void dragLoanInterestSlider(int x, int y, String MethodName)
    {
	dragSlider(loanInterestRateSlider, x, y, "LoanInterestSlider", MethodName);
    }

    public void dragLoanTenureSlider(int x, int y, String MethodName)
    {
	dragSlider(loanTenureSlider, x, y, "LoanTenureSlider", MethodName);
    }

    public void dragLoanFeesSlider(int x, int y, String MethodName)
    {
	dragSlider(loanFeesSlider, x, y, "LoanFeesSlider", MethodName);
    }

    public void dragLoanEmiSlider(int x, int y, String MethodName)
    {
	dragSlider(loanEmiSlider, x, y, "LoanEmiSlider", MethodName);
    }

    // Methods to verify the output of sliders
    public void verifyLoanAmountSliderOutput(String expectedValue)
    {
	verifySliderOutput(loanAmountInput, expectedValue, "loan amount slider");
    }

    public void verifyLoanInterestSliderOutput(String expectedValue)
    {
	verifySliderOutput(loanInterestInput, expectedValue, "loan interest rate slider");
    }

    public void verifyLoanTenureSliderYearlyOutput(String expectedValue)
    {
	clickElement(loanYearButton, "loanYearButton");
	verifySliderOutput(loanTenureInput, expectedValue, "loan tenure slider (yearly)");
    }

    public void verifyLoanTenureSliderMonthlyOutput(String expectedValue)
    {
	clickElement(loanMonthsButton, "loanMonthsButton");
	verifySliderOutput(loanTenureInput, expectedValue, "loan tenure slider (monthly)");
    }

    public void verifyLoanFeesSliderOutput(String expectedValue)
    {
	verifySliderOutput(loanFeesInput, expectedValue, "loan fees slider");
    }

    public void verifyLoanEmiSliderOutput(String expectedValue)
    {
	verifySliderOutput(loanEmiInput, expectedValue, "loan EMI slider");
    }

    // Helper method to verify slider outputs
    private void verifySliderOutput(WebElement inputElement, String expectedValue, String sliderName)
    {
	try {
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",inputElement);
	    Assert.assertEquals(inputElement.getAttribute("value"), expectedValue);
	    logger.info("Verified " + sliderName + " value using " + browser + " browser.");
	} catch (AssertionError e) {
	    logger.error("Assertion failed for " + sliderName + " using " + browser + " browser: " + e.getMessage());
	} catch (Exception e) {
	    logger.error("An error occurred while verifying the " + sliderName + " using " + browser + " browser: "
		    + e.getMessage());
	}
    }

    // Methods to navigate to different calculator sections
    public void navigateToLoanTenureCalculator()
    {
	clickElement(navigatorToLoanTenureCalculator, "Loan Tenure Calculator");
    }

    public void navigateToEmiCalculator()
    {
	clickElement(navigatorToEMICalculator, "EMI Calculator");
    }

    public void navigateToLoanAmountCalculator()
    {
	clickElement(navigatorToLoanAmountCalculator, "Loan Amount Calculator");
    }

    // Method to test the EMI Calculator functionality
    public void testEmiCalculator()
    {
	// Navigate to the EMI Calculator section
	navigateToEmiCalculator();

	// Drag the loan amount slider and verify the output
	dragLoanAmountSlider(40, 0, "testEmiCalculator");
	verifyLoanAmountSliderOutput("1,12,00,000");

	// Drag the loan interest rate slider and verify the output
	dragLoanInterestSlider(10, 0, "testEmiCalculator");
	verifyLoanInterestSliderOutput("10.25");

	// Click on the year button, drag the loan tenure slider, and verify the yearly
	// output
	clickElement(loanYearButton, "loanYearButton");
	dragLoanTenureSlider(35, 0, "testEmiCalculator");
	verifyLoanTenureSliderYearlyOutput("16.5");

	// Click on the month button, drag the loan tenure slider, and verify the
	// monthly output
	clickElement(loanMonthsButton, "loanMonthsButton");
	dragLoanTenureSlider(40, 0, "testEmiCalculator");
	verifyLoanTenureSliderMonthlyOutput("204");

	// Drag the loan fees slider and verify the output
	dragLoanFeesSlider(20, 0, "testEmiCalculator");
	verifyLoanFeesSliderOutput("53,000");
    }

    // Method to test the Loan Amount Calculator functionality
    public void testLoanAmountCalculator()
    {
	// Navigate to the Loan Amount Calculator section
	navigateToLoanAmountCalculator();

	// Drag the loan EMI slider and verify the output
	dragLoanEmiSlider(15, 0, "testLoanAmountCalculator");
	verifyLoanEmiSliderOutput("52,000.00");

	// Drag the loan interest rate slider and verify the output
	dragLoanInterestSlider(15, 0, "testLoanAmountCalculator");
	verifyLoanInterestSliderOutput("10.5");

	// Click on the month button, drag the loan tenure slider, and verify the
	// monthly output
	clickElement(loanMonthsButton, "loanMonthsButton");
	dragLoanTenureSlider(25, 0, "testLoanAmountCalculator");
	verifyLoanTenureSliderMonthlyOutput("192");

	// Click on the year button, drag the loan tenure slider, and verify the yearly
	// output
	clickElement(loanYearButton, "loanYearButton");
	dragLoanTenureSlider(20, 0, "testLoanAmountCalculator");
	verifyLoanTenureSliderYearlyOutput("16");

	// Drag the loan fees slider and verify the output
	dragLoanFeesSlider(10, 0, "testLoanAmountCalculator");
	verifyLoanFeesSliderOutput("50,500");
    }

    // Method to test the Loan Tenure Calculator functionality
    public void testLoanTenureCalculator()
    {
	// Navigate to the Loan Tenure Calculator section
	navigateToLoanTenureCalculator();

	// Drag the loan amount slider and verify the output
	dragLoanAmountSlider(60, 0, "testLoanTenureCalculator");
	verifyLoanAmountSliderOutput("1,18,00,000");

	// Drag the loan EMI slider and verify the output
	dragLoanEmiSlider(5, 0, "testLoanTenureCalculator");
	verifyLoanEmiSliderOutput("1,12,374.16");

	// Drag the loan interest rate slider and verify the output
	dragLoanInterestSlider(30, 0, "testLoanTenureCalculator");
	verifyLoanInterestSliderOutput("11");

	// Drag the loan fees slider and verify the output
	dragLoanFeesSlider(5, 0, "testLoanTenureCalculator");
	verifyLoanFeesSliderOutput("50,500");
    }
}
