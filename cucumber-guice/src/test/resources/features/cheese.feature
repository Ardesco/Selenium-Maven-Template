@all
@cheese
Feature: Google Search For Cheese

  Scenario: Search for cheese
    Given I navigate to Google page
    When I search for "Cheese!"
    Then search result is displayed
    And first result should contain word "cheese"
    And I purposefully fail this scenario to get a screenshot