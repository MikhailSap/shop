Feature: Login


  Scenario Outline: Successful login to the page and logout
    Given I open web browser
    When I navigate to login page
    And provide username as "<username>" and password as "<password>"
    And click on ligin button
    Then name should be "<name>"
    When  Open dropdown menu
    And click logout button
    Then user logged out


    Examples:
    | username | password | name |
    | admin | admin | admin |