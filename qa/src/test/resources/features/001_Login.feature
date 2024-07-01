@PayzliPOS
Feature: User verify the functionalities of Payzli POS Login module.

  @PayzliPOS_Login @mobile @Regression
  Scenario Outline:S001: User login to the Payzli POS Application
    Given I am on the Payzli POS Login screen
    And I verify the UI of Login screen
    And I enter the "<EMAILID>" and "<PASSWORD>" in Login screen
    When I select the "<BUSINESSNAME>" and "<LOCATION>" from switch locations pop up
    Then I should see the Dashboard screen
    Examples:
      | EMAILID   | PASSWORD   | LOCATION   | BUSINESSNAME   |
      | &EMAILID& | &PASSWORD& | &LOCATION& | &BUSINESSNAME& |

  @PayzliPOS_Login @mobile @Regression
  Scenario Outline:S002: User validate the functionality of eye icon in the password text box (When enabled)
    Given I am on the Dashboard screen
    And I tap logout under profile menu
    When I enter the "<EMAILID>" and "<PASSWORD>" with eye icon enabled
    Then I should see the entered "<PASSWORD>" in visible
    Examples:
      | EMAILID   | PASSWORD   |
      | &EMAILID& | &PASSWORD& |

  @PayzliPOS_Login @mobile @Regression
  Scenario Outline:S003: User validate the functionality of eye icon in the password text box (When disabled)
    Given I am on the Payzli POS Login screen
    When I disable eye icon in password text box
    Then I should see the entered "<PASSWORD>" in black circle format
    Examples:
      | PASSWORD   |
      | &PASSWORD& |

  @PayzliPOS_Login @mobile @Regression
  Scenario:S004: User verify the navigation of Sign Up screen
    Given I am on the Payzli POS Login screen
    When I tap Sign Up button in Login screen
    Then I should see the Sign Up screen

  @PayzliPOS_Login @mobile @Regression
  Scenario:S005: User validate the Forgot Password hyperlink in Login screen
    Given I am on the Payzli POS Sign Up screen
    And I tap the login button in Sign Up Screen
    When I tap on Forgot Password hyperlink
    Then I should see the Forgot Password screen

  @PayzliPOS_Login @mobile @Regression
  Scenario Outline:S006: User validates the remember me checkbox functionality (when checked)
    Given I am on the Forgot Password screen
    And I navigate to the Login screen
    And I enter the "<EMAILID>" and "<PASSWORD>" in Login screen and check remember me check box
    And I select the "<BUSINESSNAME>" and "<LOCATION>" from switch locations pop up
    When I Relaunch the application
    Then I should see the Dashboard screen
    Examples:
      | EMAILID   | PASSWORD   | LOCATION   | BUSINESSNAME   |
      | &EMAILID& | &PASSWORD& | &LOCATION& | &BUSINESSNAME& |

  @PayzliPOS_Login @mobile @Regression
  Scenario Outline:S007: User validates the remember me checkbox functionality (when unchecked)
    Given I am on the Payzli POS Login screen
    And I enter the "<EMAILID>" and "<PASSWORD>" in Login screen
    And I select the "<BUSINESSNAME>" and "<LOCATION>" from switch locations pop up
    And I tap logout under profile menu
    When I Relaunch the application
    Then I should see the "<EMAILID>" and "<PASSWORD>" has not saved and not able to login directly
    Examples:
      | EMAILID   | PASSWORD   | LOCATION   | BUSINESSNAME   |
      | &EMAILID& | &PASSWORD& | &LOCATION& | &BUSINESSNAME& |

  @PayzliPOS_Login @mobile @Regression
  Scenario Outline:S008: User validate the functionality of Privacy Policy hyperlink in Login screen
    Given I am on the Payzli POS Login screen
    When I tap on the "<PRIVACYPOLICY>" hyperlink in Login screen
    Then I should see the Privacy Policy screen
    Examples:
      | PRIVACYPOLICY  |
      | Privacy Policy |

  @PayzliPOS_Login @mobile @Regression
  Scenario Outline:S009: User validates the functionality of Terms & conditions hyperlink in Login screen
    Given I am on the Payzli POS Login screen
    When I tap on the "<TERMS&CONDITION>" hyperlink in Login screen
    Then I should see the Terms & Conditions screen
    Examples:
      | TERMS&CONDITION    |
      | Terms & Conditions |
