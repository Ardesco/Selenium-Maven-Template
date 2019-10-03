@all
@eggs
Feature: Google Search For Eggs

  Scenario: Search for eggs
    Given I navigate to Google page
    When I search for "Eggs!"
    Then search result is displayed
    Then first result should contain word "eggs"