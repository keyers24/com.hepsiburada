package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.productReviewEvaluationPage;
import util.driverOperations;

public class productReviewEvaluationStepDefinitions {

    productReviewEvaluationPage evaluationPage= new productReviewEvaluationPage(driverOperations.getDriver());


    @Given("navigate to website")
    public void navigateToWebsite() {
        evaluationPage.navigateToWebsiteMethod();
    }

    @When("The relevant {string} is searched for")
    public void theRelevantIsSearchedFor(String product){
        evaluationPage.theRelevantIsSearchedForMethod(product);
    }

    @And("Randomly selected product")
    public void randomlySelectedProduct() {
        evaluationPage.randomlySelectedProductMethod();
    }

    @And("The detail page of the relevant product is accessed")
    public void theDetailPageOfTheRelevantProductIsAccessed() {
        evaluationPage.theDetailPageOfTheRelevantProductIsAccessedMethod();
    }

    @And("The Reviews tab is navigated to, and Sort by: Newest Review is selected")
    public void theReviewsTabIsNavigatedToAndSortByNewestReviewIsSelected() {
        evaluationPage.theReviewsTabIsNavigatedToAndSortByNewestReviewIsSelectedMethod();
    }

    @And("One of the options, either thumbsUp or thumbsDown, is selected")
    public void oneOfTheOptionsEitherThumbsUpOrThumbsDownIsSelected() {

        evaluationPage.oneOfTheOptionsEitherThumbsUpOrThumbsDownIsSelectedMethod();
    }

    @Then("The selection is confirmed")
    public void theSelectionIsConfirmed() {
        evaluationPage.theSelectionIsConfirmedMethod();
    }


}



