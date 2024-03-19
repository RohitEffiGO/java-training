Feature: Login testing for OrangeHRM website

  Background: 
    Given I am redirected to login page

  #  Scenario: Successful login
  #   Given Entered valid username and password
  #  When Click on login button
  # Then Should login successfully
  Scenario Outline: Failed Login
    Given Wrong user credentials with username: "<username>" and password: "<password>"
    When Click on login button
    Then Message "<message>" should appear

    Examples: 
      | username | password   | message             |
      | rohit    | pass       | Invalid credentials |
      | test     | laskjfdlka | Invalid credentials |
