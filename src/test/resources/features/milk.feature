@all
@milk
Feature: Google Search For Milk

  Scenario: Search for cheese
    Given I navigate to "google.com"
    When I search for "Milk!"
    Then first result should contain word "milk"