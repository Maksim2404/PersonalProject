Feature: Application Login

  Scenario: Home page default login
    Given User is on landing page
    When User logged into app with right credentials userName and password
    Then Home page is populated
    And  Cards are displayed