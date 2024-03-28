Feature: Validate google search redirection

  Scenario: Google search
    Given google url
      | url            |
      | https://www.google.com |
    And try to validate
