package PayzliPOS_MobileApp.stepDefinitions;

import PayzliPOS_MobileApp.PayzliPOS_MobileApp_PageContainer;
import cap.helpers.Constants;
import cap.utilities.RandomGeneratorUtil;
import cap.utilities.TestDataUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SignUpSteps {
    private PayzliPOS_MobileApp_PageContainer PayzliPOSMobileAppPageContainer;

    public SignUpSteps(PayzliPOS_MobileApp_PageContainer PayzliPOSMobileAppPageContainer) {
        this.PayzliPOSMobileAppPageContainer = PayzliPOSMobileAppPageContainer;
    }

    @Given("I am on the Payzli POS Login screen")
    public void iAmOnThePayzliPOSLoginScreen() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyLoginScreen());
    }

    @When("I tap Sign Up button in Login screen")
    public void iTapOnTheSignUpButtonFromLoginScreen() {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifySignUpInLoginScreen());
            PayzliPOSMobileAppPageContainer.signUpScreen.tapSignUpForm();
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifySignUpInLoginScreenIOS());
            PayzliPOSMobileAppPageContainer.signUpScreen.tapSignUpForm();
        }
    }

    @Then("I should verify the UI of SignUp screen")
    public void iShouldSeeTheSgnUpFormWithBusinessNameFirstNameLastNameEmailPhoneNumberPasswordAndConfirmPassword() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifySignUpFormUI());
    }

    @Given("I am on the Payzli POS Sign Up screen")
    public void iAmOnThePayzliPOSSignUpScreen() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyTextSignUpInSignUpScreen());
    }

    @And("I enter the {string} {string} {string} {string} {string} {string} {string} and {string} in the Sign Up screen")
    public void iEnterTheInTheSignUpForm(String strBusinessName, String strFirstName, String strLastName, String strEmail, String strPhoneNumber, String strIndustryType,String strPassword, String strConfirmPassword) {
        String strEmailID = "";
        if (strEmail.contains(Constants.RANDOM_STRING)) {
            strEmailID = new StringBuilder().append(TestDataUtil.getValue(strEmail))
                    .append(RandomGeneratorUtil.getExecutionID()).append(Constants.MAILNATOR).toString();
        }
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            System.out.println("strBusinessName: "+strBusinessName);
            System.out.println("strBusinessName02 : "+TestDataUtil.getValue(strBusinessName));
            PayzliPOSMobileAppPageContainer.signUpScreen.enterValuesInSignUp(TestDataUtil.getValue(strBusinessName), TestDataUtil.getValue(strFirstName), TestDataUtil.getValue(strLastName), TestDataUtil.getValue(strEmailID), TestDataUtil.getValue(strPhoneNumber), TestDataUtil.getValue(strIndustryType), TestDataUtil.getValue(strPassword), TestDataUtil.getValue(strConfirmPassword));
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            PayzliPOSMobileAppPageContainer.signUpScreen.enterValuesInSignUpIOS(TestDataUtil.getValue(strBusinessName), TestDataUtil.getValue(strFirstName), TestDataUtil.getValue(strLastName), TestDataUtil.getValue(strEmailID), TestDataUtil.getValue(strPhoneNumber), TestDataUtil.getValue(strPassword), TestDataUtil.getValue(strConfirmPassword));
        }
    }

    @When("I tap on the {string} in {string} and {string} text box then Sign Up")
    public void iTapOnTheEyeIconInPasswordAndConfirmPasswordThenSignUp(String strPwdCover, String strPassword, String strConfirmPassword) {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.eyeIconVerificationForPassword(TestDataUtil.getValue(strPassword), TestDataUtil.getValue(strPwdCover)));
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.eyeIconVerificationForConfirmPassword(TestDataUtil.getValue(strConfirmPassword), TestDataUtil.getValue(strPwdCover)));
            PayzliPOSMobileAppPageContainer.signUpScreen.tapSignUp();
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyEyeIconDisabledForPasswordIOS(TestDataUtil.getValue(strPassword)));
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyEyeIconEnabledForPasswordIOS(TestDataUtil.getValue(strPassword)));
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyEyeIconDisabledForConfirmPasswordIOS(TestDataUtil.getValue(strConfirmPassword)));
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyEyeIconEnabledForConfirmPasswordIOS(TestDataUtil.getValue(strConfirmPassword)));
        }

    }

    @Then("I should see the success message and Login screen")
    public void iShouldSeeTheSuccessMessage() {
        try {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifySuccessMessage());
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyLoginScreen());
        } finally {
            PayzliPOSMobileAppPageContainer.signUpScreen.tapSignUpForm();
        }
    }

    @When("I Sign Up using existing values such as {string} {string} {string} {string} {string} {string} {string} and {string} in Sign Up screen")
    public void iEnterTheExistingValuesAndTapSignUpScreen(String strBusinessName, String strFirstName, String strLastName, String strEmail, String strPhoneNumber, String strIndustryType, String strPassword, String strConfirmPassword) {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            PayzliPOSMobileAppPageContainer.signUpScreen.enterValuesInSignUp(TestDataUtil.getValue(strBusinessName), TestDataUtil.getValue(strFirstName), TestDataUtil.getValue(strLastName), TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPhoneNumber), TestDataUtil.getValue(strIndustryType), TestDataUtil.getValue(strPassword), TestDataUtil.getValue(strConfirmPassword));
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            PayzliPOSMobileAppPageContainer.signUpScreen.enterValuesInSignUpIOS(TestDataUtil.getValue(strBusinessName), TestDataUtil.getValue(strFirstName), TestDataUtil.getValue(strLastName), TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPhoneNumber), TestDataUtil.getValue(strPassword), TestDataUtil.getValue(strConfirmPassword));
        }
        PayzliPOSMobileAppPageContainer.signUpScreen.tapSignUp();
    }

    @When("I enter the existing values with {string} in the following fields {string} {string} {string} {string} {string} {string} and {string} in Sign Up screen")
    public void iEnterTheExistingValuesWithUniqueEmailInTheFollowingFieldsInSignUpScreen(String strEmail, String strBusinessName, String strFirstName, String strLastName, String strPhoneNumber,String strIndustryType, String strPassword, String strConfirmPassword) {
        String strEmailID = "";
        if (strEmail.contains(Constants.RANDOM_STRING)) {
            strEmailID = new StringBuilder().append(TestDataUtil.getValue(strEmail))
                    .append(RandomGeneratorUtil.getExecutionID()).append(Constants.MAILNATOR).toString();
        }
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            PayzliPOSMobileAppPageContainer.signUpScreen.enterValuesInSignUp(TestDataUtil.getValue(strBusinessName), TestDataUtil.getValue(strFirstName), TestDataUtil.getValue(strLastName), TestDataUtil.getValue(strEmailID), TestDataUtil.getValue(strPhoneNumber), TestDataUtil.getValue(strIndustryType), TestDataUtil.getValue(strPassword), TestDataUtil.getValue(strConfirmPassword));
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            PayzliPOSMobileAppPageContainer.signUpScreen.enterValuesInSignUpIOS(TestDataUtil.getValue(strBusinessName), TestDataUtil.getValue(strFirstName), TestDataUtil.getValue(strLastName), TestDataUtil.getValue(strEmailID), TestDataUtil.getValue(strPhoneNumber), TestDataUtil.getValue(strPassword), TestDataUtil.getValue(strConfirmPassword));
        }
        PayzliPOSMobileAppPageContainer.signUpScreen.tapSignUp();
    }

    @Then("I should see the Error message")
    public void iShouldSeeTheErrorMessage() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyErrorMessage());
    }

    @When("I tap on the login button in Sign Up screen")
    public void iTapOnTheLoginFromSignUpScreen() {
        PayzliPOSMobileAppPageContainer.signUpScreen.tapLoginInSignUp();
    }

    @When("I tap on the {string} hyperlink in Sign Up screen")
    public void iTapOnTheHyperlinkInLoginScreen(String strHyperlink) {
        PayzliPOSMobileAppPageContainer.loginScreen.tapHyperlinkInLogin(strHyperlink);
    }


    @Then("I should see the Login screen")
    public void iShouldSeeTheLoginScreen() {
        try {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyLoginScreen());
        } finally {
            PayzliPOSMobileAppPageContainer.signUpScreen.tapSignUpForm();
        }
    }

    @Then("I should see the Privacy Policy screen")
    public void iShouldSeeThePrivacyPolicyScreen() {

        try {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyPrivacyPolicy());
        } finally {
            PayzliPOSMobileAppPageContainer.signUpScreen.closePrivacyPolicyAndTermsOfUse();
        }
    }

    @Then("I should see the Terms & Conditions screen")
    public void iShouldSeeTheTermsOfUseScreen() {
        try {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.signUpScreen.verifyTermsOfUse());
        } finally {
            PayzliPOSMobileAppPageContainer.signUpScreen.closePrivacyPolicyAndTermsOfUse();
        }
    }


}