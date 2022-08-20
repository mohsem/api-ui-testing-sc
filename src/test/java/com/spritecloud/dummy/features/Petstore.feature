Feature: Pet Store API

  Scenario Outline: Pet Store Inventory Checks
    When I request inventory details from store
    Then I should see the store inventory data
    When I add different pets to the store with "<name>" and "<status>"
    Then I should see available pet count increased
    When I delete one of the pets
    Then I should see available pet count decreased
    Examples:
      | name   | status       |
      | nohup  | available    |
      | kara   | available    |

  Scenario: User Operations
    When I create a user
    Then I can receive user details
    When I change user name with "ankaraliKovboy"
    Then I can receive changed user details
    When I delete registered user
    Then I should not receive user details for deleted user

  Scenario Outline: Pet Store Order
    When I add different pets to the store with "<name>" and "<status>"
    And I place an order to store
    Then I should find order details
    Examples:
      | name   | status       |
      | nohup  | available    |


