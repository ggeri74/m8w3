Feature: Login Action

  Scenario: Navigate to login page
    Given User is on mentorship page not yet logged in
    When User clicks on login button on the main page
    Then Login page appears

  Scenario: Successful login
    Given User is on the login page with proper credentials entered
    When User clicks on login button on the login page
    Then Main page appears with logged in user

  Scenario Outline: Failed login
    Given User is on the login page with wrong <email> and <password> entered
    When User clicks on login button on the login page
    Then Error message <errorMessage> appears on the login page

    Examples:
      | email                  | password | errorMessage                          |
      | gergely_glosz@epam.com | wrong    | Invalid password                      |
      | wrong@wrong.com        | wrong    | There isn't an account for this email |



  Scenario Outline: Search works
    Given User is on mentorship page logged in
    When User entered a <searchString>
    Then <searchResult> for <searchString> given back

    Examples:
      | searchString | searchResult |
      | gergely      | found        |
      | lorem ipsum  | not found    |
      | blabla       | not found    |
      | glosz        | found        |