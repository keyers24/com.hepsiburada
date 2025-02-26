package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

public class driverOperations {
    static WebDriver driver;
    static WebDriverWait wait;
    static String baseURL=configReader.getProperty("URL");
    private static final Logger log = Logger.getLogger("driverOperations");
    public static   WebDriver initializeDriver(String browser){
        switch (browser) {
            case "Chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                driver =new ChromeDriver(options);
                log.info("Starting Chrome");
                break;
            case "Firefox":

                log.info("no driver");
                break;
        }

        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseURL);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return getDriver();
    }
    public static synchronized WebDriver getDriver(){return driver;}

}
