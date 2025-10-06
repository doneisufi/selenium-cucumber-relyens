Feature: Relyens Homepage Navigation
  As a user
  I want to navigate the Relyens homepage
  So that I can see the welcome content and access different sections

  Scenario: Open homepage and navigate to a section
    Given I open the Relyens homepage
    Then I should see the Relyens logo
    And I should see the Assurance and Risk Management text
    And I click on the "nav link text" navigation link and verify "pageIdentifier" visibility then click "button or link" present
    And I click a button in Relyens page then verify it's title
