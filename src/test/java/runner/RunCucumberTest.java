package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// CucumberOptions annotation to specify the configuration for the Cucumber test
@CucumberOptions(
    // Path to the feature files
    features = "src/test/resources/features", 
    // Package containing the step definitions
    glue = "stepDefinitions", 
    // Plugins for generating reports
    plugin = { 
        "pretty", // Pretty format for console output
        "html:target/cucumber-reports.html", // HTML report
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // ExtentReports adapter
    }, 
    // If true, Cucumber will only check that every step in the feature files has a corresponding step definition
    dryRun = false,
    // If true, console output is more readable
    monochrome = true,
    // If true, the report will be published to the Cucumber Reports service
    publish = true
)

// Class to run the Cucumber tests with TestNG
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    // Uncomment the following method to run scenarios in parallel
    // @Override
    // @DataProvider(parallel = true)
    // public Object[][] scenarios() 
    // {
    //     return super.scenarios();
    // }

}
