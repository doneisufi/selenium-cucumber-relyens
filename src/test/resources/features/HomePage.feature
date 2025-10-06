Feature: Relyens homepage navigation
  As a user
  I want to access the homepage
  So that I can see the welcome content and more

  Scenario: Open homepage and navigate
    Given I open the Relyens homepage
    Then I should see the Relyens logo
    And I should see the Assurance and Risk Management text
    And I click on the "nav link text" navigation link and verify "pageIdentifier" visibility then click "button or link" present
