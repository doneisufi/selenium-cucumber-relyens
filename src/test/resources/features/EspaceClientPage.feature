Feature: Espace Client
  As a user
  When I am on the Espace Client page
  I want to navigate to the Professionnel de santé section
  So that I can attempt to log in with invalid credentials

  Scenario: Attempt to connect to the Professionnel de santé page with invalid credentials
    Given I open the Relyens homepage for the second time
    And I click Espace client button
    And I open the Professionnel de santé page
    Then I should see the Professionnel de santé title
    When I enter invalid credentials and try to login
    Then I should see an error message
