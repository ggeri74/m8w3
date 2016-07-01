Feature: Search Action

  Scenario Outline: Search works
    Given User is on mentorship page logged in
    When User entered a <searchString>
    Then <searchResult> for <searchString> given back

    Examples:
      | searchString | searchResult |
      | gergelx      | found        |
      | lorem ipsum  | not found    |
      | blabla       | not found    |
      | glosz        | found        |