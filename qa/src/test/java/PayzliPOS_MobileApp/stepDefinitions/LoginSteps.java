package PayzliPOS_MobileApp.stepDefinitions;

import PayzliPOS_MobileApp.PayzliPOS_MobileApp_PageContainer;
import cap.utilities.TestDataUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private PayzliPOS_MobileApp_PageContainer PayzliPOSMobileAppPageContainer;

    public LoginSteps(PayzliPOS_MobileApp_PageContainer PayzliPOSMobileAppPageContainer) {
        this.PayzliPOSMobileAppPageContainer = PayzliPOSMobileAppPageContainer;
    }

    @And("I verify the UI of Login screen")
    public void iVerifyTheUIOfLoginScreen() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyUIOfLoginScreen());
    }

    @And("I enter the {string} and {string} in Login screen")
    public void iEnterTheAndTapLogin(String strEmail, String strPassword) {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            PayzliPOSMobileAppPageContainer.loginScreen.enterEmailAndPassword(TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPassword));
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            PayzliPOSMobileAppPageContainer.loginScreen.enterEmailAndPasswordIOS(TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPassword));
        }
        PayzliPOSMobileAppPageContainer.loginScreen.tapLoginButton();
    }

    @When("I select the {string} and {string} from switch locations pop up")
    public void iSelectTheAndFromSwitchBusiness(String strBusiness, String strLocation) {
        PayzliPOSMobileAppPageContainer.loginScreen.selectSalonInSwitchBusiness(TestDataUtil.getValue(strBusiness), TestDataUtil.getValue(strLocation));
        PayzliPOSMobileAppPageContainer.loginScreen.tapSelectButtonInTenantAndBranch();
    }

    @Then("I should see the Dashboard screen")
    public void iShouldSeeTheDashboardScreen() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyDashboardScreen());
    }

    @Given("I am on the Dashboard screen")
    public void iAmOnTheDashboardScreen() {

        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyDashboardScreen());
    }

    @And("I tap logout under profile menu")
    public void iGoToProfileAndTapLogout() {
        PayzliPOSMobileAppPageContainer.loginScreen.logout();

    }

    @When("I disable eye icon in password text box")
    public void iDisableEyeIconInPasswordTextBox() {
        PayzliPOSMobileAppPageContainer.loginScreen.tapEyeIconOfPassword();
    }

    @Then("I should see the entered {string} in visible")
    public void theEnteredShouldBeVisible(String strPassword) {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyEyeIconOfPassword(TestDataUtil.getValue(strPassword)), "Password is Visible");
    }

    @Then("I should see the entered {string} in black circle format")
    public void iShouldSeeTheEnteredPasswordInAsteriskFormat(String strPassword) {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyAsteriskInPassword(TestDataUtil.getValue(strPassword)));
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyAsteriskInPasswordIOS(TestDataUtil.getValue(strPassword)));
        }
    }

    @Then("I should see the {string} and {string} has not saved and not able to login directly")
    public void iShouldSeeTheAndNotSavedAndErrorMessageShouldAppearWhenTapLogin(String strEmail, String strPassword) {

            if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
                PayzliPOSMobileAppPageContainer.loginScreen.tapLoginButton();
                Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyLoginFailErrorMessage());
                Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyRememberMeCheckBoxUncheck(TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPassword)));
            } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
                Assert.assertFalse(PayzliPOSMobileAppPageContainer.loginScreen.verifySavedEmail(strEmail));
                Assert.assertFalse(PayzliPOSMobileAppPageContainer.loginScreen.verifyEyeIconOfPassword(strPassword));
//               Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.emailAndPasswordWhenEyeIconDisabled(strEmail, strPassword));
                PayzliPOSMobileAppPageContainer.loginScreen.tapLoginButton();
                Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyLoginFailErrorMessage());
            }

    }

    @Then("I should see the Sign Up screen")
    public void iShouldSeeTheSignUpScreen() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifySignUpForm());
    }

    @When("I tap on Forgot Password hyperlink")
    public void iTapForgotPassword() {
        PayzliPOSMobileAppPageContainer.loginScreen.tapForgotPassword();
    }

    @Then("I should see the Forgot Password screen")
    public void iShouldSeeTheForgotPasswordScreen() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyForgotPassword());
    }

    @When("I enter the {string} and {string} with eye icon enabled")
    public void iEnterTheWithEyeIconEnabled(String strEmail, String strPassword) {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            PayzliPOSMobileAppPageContainer.loginScreen.enterEmailAndVisiblePassword(TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPassword));
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            PayzliPOSMobileAppPageContainer.loginScreen.enterEmailAndVisiblePasswordIOS(TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPassword));
        }
    }

    @Given("I am on the Forgot Password screen")
    public void iAmOnTheForgotPasswordScreen() {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyForgotPassword());
    }

    @And("I navigate to the Login screen")
    public void iNavigateToTheLoginScreen() {
        PayzliPOSMobileAppPageContainer.loginScreen.navigateBack();
    }


    @When("I tap on the {string} hyperlink in Login screen")
    public void iTapOnTheHyperlinkInLoginScreen(String strHyperlink) {
        PayzliPOSMobileAppPageContainer.loginScreen.tapHyperlinkInLogin(strHyperlink);
    }

    @Then("I should see the {string} and {string} are saved")
    public void iShouldSeeTheAndSavedAndAbleToLoginWithoutReenteringTheCredentials(String strEmail, String strPassword) {
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifySavedEmail(TestDataUtil.getValue(strEmail)));
        PayzliPOSMobileAppPageContainer.loginScreen.tapEyeIconOfPassword();
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyEyeIconOfPassword(TestDataUtil.getValue(strPassword)));
    }

    @Then("I able to {string} without re-entering the {string} in login form")
    public void iShouldSeeTheAndSaved(String strSalon, String strLocation) {
        PayzliPOSMobileAppPageContainer.loginScreen.tapLoginButton();
        PayzliPOSMobileAppPageContainer.loginScreen.selectSalonInSwitchBusiness(TestDataUtil.getValue(strSalon), TestDataUtil.getValue(strLocation));
        PayzliPOSMobileAppPageContainer.loginScreen.tapSelectButtonInTenantAndBranch();
        Assert.assertTrue(PayzliPOSMobileAppPageContainer.loginScreen.verifyDashboardScreen());
    }

    @And("I tap the login button in Sign Up Screen")
    public void iTapTheLoginButtonInSignUpScreen() {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            PayzliPOSMobileAppPageContainer.loginScreen.tapLoginButtonInSignupScreen();
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            PayzliPOSMobileAppPageContainer.loginScreen.tapLoginButtonInSignupScreenIOS();
        }
    }

    @And("I enter the {string} and {string} in Login screen and check remember me check box")
    public void iEnterTheInLoginScreenAndCheckRememberMe(String strEmail, String strPassword) {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            PayzliPOSMobileAppPageContainer.loginScreen.enterEmailAndPassword(TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPassword));
            PayzliPOSMobileAppPageContainer.loginScreen.checkRememberMe();
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            PayzliPOSMobileAppPageContainer.loginScreen.enterEmailAndPasswordIOS(TestDataUtil.getValue(strEmail), TestDataUtil.getValue(strPassword));
            PayzliPOSMobileAppPageContainer.loginScreen.checkRememberMe();
        }
        PayzliPOSMobileAppPageContainer.loginScreen.tapLoginButton();
    }
    @When("I Relaunch the application")public void iRelaunchTheApplication() {
        PayzliPOSMobileAppPageContainer.loginScreen.reLaunchApplication();
    }}




