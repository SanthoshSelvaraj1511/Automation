package PayzliPOS_MobileApp.stepDefinitions;


import PayzliPOS_MobileApp.PayzliPOS_MobileApp_PageContainer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Objects;

public class ForgotPasswordSteps {

    private PayzliPOS_MobileApp_PageContainer PayzliPOSMobileAppPageContainer;

    @And("I navigate to Forgot Password screen")
    public void iNavigateToForgotPasswordScreen() {
        PayzliPOSMobileAppPageContainer.forgotPasswordScreen.tapForgotPasswordButtonFromLoginScreen();
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.forgotPasswordScreen.verifyForgotPasswordScreen());
    }

    @When("I tap the {string} from the Forgot Password screen")
    public void iTapTheFromTheForgotPasswordScreen(String strHyperLink) {

        PayzliPOSMobileAppPageContainer.forgotPasswordScreen.tapHyperLinkInForgotPasswordScreen(strHyperLink);
    }

    @Then("I should see the respective screen related to the {string} tapped")
    public void iShouldSeeTheRespectiveScreenRelatedToTheClicked(String strHyperLink) {
        if (Objects.equals(strHyperLink, "Sign Up")) {
            try {
                Assert.assertTrue(PayzliPOSMobileAppPageContainer.forgotPasswordScreen.verifySignUpScreen());
            } finally {
                PayzliPOSMobileAppPageContainer.forgotPasswordScreen.tapOnLoginButtonFromSignUpScreen();
            }
        } else if (Objects.equals(strHyperLink, "Privacy Policy")) {
            try {
                Assert.assertTrue(PayzliPOSMobileAppPageContainer.forgotPasswordScreen.verifyPrivacyPolicy());
            } finally {
                PayzliPOSMobileAppPageContainer.forgotPasswordScreen.closePrivacyPolicyAndTermsOfUse();
            }
        } else if (Objects.equals(strHyperLink, "Terms & Conditions")) {
            try {
                Assert.assertTrue(PayzliPOSMobileAppPageContainer.forgotPasswordScreen.verifyTermsOfUse());
            } finally {
                PayzliPOSMobileAppPageContainer.forgotPasswordScreen.closePrivacyPolicyAndTermsOfUse();
            }
        }
    }

}

