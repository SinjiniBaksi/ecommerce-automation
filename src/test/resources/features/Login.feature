Feature: Login Functionality

  Background: User launches the application
    Given the application is launched

  @smoke
  Scenario: Successful login with valid credentials
    When user is on login page and enters credentials "td_user" and "td_password"
    Then the user is redirected to the homepage

  @smoke
  Scenario: Unsuccessful login with invalid credentials
    When user is on login page and enters credentials "td_invalid_user" and "td__invalid_password"
    Then the user is shown an error message "Invalid username or password"