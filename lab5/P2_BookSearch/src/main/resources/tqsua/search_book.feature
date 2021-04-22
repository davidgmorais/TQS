Feature: Book Search

  Background: A simple library
    Given a book with the title 'One good book', written by 'Anonymous', published in 14 March 2013
    And another book with the title 'Some other book', written by 'Tim Tomson', published in 23 August 2014
    And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 01 January 2015

  Scenario: Search books by author
    When the customer searches for books by 'Fred'
    Then 1 book should be found
    And Book 1 should have the title 'How to cook a dino'

  Scenario: Search book by publication date within a range
    When the customer searches for books from 01 January 2014 to 01 January 2016
    Then 2 books should be found
    And Book 1 should have the title 'Some other book'
    And Book 2 should have the title 'How to cook a dino'
