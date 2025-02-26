# hepsiburadaUIAutomation
## Project Overview

- **Page Object Model (POM)**: The framework is built using the Page Object Model pattern for better reusability and maintainability.
- **Technologies Used**:
  - Java 18.0.2
  - Selenium 4.27
- **Framework Highlights**:
  - Web automation using Selenium WebDriver.
  - Integration of dynamic waits and reusable components.
  - Use of TestNG  test execution and reporting
  - Use of cucumber report
  - Log to Log4j2
  - Scenario planning with Cucumber - Gherkin
- **Test Scenario**:
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
 - **BONUS Test Scenario**:
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

   
<img width="1855" alt="UI_Result" src="https://github.com/user-attachments/assets/d1497476-1fbd-4410-a130-264db72b6e8f" />

