package commonScreenShotMethod;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil 
{
	public static void takeScreenshot(WebDriver driver, String screenshotName) 
	{
        // Convert WebDriver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) driver);

        // Call getScreenshotAs method to create image file
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        String filePath = "ScreenShots/" + screenshotName + ".png";
        File destFile = new File(filePath);

        // Copy file to the destination
        try 
        {
            FileUtils.copyFile(srcFile, destFile);
            //System.out.println("Screenshot saved at: " + filePath);
        } 
        catch (IOException e) 
        {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}

