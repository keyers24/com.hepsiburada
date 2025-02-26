package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import util.locatorReader;
import java.time.Duration;
import java.util.Set;
import java.util.logging.Logger;

public class baseTest {
    WebDriver driver;
    WebDriverWait wait;
    private locatorReader locatorReader;
    Actions actions;
    private static final Logger log = Logger.getLogger("baseTest");
    public baseTest(WebDriver driver,String jsonFilePath){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        this.actions=new Actions(driver);
        locatorReader = new locatorReader(jsonFilePath);
    }

    protected WebElement getElement(String elementName, Object... dynamicValues) {
        if (locatorReader == null) {
            throw new IllegalStateException("LocatorReader is not initialized. Please call initLocatorReader() first.");
        }

        String locatorType = locatorReader.getLocatorType(elementName);
        String locatorValue = locatorReader.getLocatorValue(elementName);

        // Dinamik değer varsa ve locatorValue içinde %s placeholder'ı bulunuyorsa, formatla.
        if (dynamicValues != null && dynamicValues.length > 0 && locatorValue.contains("%s")) {
            locatorValue = String.format(locatorValue, dynamicValues);
        }

        switch (locatorType.toLowerCase()) {
            case "id":
                return driver.findElement(By.id(locatorValue));
            case "xpath":
                return driver.findElement(By.xpath(locatorValue));
            case "css":
                return driver.findElement(By.cssSelector(locatorValue));
            case "name":
                return driver.findElement(By.name(locatorValue));
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }

    public WebElement checkElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement clickableElement(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        WebElement visibleElement = checkElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(visibleElement)).click();
    }

    public void sendKeysElement(WebElement element, String text) {
        checkElement(element).sendKeys(text);
    }

    public void scrollToElement(WebElement element) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();",element);
    }

    public void waitForPageToLoad(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String readyState = js.executeScript("return document.readyState").toString();

            if (readyState.equals("complete")) {
                break;
            }
        }
    }

    public void switchToNewTabAndFindElement(WebDriver driver) {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}
