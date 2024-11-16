package sharedDrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.apache.logging.log4j.LogManager; // Importing Log4j LogManager
import org.apache.logging.log4j.Logger; // Importing Log4j Logger

public class DriverSetup
{
    // Declaring WebDriver instance
    private static WebDriver driver;

    // Declaring Logger instance for logging
    public static Logger logger;

    /**
     * Method to initialize WebDriver based on the browser type
     * @param browser - Type of browser (chrome/edge)
     * @return WebDriver instance
     */
    
    public static WebDriver getDriver(String browser)
    {
	// Initializing logger for DriverSetup class
	logger = LogManager.getLogger(DriverSetup.class);

	// Switch case to handle different browser types
	switch (browser.toLowerCase()) {
	case "chrome":
	    // Initializing ChromeDriver and navigating to the URL
	    driver = new ChromeDriver();
	    driver.get("https://emicalculator.net/");
	    driver.manage().window().maximize();
	    break;

	case "edge":
	    // Initializing EdgeDriver and navigating to the URL
	    driver = new EdgeDriver();
	    driver.get("https://emicalculator.net/");
	    driver.manage().window().maximize();
	    break;

	default:
	    // Logging an error message if the browser type is not supported
	    logger.error("Browser type not supported: " + browser);
	    throw new IllegalArgumentException("Browser type not supported: " + browser);
	}
	return driver;
    }

}
