@all
Feature: Sharing state between steps

  Scenario: Sharing state between steps
    When I set variable in one class
    Then I retrieve variable in other class