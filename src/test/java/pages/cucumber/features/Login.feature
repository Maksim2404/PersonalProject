Feature: Application Login

  Scenario: Home page default login
    Given User is on landing page
    When User logged into app with right credentials userName "John" and password "1234"
    Then Home page is populated
    And  Cards are displayed

  Scenario: Home page default login
    Given User is on landing page
    When User logged into app with right credentials userName "Jane" and password "4321"
    Then Home page is populated
    And  Cards are displayed