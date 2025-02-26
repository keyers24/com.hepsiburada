package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import util.driverOperations;

import java.util.logging.Logger;

@CucumberOptions(
        features = {"src/test/java/features"},
        glue={"stepDefinitions","util"},
        tags = "@Tablet",
        plugin = {
                "summary","pretty","html:Reports/CucumberReport/Reports.html",
                "json:Reports/CucumberReport/Report",

        }
)

public class runner extends AbstractTestNGCucumberTests {
    private static final Logger log = Logger.getLogger("priceComparisonPage");
    WebDriver driver;
    @BeforeMethod
    public void before(){
        String browser= Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        driver= driverOperations.initializeDriver(browser);
        log.info("Driver Initialize");

    }
    @AfterMethod
    public void after(){
        driver.quit();
        log.info("Driver Quit");
    }
}