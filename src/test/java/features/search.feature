@Product
Feature: Product
  @Evaluation
  Scenario Outline: Product Review Evaluation
    Given navigate to website
    When  The relevant "<product>" is searched for
    And   Randomly selected product
    And   The detail page of the relevant product is accessed
    And   The Reviews tab is navigated to, and Sort by: Newest Review is selected
    And   One of the options, either thumbsUp or thumbsDown, is selected
    Then  The selection is confirmed

    Examples:
      |product|
      |iphone 13|



