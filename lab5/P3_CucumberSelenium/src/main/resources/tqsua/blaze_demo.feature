Feature: Buy a plane ticket

  Scenario: Select departure and destination city
    When I navigate to "https://blazedemo.com"
    And I choose "Boston" as the departure city
    And I choose "London" as the destination city
    And I press the 'Find Flights' button
    Then I should be in the page "BlazeDemo - reserve"

    When I choose flight 1
    Then I should be in the page "BlazeDemo Purchase"

    When I type 'David' on the 'inputName' field
    And I type 'Dummy Address' on the 'address' field
    And I type 'Aveiro' on the 'city' field
    And I type '12345' on the 'zipCode' field
    And I choose 'American Express' as the Card Type
    And I type '12345678' on the 'creditCardNumber' field
    And I type 'David' on the 'nameOnCard' field
    And I press the 'Purchase Flight' button
    Then I should be in the page "BlazeDemo Confirmation"

