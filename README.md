# Cucumber (Selenium + Java) Demo Project

## Project Description:
This project demonstrates the implementation of a Selenium WebDriver test automation framework using the Cucumber BDD (Behavior-Driven Development) framework in Java. It provides a structured approach for automating web application testing by defining test scenarios in plain language using Gherkin syntax. The framework includes features such as browser configuration, test data management, reporting using Extent Reports with screenshot capture on test failure, and hooks for setup and teardown operations.

## Pre requisite
- Google Chrome Browser
- IDE

## How to run the project
- clone the project
- Open the TestRunner class (com.runner.TestRunner.java)
- Run the TestRunner class (by default all the test cases will be executed)
- Extent Reports are generated (test-output/spark/Index.html)

Screen shots will be captured for failed test cases, If screen shots are required for all test cases logic in the AfterScenario Method in Hooks.java class need to be updated.

## How to run in Firefox
- In Application.properties file, update the browser property to ‘FireFox’

## How to run in other browsers
- Download the respective driver file and place it in Drivers (src/test/resources/drivers)  folder
- Add the path of the driver in Application.properties file and update AppConfig.java and Hooks.java to load the new driver.

## Running specific Tags
- By default all test cases will be executed ( tags="@RT")
- To executed feature specific test cases update the tags in TestRunner.java
- The below tags has been configured
    - @FisrtNameValidation
    - @LastNameValidation
    - @EmailValidation
    - @CommentsFieldValidation
    - @ResetButtonValidation
    - @OrderValidation
    - @LableValidation

