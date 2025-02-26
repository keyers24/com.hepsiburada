package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class tabletPage extends baseTest{
    private static final Logger log = Logger.getLogger("tabletPage");
    Actions actions;
    private double productPrice;
    private double cartPrice;
    public tabletPage(WebDriver driver) {
        super(driver,"src/main/resources/locator/tablet.json");
        actions = new Actions(driver);
    }

    public void goToAllCategoriesElectronicsCategoryPage(String e) {
        waitForPageToLoad(driver);
        checkElement(getElement("menu"));
        actions.moveToElement(getElement("menu")).perform();

        checkElement(getElement("subCategory"));
        actions.moveToElement(getElement("subCategory")).perform();

        clickableElement(getElement("finalCategory"));
        click(getElement("finalCategory"));

    }


    public void optionIsFilteredPage(String e){
        waitForPageToLoad(driver);
        click(getElement("brand",e));
        waitForPageToLoad(driver);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.hepsiburada.com/apple-tablet-xc-3008012-b8849");

    }
    public void theSizeFilterIsAppliedPage(String e){
        waitForPageToLoad(driver);
        scrollToElement(getElement("inc"));
        click(getElement("inc"));
    }
    public void clickOnTheHighestPricedProductPage(){

        waitForPageToLoad(driver);
        List<WebElement> productElements = driver.findElements(By.xpath("//ul[@id='1']/li"));
        log.info("toplam ürün sayısı: "+ productElements.size());

        double maxPrice = 0;
        WebElement highestPricedProduct = null;

        for (WebElement product : productElements) {
            try {
                scrollToElement(product);
                List<WebElement> priceElements = product.findElements(By.xpath(".//*[@data-test-id='price-current-price']"));
                if (!priceElements.isEmpty()) {
                    WebElement priceElement = priceElements.get(0);
                    wait.until(ExpectedConditions.visibilityOf(priceElement));
                    String priceText = priceElement.getText();

                    String digits = priceText.replaceAll("[^0-9]", "");
                    if (!digits.isEmpty()) {
                        double price = Double.parseDouble(digits);

                        if (price > maxPrice) {
                            maxPrice = price;
                            highestPricedProduct = product;
                        }
                    }
                } else {
                    System.out.println(" ");
                }
            } catch (Exception e) {
                log.info("Hata: " + e.getMessage());
            }
        }
        log.info("En yüksek fiyat: " + maxPrice);
        if (highestPricedProduct != null) {
            WebElement productLink = highestPricedProduct.findElement(By.tagName("a"));
            scrollToElement(productLink);
            try {
                click(productLink);
                Thread.sleep(2000);
            } catch (Exception e) {
                log.info(" tıklama başarısız oldu" + e.getMessage());
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", productLink);
            }
        } else {
            throw new NoSuchElementException("En yüksek fiyatlı ürün bulunamadı.");
        }
    }

    public void addedToTheBasketOnTheProductDetail(){
        switchToNewTabAndFindElement(driver);
        waitForPageToLoad(driver);
        checkElement(getElement("detailPrice"));
        String productPriceText = getElement("detailPrice").getText();
        String productPriceDigits = productPriceText.replaceAll("[^0-9]", "");
        productPrice=Double.parseDouble(productPriceDigits);
        log.info("Ürün Detay Sayfa Fiyatı: " + productPrice);
        click(getElement("addToCard"));

    }
    public void pricesEqualityIsVerifiedPage(){
        click(getElement("basket"));
        waitForPageToLoad(driver);
        checkElement(getElement("cartPrice"));
        String productPriceText = getElement("cartPrice").getText();
        String productPriceDigits = productPriceText.replaceAll("[^0-9]", "");
        cartPrice=Double.parseDouble(productPriceDigits);
        log.info("Ürün Sepet Sayfa Fiyatı: " + cartPrice);
        if (productPrice == cartPrice) {
            System.out.println("Fiyatlar eşleşiyor. Ürün doğru şekilde sepete eklendi.");
        } else {
            System.out.println("Fiyatlar eşleşmiyor. Detay sayfası fiyatı: " + productPrice + ", Sepet fiyatı: " + cartPrice);
        }
    }
}
