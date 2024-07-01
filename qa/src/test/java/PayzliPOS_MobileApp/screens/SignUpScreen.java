package PayzliPOS_MobileApp.screens;

import cap.common.BaseScreen;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Objects;

import static PayzliPOS_MobileApp.PayzliPOS_MobileApp_PageContainer.loginScreen;
import static PayzliPOS_MobileApp.screens.LoginScreen.btnReset;

public class SignUpScreen extends BaseScreen {

    public SignUpScreen(WebDriver driver) {
        super(driver);
    }

    FluentWait wait = new FluentWait(driver);

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Login']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Login']")
    public static WebElement txtLogin;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Sign Up\"]"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Sign Up']")
    })
    @iOSXCUITFindBy(accessibility = "Sign Up")
    public static WebElement btnSignUp;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Sign Up']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Sign Up']")
    public static WebElement txtSignUp;

    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    @iOSXCUITFindBy(id = "Business Name")
    public static WebElement txtBoxBusinessName;

    @AndroidFindBy(xpath = "//android.widget.EditText[2]")
    @iOSXCUITFindBy(id = "First Name")
    public static WebElement txtBoxFirstName;

    @AndroidFindBy(xpath = "//android.widget.EditText[3]")
    @iOSXCUITFindBy(id = "Last Name")
    public static WebElement txtBoxLastName;

    @AndroidFindBy(xpath = "//android.widget.EditText[4]")
    @iOSXCUITFindBy(id = "Email")
    public static WebElement txtBoxEmail;

    @AndroidFindBy(xpath = "//android.widget.EditText[5]")
    @iOSXCUITFindBy(id = "Phone Number")
    public static WebElement txtBoxPhoneNumber;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sign Up\"]/preceding-sibling::android.widget.EditText[last()]/preceding-sibling::android.widget.EditText[last()]")
    @iOSXCUITFindBy(id = "Password")
    public static WebElement txtBoxPassword;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sign Up\"]/preceding-sibling::android.widget.EditText[last()]")
    @iOSXCUITFindBy(id = "Confirm Password")
    public static WebElement txtBoxConfirmPassword;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sign Up\"]/preceding-sibling::android.widget.EditText[last()]/preceding-sibling::android.widget.EditText[last()]/android.widget.Button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Password']/following-sibling::XCUIElementTypeButton")
    public static WebElement eyeIconPassword;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sign Up\"]/preceding-sibling::android.widget.EditText[last()]/android.widget.Button")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Confirm Password']/following-sibling::XCUIElementTypeButton")
    public static WebElement eyeIconConfirmPassword;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Sign Up\"]"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Sign Up']")
    })
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Sign Up']")
    public static WebElement btnSignUpForm;

    protected String password = new StringBuilder()
            .append("//android.view.View[@content-desc='")
            .append("<<PASSWORD>>")
            .append("']").toString();

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Login']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    public static WebElement btnLoginInSignUp;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Privacy Policy'])[1]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Privacy Policy'])[1]")
    public static WebElement txtPrivacyPolicy;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Terms']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Terms']")
    public static WebElement txtTermsOfUse;

    @iOSXCUITFindBy(id = "Terms")
    public static WebElement txtTermsOfUseVerification;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    public static WebElement btnDoneInPrivacyPolicyAndTermsOfUse;

    @AndroidFindBy(xpath = "//android.view.View/following-sibling::android.widget.EditText[2]")
    public static WebElement txtIndustryType;

    public boolean verifyLoginScreen() {
        if (verifyElementWithoutWait(LoginScreen.labelDashboard)) {
            loginScreen.logout();
        }
        if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            if (verifyElementWithoutWait(btnReset)) {
                reLaunchApp();
            }
        }
        if (!verifyElementWithoutWait(txtLogin)) {
            swipeUpDirection();
            click(btnLoginInSignUp);
        }
        waitForElement(txtLogin);
        return verifyElement(txtLogin);
    }

    public boolean verifySignUpInLoginScreen() {
        swipeUp();
        waitForElement(btnSignUp);
        return verifyElement(btnSignUp);
    }

    public void tapSignUpForm() {
        if (!verifyElementWithoutWait(btnSignUp)) {
            swipeUpDirection();
        }
        waitForElementClickable(btnSignUp);
        click(btnSignUp);
    }

    public boolean verifySignUpInLoginScreenIOS() {
        while (!verifyElementWithoutWait(btnSignUp)) {
            swipeUpDirection();
        }
        return verifyElement(btnSignUp);
    }

    public boolean verifyTextSignUpInSignUpScreen() {
        if (!verifyElementWithoutWait(txtSignUp)) {
            swipeDownDirection2();
            swipeDownDirection2();
        }
        return verifyElement(txtSignUp);
    }

    public boolean verifySignUpFormUI() {
        verifyElementWithoutTryCatch(txtBoxBusinessName);
        verifyElementWithoutTryCatch(txtBoxFirstName);
        verifyElementWithoutTryCatch(txtBoxLastName);
        verifyElementWithoutTryCatch(txtBoxEmail);
        verifyElementWithoutTryCatch(txtBoxPhoneNumber);
        swipeUp();
        verifyElementWithoutTryCatch(txtBoxPassword);
        verifyElementWithoutTryCatch(txtBoxConfirmPassword);
        return true;
    }

    public void enterValuesInSignUp(String strBusinessName, String strFirstName, String strLastName, String strEmail, String strPhoneNumber, String strIndustryType, String strPassword, String strConfirmPassword) {
        enterValue(txtBoxBusinessName, strBusinessName);
        System.out.println(strBusinessName);
        enterValue(txtBoxFirstName, strFirstName);
        System.out.println(strFirstName);

        enterValue(txtBoxLastName, strLastName);

        enterValue(txtBoxEmail, strEmail);
        System.out.println(strEmail);
//        swipeByElement(txtBoxEmail, txtBoxBusinessName);
        enterValue(txtBoxPhoneNumber, strPhoneNumber);
        swipeUp();
        txtIndustryType.click();
        WebElement dropDownIndustryType = waitForElement(By.xpath(password.replace("<<PASSWORD>>", strIndustryType)));
        click(dropDownIndustryType);
        enterValue(txtBoxPassword, strPassword);
        enterValue(txtBoxConfirmPassword, strConfirmPassword);
    }

    public void enterValuesInSignUpIOS(String strBusinessName, String strFirstName, String strLastName, String strEmail, String strPhoneNumber, String strPassword, String strConfirmPassword) {
        enterValue(txtBoxBusinessName, strBusinessName);
        enterValue(txtBoxFirstName, strFirstName);
        clickLastKeyInKeyboard();
        enterValue(txtBoxLastName, strLastName);
        clickLastKeyInKeyboard();
        txtBoxEmail.click();
        txtBoxEmail.clear();
        enterValue(txtBoxEmail, strEmail);
        clickLastKeyInKeyboard();
        enterValue(txtBoxPhoneNumber, randomGeneratedValueInt(strPhoneNumber));
        swipeByElement(txtBoxPhoneNumber, txtBoxBusinessName);
        enterValue(txtBoxPassword, strPassword);
        clickLastKeyInKeyboard();
        enterValue(txtBoxConfirmPassword, strConfirmPassword);
    }

    public boolean eyeIconVerificationForPassword(String strPassword, String strPwdCover) {
        String pwd = txtBoxPassword.getText();
        click(eyeIconPassword);
        pwd.contains(strPwdCover);
        enterValue(txtBoxPassword, strPassword);
        String visiblePwd = txtBoxPassword.getText();
        visiblePwd.contains(strPassword);
        return true;
    }

    public boolean verifyEyeIconDisabledForPasswordIOS(String strPassword) {
        boolean isVerified = false;
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

    public boolean verifyEyeIconEnabledForPasswordIOS(String strPassword) {
        boolean isVerified = false;
        click(eyeIconPassword);
        enterValue(txtBoxPassword, strPassword);
        String visiblePwd = txtBoxPassword.getText();
        if (Objects.equals(visiblePwd, strPassword)) {
            isVerified = true;
        }
        return isVerified;
    }

    public boolean verifyEyeIconEnabledForConfirmPasswordIOS(String strConfirmPassword) {
        boolean isVerified = false;
        click(eyeIconConfirmPassword);
        enterValue(txtBoxConfirmPassword, strConfirmPassword);
        String visibleConfirmPwd = txtBoxConfirmPassword.getText();
        if (Objects.equals(visibleConfirmPwd, strConfirmPassword)) {
            isVerified = true;
        }
        btnLoginInSignUp.sendKeys(Keys.RETURN);
        return isVerified;
    }

    public boolean verifyEyeIconDisabledForConfirmPasswordIOS(String strConfirmPassword) {
        boolean isVerified = false;
        String newPasswordText = txtBoxConfirmPassword.getText();
        int newPasswordLength = strConfirmPassword.length();
        char hidePasswordSymbol = '\u25CF';
        String strNewHidePassword = Character.toString(hidePasswordSymbol);
        String passwordCover = new String(new char[newPasswordLength]).replace("\0", strNewHidePassword);
        if (Objects.equals(newPasswordText, passwordCover)) {
            isVerified = true;
        }
        clickLastKeyInKeyboard();
        return isVerified;
    }

    public boolean eyeIconVerificationForConfirmPassword(String strConfirmPassword, String strPwdCover) {
        String confirmPwd = txtBoxConfirmPassword.getText();
        confirmPwd.contains(strPwdCover);
        click(eyeIconConfirmPassword);
        enterValue(txtBoxConfirmPassword, strConfirmPassword);
        String visibleConfirmPwd = txtBoxConfirmPassword.getText();
        visibleConfirmPwd.contains(strConfirmPassword);
//          btnLoginInSignUp.sendKeys(Keys.RETURN);
        return true;
    }

    public void tapSignUp() {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            swipeUp();
            waitForElement(btnSignUpForm);
            click(btnSignUpForm);
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            clickLastKeyInKeyboard();
        }
    }

    public boolean verifySuccessMessage() {
        boolean isVerified = false;
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            fluentWaitUntilVisible(By.xpath("//android.view.View[contains(@content-desc,'Almost')]"));
            isVerified = true;
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            fluentWaitUntilVisible(By.xpath("//XCUIElementTypeStaticText[contains(@name,'Almost done!')]"));
            isVerified = true;
        }
        return isVerified;
    }

    public boolean verifyErrorMessage() {
        boolean isVerified = false;
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            fluentWaitUntilVisible(By.xpath("//android.view.View[contains(@content-desc,'We already have an account using that email')]"));
            isVerified = true;
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            fluentWaitUntilVisible(By.xpath("//XCUIElementTypeStaticText[contains(@name,'We already have an account using that email')]"));
            isVerified = true;
        }
        return isVerified;
    }

    public void tapLoginInSignUp() {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            waitForSecond(2);
            swipeUp();
            click(btnLoginInSignUp);
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            if (!verifyElementWithoutWait(btnLoginInSignUp)) {
                swipeUpDirection();
            }
            click(btnLoginInSignUp);
        }
    }

    public boolean verifyPrivacyPolicy() {
        boolean isVerified = false;
        isVerified = verifyElement(txtPrivacyPolicy);
        return isVerified;
    }

    public boolean verifyTermsOfUse() {
        boolean isVerified = false;
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            waitForElement(txtTermsOfUse);
            isVerified = verifyElement(txtTermsOfUse);
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            waitForElement(txtTermsOfUseVerification);
            isVerified = verifyElement(txtTermsOfUseVerification);
        }
        return isVerified;
    }

    public void closePrivacyPolicyAndTermsOfUse() {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            driver.navigate().back();
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            waitForElementClickable(btnDoneInPrivacyPolicyAndTermsOfUse);
            click(btnDoneInPrivacyPolicyAndTermsOfUse);
        }
    }
}