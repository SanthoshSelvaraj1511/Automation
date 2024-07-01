package PayzliPOS_MobileApp.screens;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import cap.common.BaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;

import java.net.URL;
import java.util.Objects;



public class LoginScreen extends BaseScreen {
    public LoginScreen(WebDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Login']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Login']")
    public static WebElement labelLoginInLoginScreen;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
    @iOSXCUITFindBy(id = "Email")
    public static WebElement txtBoxEmailAddress;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    @iOSXCUITFindBy(id = "Password")
    public static WebElement txtBoxPassword;

    @AndroidFindBy(xpath = "//android.widget.EditText/android.widget.Button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Password']/following-sibling::XCUIElementTypeButton")
    public static WebElement eyeIconPassword;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Remember Me\"]/preceding-sibling::android.widget.CheckBox")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Remember Me']/preceding-sibling::XCUIElementTypeSwitch")
    public static WebElement checkBoxRememberMe;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Forgot Password?']")
    @iOSXCUITFindBy(id = "Forgot Password?")
    public static WebElement hyperLinkForgotPassword;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Sign Up']")
    @iOSXCUITFindBy(id = "Sign Up")
    public static WebElement btnSignUp;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Privacy Policy']")
    @iOSXCUITFindBy(id = "Privacy Policy")
    public static WebElement hyperLinkPrivacyPolicy;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Terms & Conditions\"]")
    @iOSXCUITFindBy(id = "Terms & Conditions")
    public static WebElement hyperLinkTermsOfUse;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Login']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    public static WebElement btnLogin;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Select']")
    @iOSXCUITFindBy(id = "Select")
    public static WebElement btnSelectOnTenantAndBranch;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Dashboard')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Dashboard')]")
    public static WebElement labelDashboard;

    @AndroidFindBy(xpath = "//android.view.View/child::android.widget.ImageView/following-sibling::android.view.View[1]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]")
    public static WebElement btnProfile;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Logout']")
    @iOSXCUITFindBy(id = "Logout")
    public static WebElement btnLogout;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Sign Up']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sign Up']")
    public static WebElement labelSignUp;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Reset\"]")
    @iOSXCUITFindBy(id = "Reset")
    public static WebElement btnReset;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Login\"]")
    @iOSXCUITFindBy(id = "Login")
    public static WebElement btnLoginInSignUpScreen;


    protected String elementStaticTextIOS = new StringBuilder()
            .append("//XCUIElementTypeStaticText[@name=\"")
            .append("<<TEXT>>")
            .append("\"]").toString();
    protected String strHyperlinkInLoginIOS = new StringBuilder()
            .append("//XCUIElementTypeButton[@name='")
            .append("<<TEXT>>")
            .append("']").toString();

    protected String strLocationName = new StringBuilder()
            .append("(//android.view.View[@content-desc='")
            .append("<<SALONNAME>>")
            .append("'])[1]").toString();

    protected String businessName = new StringBuilder()
            .append("//android.view.View[@content-desc='")
            .append("<<SALONNAME>>")
            .append("']").toString();

    protected String strHyperLink = new StringBuilder()
            .append("//android.widget.Button[@content-desc='")
            .append("<<TEXT>>")
            .append("']").toString();

    public boolean verifyDashboardScreen() {

        waitForElement(labelDashboard);
        return verifyElement(labelDashboard);
    }

    public boolean setVerifyBusinessName(String strBusinessName) {
        By getBusinessName = null;
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            getBusinessName = By.xpath(businessName.replace("<<SALONNAME>>", strBusinessName));
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            getBusinessName = By.xpath(elementStaticTextIOS.replace("<<TEXT>>", strBusinessName));
        }
        int tryAgain = 0;
        while (!verifyElementWithoutWait(getBusinessName)) {
            if (tryAgain == 20) {
                break;
            }
            swipeUpDirection();
            tryAgain++;
        }
        waitForElement(getBusinessName);
        return verifyElement(getBusinessName);
    }

    public void selectSalonInSwitchBusiness(String strSalon, String strLocation) {
        By Salon = null;
        By Location = null;
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            Salon = By.xpath(businessName.replace("<<SALONNAME>>", strSalon));
            click(Salon);
            Location = By.xpath(strLocationName.replace("<<SALONNAME>>", strLocation));

            int tryAgain = 0;
            while (!verifyElement(Location)) {
                if (tryAgain == 20) {
                    break;
                }
                swipeUpDirection();
                verifyElement(Location);
                tryAgain = tryAgain + 1;

            }
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            waitForSecond(2);
            Salon = By.xpath(elementStaticTextIOS.replace("<<TEXT>>", strLocation));
            click(Salon);
            Location = By.xpath(elementStaticTextIOS.replace("<<TEXT>>", strSalon));
            waitForSecond(1);
            while (!verifyElement(Location)) {
                swipeUpDirection();
            }
        }
        waitForElement(Location);
        click(Location);
    }

    public boolean verifyUIOfLoginScreen() {
        waitForElement(labelLoginInLoginScreen);
        verifyElementWithoutTryCatch(labelLoginInLoginScreen);
        verifyElementWithoutTryCatch(txtBoxEmailAddress);
        verifyElementWithoutTryCatch(txtBoxPassword);
        verifyElementWithoutTryCatch(checkBoxRememberMe);
        verifyElementWithoutTryCatch(hyperLinkForgotPassword);
        swipeUpDirection();
        verifyElementWithoutTryCatch(btnLogin);
        verifyElementWithoutTryCatch(btnSignUp);
        verifyElementWithoutTryCatch(hyperLinkPrivacyPolicy);
        verifyElementWithoutTryCatch(hyperLinkTermsOfUse);
        return true;
    }

    public void enterEmailAndPassword(String strEmail, String strPassword) {
        click(txtBoxEmailAddress);
        click(txtBoxEmailAddress);
        enterValue(txtBoxEmailAddress, strEmail);
        click(txtBoxPassword);
        click(txtBoxPassword);
        enterValue(txtBoxPassword, strPassword);
    }

    public void enterEmailAndPasswordIOS(String strEmail, String strPassword) {
        waitForElement(txtBoxEmailAddress);
        clearTextBox(txtBoxEmailAddress);
        enterValue(txtBoxEmailAddress, strEmail);
        clickLastKeyInKeyboard();
        waitForElement(txtBoxPassword);
        txtBoxPassword.clear();
        txtBoxPassword.sendKeys(strPassword);

    }

    public void checkRememberMe() {
        waitForSecond(3);
        swipeUpDirection();
        waitForElement(checkBoxRememberMe);
        click(checkBoxRememberMe);
    }

    public void enterEmailAndVisiblePasswordIOS(String strEmail, String strPassword) {
        enterValue(txtBoxEmailAddress, strEmail);
        clickLastKeyInKeyboard();
        waitForElement(eyeIconPassword);
        click(eyeIconPassword);
        txtBoxPassword.clear();
        txtBoxPassword.sendKeys(strPassword);
    }

    public void enterEmailAndVisiblePassword(String strEmail, String strPassword) {
        enterValue(txtBoxEmailAddress, strEmail);
        click(eyeIconPassword);
        enterValue(txtBoxPassword, strPassword);
    }

    public void tapLoginButton() {
        if (!verifyElementWithoutWait(btnLogin)) {
            swipeUpDirection();
        }
        click(btnLogin);
    }

    public void tapLoginButtonInSignupScreen() {

        if (!verifyElementWithoutWait(btnLoginInSignUpScreen)) {
            swipeUp();
        }
        waitForElement(btnLoginInSignUpScreen);
        click(btnLoginInSignUpScreen);
    }

    public void tapLoginButtonInSignupScreenIOS() {

        if (!btnLoginInSignUpScreen.isDisplayed()) {
            swipeUp();
        }
        waitForElementClickable(btnLoginInSignUpScreen);
        click(btnLoginInSignUpScreen);
    }

    public void tapSelectButtonInTenantAndBranch() {
        waitForElement(btnSelectOnTenantAndBranch);
        click(btnSelectOnTenantAndBranch);
    }


    public void logout() {
        waitForElementClickable(btnProfile);
        click(btnProfile);
        click(btnLogout);
    }

    public void tapEyeIconOfPassword() {
        waitForElementClickable(eyeIconPassword);
        click(eyeIconPassword);
    }

    public boolean verifyRememberMeCheckBoxUncheck(String strEmail, String strPassword) {
        waitForSecond(3);
        String email = txtBoxEmailAddress.getText();
        String password = txtBoxPassword.getText();
        boolean isVerified = false;
        if (email != strEmail && password != strPassword) {
            isVerified = true;
        }
        return isVerified;
    }


    public boolean verifySavedEmail(String strEmail) {
        click(txtBoxEmailAddress);
        String email = txtBoxEmailAddress.getText();
        click(labelLoginInLoginScreen);

        boolean isVerified = false;
        if (email.contains(strEmail)) {
            isVerified = true;
        }
        return isVerified;
    }

    public boolean verifyEyeIconOfPassword(String strPassword) {
        String pwd = txtBoxPassword.getText();
        boolean isVerified = false;
        if (pwd.contains(strPassword)) {
            isVerified = true;
        }
        return isVerified;
    }

    public boolean verifyAsteriskInPasswordIOS(String strPassword) {
        boolean isVerified = false;
        txtBoxPassword.clear();
        txtBoxPassword.sendKeys(strPassword);
        String newPasswordText = txtBoxPassword.getText();
        int newPasswordLength = strPassword.length();
        char hidePasswordSymbol = '\u25CF';
        String strNewHidePassword = Character.toString(hidePasswordSymbol);
        String passwordCover = new String(new char[newPasswordLength]).replace("\0", strNewHidePassword);
        if (Objects.equals(newPasswordText, passwordCover)) {
            isVerified = true;
        }
        return isVerified;
    }

    public boolean verifyAsteriskInPassword(String strPassword) {
        enterValue(txtBoxPassword, strPassword);
        waitForElement(eyeIconPassword);
        click(eyeIconPassword);
        String hiddenpwd = txtBoxPassword.getText();
        boolean isVerified = false;
        if (hiddenpwd.contains(strPassword)) {
            isVerified = true;
        }
        return isVerified;
    }

    public boolean verifyLoginFailErrorMessage() {
        boolean isVerified = false;

        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            fluentWait(By.xpath("(//android.view.View[@content-desc='Oops. Looks like you missed this.'])[1]"));
            fluentWait(By.xpath("(//android.view.View[@content-desc='Oops. Looks like you missed this.'])[2]"));
            isVerified = true;
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            fluentWait(By.xpath("//XCUIElementTypeOther[contains(@name,'Looks like you missed this')]"));
            isVerified = true;
        }
        return isVerified;
    }

    public boolean verifySignUpForm() {
        waitForElement(labelSignUp);
        return verifyElement(labelSignUp);
    }

    public void tapForgotPassword() {
        waitForSecond(2);
        waitForElementClickable(hyperLinkForgotPassword);
        click(hyperLinkForgotPassword);
    }


    public boolean verifyForgotPassword() {
        if (!verifyElementWithoutWait(btnReset)) {
            if (!verifyElementWithoutWait(btnLoginInSignUpScreen)) {
                swipeUp();
            }
            waitForSecond(2);
            click(btnLoginInSignUpScreen);
            tapForgotPassword();
        }
        waitForElement(btnReset);
        return verifyElement(btnReset);
    }


    public void navigateBack() {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            driver.navigate().back();
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            swipeLeftIOS();
            reLaunchApp();
        }
    }

    public void tapHyperlinkInLogin(String strText) {
        swipeUp();
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            waitForSecond(2);
            swipeUp();
            WebElement Hyperlink = waitForElement(By.xpath(strHyperLink.replace("<<TEXT>>", strText)));
            click(Hyperlink);
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            waitForSecond(2);
            By Hyperlink = By.xpath(strHyperlinkInLoginIOS.replace("<<TEXT>>", strText));
            while (!verifyElementWithoutWait(Hyperlink)) {
                swipeUpDirection();
            }
            click(Hyperlink);
        }
    }

    public void reLaunchApplication() {
        reLaunchApp();
    }


}