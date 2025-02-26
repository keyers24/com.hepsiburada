package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.tabletPage;
import util.driverOperations;

public class tabletStepDefinitions {

    tabletPage tabletPage= new tabletPage(driverOperations.getDriver());
    String detailPagePrice;

    @When("Go to all categories > electronics > {string} category")
    public void goToAllCategoriesElectronicsCategory(String tablet) {
        tabletPage.goToAllCategoriesElectronicsCategoryPage(tablet);
    }

    @And("{string} option is filtered")
    public void optionIsFiltered(String brand) {
        tabletPage.optionIsFilteredPage(brand);
    }

    @And("The {string} size filter is applied")
    public void theSizeFilterIsApplied(String size) {
        tabletPage.theSizeFilterIsAppliedPage(size);
    }

    @And("Click on the highest priced product")
    public void clickOnTheHighestPricedProduct() {
        tabletPage.clickOnTheHighestPricedProductPage();

    }

    @And("Added to the basket on the product detail page")
    public void addedToTheBasketOnTheProductDetailPage() {
        tabletPage.addedToTheBasketOnTheProductDetail();
    }

    @Then("Prices equality is verified")
    public void pricesEqualityIsVerified() {
        tabletPage.pricesEqualityIsVerifiedPage();
    }
}
