Feature: User Registration

  Background: 
    Given I open the Contact Us page

  @Test_001 @RT
  Scenario: User successfully submits the form with valid input
    When the user enters the following information:
      | First Name | Last Name | Email               | Message       |
      | John       | Doe       | johndoe@example.com | Positive flow |
    And clicks the "Submit" button
    Then the user sees a thank you page

  ######################## FIRST NAME VALIDATIONS ###########################
  @Test_002 @FisrtNameValidation @RT
  Scenario Outline: User successfully submits the form with all possible valid values for first name
    When the user enters the following information:
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user sees a thank you page

    Examples: 
      | First Name                     | Last Name | Email               | Message             |
      | Jo                             | Doe       | johndoe@example.com | eaxctly with 2 char |
      | Johnsmith                      | Doe       | johndoe@example.com | more than 2 char    |
      | JohnABCDEFGHIJKLMNOPQRSTUVWXYZ | Doe       | johndoe@example.com | exactly 25 char     |

  @Test_003 @FisrtNameValidation @RT
  Scenario Outline: Submitting the form with a blank first name
    When the user enters the following information with blank first name
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user should see an error message

    Examples: 
      | First Name      | Last Name | Email               | Message        |
      |                 | Doe       | johndoe@example.com | This is a test |
      | !-empty space-! | Doe       | johndoe@example.com | This is a test |

  @Test_004 @FisrtNameValidation @RT
  Scenario Outline: Submitting the form with a invalid first name
    When the user enters the following information:
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user should see an error message

    Examples: 
      | First Name                    | Last Name | Email               | Message                 |
      | J                             | Doe       | johndoe@example.com | less that 2 character   |
      | JohnABCDEFGHIJKLMNOPQRSTUVWXY | Doe       | johndoe@example.com | more than 25 characters |

  ####################### LAST NAME VALIDATIONS ###########################
  @Test_005 @LastNameValidation @RT
  Scenario Outline: User successfully submits the form with all possible valid values for last name
    When the user enters the following information:
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    When clicks the "Submit" button
    Then the user sees a thank you page

    Examples: 
      | First Name | Last Name                      | Email               | Message             |
      | John       | Jo                             | johndoe@example.com | eaxctly with 2 char |
      | John       | Johnsmith                      | johndoe@example.com | more than 2 char    |
      | John       | JohnABCDEFGHIJKLMNOPQRSTUVWXYZ | johndoe@example.com | exactly 25 char     |

  @Test_006 @LastNameValidation @RT
  Scenario Outline: Submitting the form with a blank Last name
    When the user enters the following information with blank last name
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user should see an error message

    Examples: 
      | First Name | Last Name       | Email               | Message        |
      | Doe        |                 | johndoe@example.com | This is a test |
      | Doe        | !-empty space-! | johndoe@example.com | This is a test |

  @Test_007 @LastNameValidation @RT
  Scenario Outline: Submitting the form with invalid last name
    When the user enters the following information:
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user should see an error message

    Examples: 
      | First Name | Last Name                     | Email               | Message        |
      | J          | Do                            | johndoe@example.com | This is a test |
      | John       | JohnABCDEFGHIJKLMNOPQRSTUVWXY | johndoe@example.com | This is a test |

  ####################### EMAIL FIELD VALIDATIONS ###########################
  @Test_008 @EmailValidation @RT
  Scenario Outline: User successfully submits the form with all possible valid values for email
    When the user enters the following information:
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user sees a thank you page

    Examples: 
      | First Name | Last Name | Email                  | Message     |
      | John       | Doe       | john.deo@example.com   | valid email |
      | John       | Doe       | john_deo@example.com   | valid email |
      | John       | Doe       | john.deo1@example.com  | valid email |
      | John       | Doe       | johndeo@example-ca.com | valid email |

  @Test_009 @EmailValidation @RT
  Scenario Outline: Submitting the form with a blank email id
    When the user enters the following information with blank email
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user should see all fields required and invalid email error message

    Examples: 
      | First Name | Last Name | Email           | Message        |
      | Doe        | Dolly     |                 | This is a test |
      | Doe        | Dolly     | !-empty space-! | This is a test |

  @Test_010 @EmailValidation @RT
  Scenario Outline: Submitting the form with a invalid email id
    When the user enters the following information with invalid email:
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user should see invalid email error message

    Examples: 
      | First Name | Last Name | Email                | Message          |
      | John       | Doe       | johndoe              | No domain name   |
      | John       | Doe       | johndoeexample.com   | No @ symbol      |
      | John       | Doe       | johndoe@example      | No TLD           |
      | John       | Doe       | johndoe!@example.com | part name with ! |
      | John       | Doe       | johndoe@@example.com | 2 @ symbol       |
      | John       | Doe       | johndoe@example.co!m | Invalid TLD      |
      | John       | Doe       | johndoe@example.     | Invalid TLD      |
      | John       | Doe       | johndoe@example.c    | Invalid TLD      |
      | John       | Doe       | johndoe@example.comm | Invalid TLD      |

  ####################### COMMENTS FIELD VALIDATIONS ###########################
  @Test_011 @CommentsFieldValidation @RT
  Scenario Outline: Submitting the form with a blank comments
    When the user enters the following information with blank message:
      | First Name   | Last Name   | Email   | Message   |
      | <First Name> | <Last Name> | <Email> | <Message> |
    And clicks the "Submit" button
    Then the user should see an error message

    Examples: 
      | First Name | Last Name | Email               | Message         |
      | John       | Smith     | johndoe@example.com |                 |
      | John       | Smith     | johndoe@example.com | !-empty space-! |

  ####################### RESET BUTTON VALIDATIONS ###########################
  @Test_012 @ResetButtonValidation @RT
  Scenario: User resets the form after filling all the fields
    When the user enters the following information:
      | First Name | Last Name | Email               | Message       |
      | John       | Doe       | johndoe@example.com | Positive flow |
    And clicks the "Reset" button
    Then the fields in the form are reset

  ####################### ORDER VALIDATION ###########################
  @Test_013 @OrderValidation @RT
  Scenario: Verify the order of web elements
    Then user validates the order of webelements

  ####################### LABLE VALIDATION ###########################
  @Test_014 @LableValidation @RT
  Scenario: Verify the lable or place holder of web elements
    Then user validates the lable of webelements
