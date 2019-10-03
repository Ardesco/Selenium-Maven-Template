@all
@milk
Feature: Google Search For Milk

  Scenario: Search for milk
    Given I navigate to Google page
    When I search for "Milk!"
    Then first result should contain word "milk"