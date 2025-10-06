Feature: Relyens Homepage Navigation
  As a user
  I want to navigate the Relyens homepage
  So that I can see the welcome content and access different sections

  Scenario: Open homepage and navigate at different sections
    Given I open the Relyens homepage
    Then I should see the Relyens logo
    And I should see the Assurance and Risk Management text
    And I click on different navigation links and verify their first content
    And I click a button in Relyens page then verify it's title
