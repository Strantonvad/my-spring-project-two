Feature: Login

  Scenario Outline: Successful Login to the page and logout after
    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    When I click on login button
    Then name should be "<name>"
    Then click brand link
    Then click to create new button
    And I provide new brand as "<brand>"
    Then check added brand "<brand>"
    When Open dropdown menu
    And click logout button
    Then user logged out

    Examples:
      | username | password | name | brand |
      | admin | 111 | admin | new_brand |
