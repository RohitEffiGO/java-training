Feature: Test accordian widget

  Background: 
    Given	driver and url for accordian
    Then redirect to demoqa
    And click on widgets and go to accordian

  Scenario: We will test all card header available in accordian widget
    Given path of all card headers get it and write it into some file
      | dynamic-xpath                      |
      | (//div[@class ='card']/child::div) |
    Then show the extracted data from file manual validation