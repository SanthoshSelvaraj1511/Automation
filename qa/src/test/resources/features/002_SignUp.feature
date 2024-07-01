@PayzliPOS
Feature: User verify the functionalities of Payzli POS Sign Up module.

  @PayzliPOS_SignUp @mobile @Regression
  Scenario:S001: User verify the UI of Sign Up screen
    Given I am on the Payzli POS Login screen
    When I tap Sign Up button in Login screen
    Then I should verify the UI of SignUp screen

  @PayzliPOS_SignUp @mobile @Regression
  Scenario Outline:S002: User creates an account from Sign Up screen
    Given I am on the Payzli POS Sign Up screen
    And I enter the "<BUSINESSNAME>" "<FIRSTNAME>" "<LASTNAME>" "<EMAIL>" "<PHONENUMBER>" "<INDUSTRYTYPE>" "<PASSWORD>" and "<CONFIRMPASSWORD>" in the Sign Up screen
    When I tap on the "<EYEICON>" in "<PASSWORD>" and "<CONFIRMPASSWORD>" text box then Sign Up
    Then I should see the success message and Login screen
    Examples:
      | BUSINESSNAME          | FIRSTNAME          | LASTNAME          | EMAIL          | PHONENUMBER   | PASSWORD   | CONFIRMPASSWORD   | EYEICON   | INDUSTRYTYPE |
      | &BUSINESSNAME_RANDOM& | &FIRSTNAME_RANDOM& | &LASTNAME_RANDOM& | &EMAIL_RANDOM& | &PHONENUMBER& | &PASSWORD& | &CONFIRMPASSWORD& | &EYEICON& | Tattoo       |

  @PayzliPOS_SignUp @mobile @Regression
  Scenario Outline:S003: User Sign Up using existing details
    Given I am on the Payzli POS Sign Up screen
    When I Sign Up using existing values such as "<BUSINESSNAME>" "<FIRSTNAME>" "<LASTNAME>" "<EMAIL>" "<PHONENUMBER>" "<INDUSTRYTYPE>" "<PASSWORD>" and "<CONFIRMPASSWORD>" in Sign Up screen
    Then I should see the Error message
    Examples:
      | BUSINESSNAME   | FIRSTNAME   | LASTNAME   | EMAIL        | PHONENUMBER   | PASSWORD   | CONFIRMPASSWORD   | INDUSTRYTYPE |
      | &BUSINESSNAME& | &FIRSTNAME& | &LASTNAME& | &VALIDEMAIL& | &PHONENUMBER& | &PASSWORD& | &CONFIRMPASSWORD& | Tattoo       |

  @PayzliPOS_SignUp @mobile @Regression
  Scenario Outline:S004: User Sign Up with existing details but using unique email id
    Given I am on the Payzli POS Sign Up screen
    When I enter the existing values with "<UNIQUEEMAIL>" in the following fields "<BUSINESSNAME>" "<FIRSTNAME>" "<LASTNAME>" "<PHONENUMBER>" "<INDUSTRYTYPE>" "<PASSWORD>" and "<CONFIRMPASSWORD>" in Sign Up screen
    Then I should see the success message and Login screen
    Examples:
      | BUSINESSNAME   | FIRSTNAME   | LASTNAME   | UNIQUEEMAIL          | PHONENUMBER   | PASSWORD   | CONFIRMPASSWORD   | INDUSTRYTYPE |
      | &BUSINESSNAME& | &FIRSTNAME& | &LASTNAME& | &UNIQUEEMAIL_RANDOM& | &PHONENUMBER& | &PASSWORD& | &CONFIRMPASSWORD& | Tattoo       |

  @PayzliPOS_SignUp @mobile @Regression
  Scenario:S005: User verify the navigation of Login screen from Sign Up screen
    Given I am on the Payzli POS Sign Up screen
    When I tap on the login button in Sign Up screen
    Then I should see the Login screen

  @PayzliPOS_SignUp @mobile @Regression
  Scenario Outline:S006: User validates the Privacy Policy hyperlink in Sign Up screen
    Given I am on the Payzli POS Sign Up screen
    When I tap on the "<PRIVACYPOLICY>" hyperlink in Sign Up screen
    Then I should see the Privacy Policy screen
    Examples:
      | PRIVACYPOLICY  |
      | Privacy Policy |

  @PayzliPOS_SignUp @mobile @Regression
  Scenario Outline:S007: User validates the Terms of Use hyperlink in Sign Up screen
    Given I am on the Payzli POS Sign Up screen
    When I tap on the "<TERMS&CONDITION>" hyperlink in Sign Up screen
    Then I should see the Terms & Conditions screen
    And I tap on the login button in Sign Up screen
    Examples:
      | TERMS&CONDITION    |
      | Terms & Conditions |