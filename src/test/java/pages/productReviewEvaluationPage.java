package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class productReviewEvaluationPage extends baseTest {

    private static final Logger log = Logger.getLogger("productReviewEvaluationPage");

    Actions actions;
    public productReviewEvaluationPage(WebDriver driver){
        super(driver,"src/main/resources/locator/productReviewEvaluation.json");
        actions = new Actions(driver);
    }
    public void navigateToWebsiteMethod(){
        waitForPageToLoad(driver);
        click(getElement("oneTrustBtn"));
        checkElement(getElement("myAccountBtn"));

    }
    public void theRelevantIsSearchedForMethod(String product){
        waitForPageToLoad(driver);
        getElement("searchDiv").click();
        waitForPageToLoad(driver);
        sendKeysElement(getElement("searchInput"),product);
        getElement("searchInput").sendKeys(Keys.ENTER);
    }
    public void randomlySelectedProductMethod(){
        waitForPageToLoad(driver);
        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul[id='1'] li div a"))
        );

        if (products.isEmpty()) {
            log.warning("product list not found");
        }

        log.info("number of products : "+products.size());

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement randomProduct = products.get(randomIndex);

        actions.moveToElement(randomProduct).perform();
        wait.until(ExpectedConditions.elementToBeClickable(randomProduct));
        randomProduct.click();

    }
    public void theDetailPageOfTheRelevantProductIsAccessedMethod(){
        waitForPageToLoad(driver);
        switchToNewTabAndFindElement(driver);
        click(getElement("evaluationBtn"));
    }

    public void theReviewsTabIsNavigatedToAndSortByNewestReviewIsSelectedMethod(){
        waitForPageToLoad(driver);
        checkElement(getElement("dropdown"));
        scrollToElement(getElement("dropdown"));
        actions.moveToElement(getElement("dropdown")).perform();
        wait.until(ExpectedConditions.elementToBeClickable(getElement("dropdown")));
        click(getElement("dropdown"));
        click(getElement("Latest"));
    }

    public void oneOfTheOptionsEitherThumbsUpOrThumbsDownIsSelectedMethod(){
        waitForPageToLoad(driver);
        actions.moveToElement(getElement("like")).perform();
        wait.until(ExpectedConditions.elementToBeClickable(getElement("like")));
        click(getElement("like"));

    }
    public void theSelectionIsConfirmedMethod(){
        Assert.assertEquals(getElement("alert").getText(),"Teşekkür Ederiz.");
    }
}
