
@Tablet
Feature: Product
  Scenario Outline: Tablet Test
    Given navigate to website
    When  Go to all categories > electronics > "<subCategory>" category
    And   "<brand>" option is filtered
    And   The "<desiredScreen>" size filter is applied
    And   Click on the highest priced product
    And   Added to the basket on the product detail page
    Then  Prices equality is verified

    Examples:
    |subCategory|brand|desiredScreen|
    |tablet     |apple|13.2         |