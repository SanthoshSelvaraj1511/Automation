Feature: User verify the functionalities of Payzli POS Forgot Password module.

  @PayzliPOS_ForgotPassword @Regression @mobile
  Scenario Outline:S001: User validates the hyperlinks from Forgot Password screen
    Given I am on the Payzli POS Login screen
    And I navigate to Forgot Password screen
    When I tap the "<HYPERLINK>" from the Forgot Password screen
    Then I should see the respective screen related to the "<HYPERLINK>" tapped
    Examples:
      | HYPERLINK          |
      | Sign Up            |
      | Privacy Policy     |
      | Terms & Conditions |
