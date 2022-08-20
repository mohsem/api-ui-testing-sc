Feature: Playground Scenarios

  Scenario: Dynamic Id
    When I go to test page
    And I navigate to "Dynamic ID" page
    Then I should be able to click the button

  Scenario: Delayed Load
    When I go to test page
    And I navigate to "Load Delay" page
    Then I should be able to click the button

  Scenario: Hidden Layers
    When I go to test page
    And I navigate to "Hidden Layers" page
    Then I should be able to click to the green button
    And I should be able to click the button
