package PayzliPOS_MobileApp.screens;

import cap.common.BaseScreen;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordScreen extends BaseScreen {
    public ForgotPasswordScreen(WebDriver driver) {
        super(driver);
    }

    protected String elementButtonIOS = new StringBuilder()
            .append("//XCUIElementTypeButton[@name='")
            .append("<<TEXT>>")
            .append("']").toString();

    protected String elementButtonAndroid = new StringBuilder()
            .append("//android.widget.Button[contains(@content-desc,'")
            .append("<<TEXT>>")
            .append("')]").toString();


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

    @AndroidFindBy(xpath = "//android.widget.EditText[6]")
    @iOSXCUITFindBy(id = "Password")
    public static WebElement txtBoxPassword;

    @AndroidFindBy(xpath = "//android.widget.EditText[7]")
    @iOSXCUITFindBy(id = "Confirm Password")
    public static WebElement txtBoxConfirmPassword;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Terms']")
    @iOSXCUITFindBy(id = "Terms")
    public static WebElement txtTermsOfUseVerification;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.Button[@content-desc=\"Forgot Password?\"]"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc=\"Quên mật khẩu?\"]")
    })
    @iOSXCUITFindBy(id = "Forgot Password?")
    public static WebElement btnForgotPasswordInLoginScreen;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Forgot Password\"]")
    @iOSXCUITFindBy(id = "Forgot Password")
    public static WebElement labelForgotPasswordInForgotPasswordScreen;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Forgot Password\"]/following-sibling::android.widget.EditText")
    @iOSXCUITFindBy(id = "Email")
    public static WebElement txtBoxEmailInForgotPasswordScreen;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Reset\"]")
    @iOSXCUITFindBy(id = "Reset")
    public static WebElement btnResetInForgotPasswordScreen;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Not Registered?')]")
    @iOSXCUITFindBy(id = "Not Registered?")
    public static WebElement labelNotRegisteredInForgotPasswordScreen;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Privacy Policy'])[1]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Privacy Policy'])[1]")
    public static WebElement txtPrivacyPolicy;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    public static WebElement btnDoneInPrivacyPolicyAndTermsOfUse;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.Button[@content-desc='Login']"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc=\"Đăng nhập\"]")
    })
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    public static WebElement btnLoginInSignUp;

    public void tapHyperLinkInForgotPasswordScreen(String strHyperlink) {
        By btnForgotPasswordInLoginScreen = null;

        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            btnForgotPasswordInLoginScreen = By.xpath(elementButtonAndroid.replace("<<TEXT>>", strHyperlink));
            click(btnForgotPasswordInLoginScreen);

        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            btnForgotPasswordInLoginScreen = By.xpath(elementButtonIOS.replace("<<TEXT>>", strHyperlink));
            click(btnForgotPasswordInLoginScreen);
        }
    }

    public boolean verifySignUpScreen() {
        verifyElementWithoutTryCatch(txtBoxBusinessName);
        verifyElementWithoutTryCatch(txtBoxFirstName);
        verifyElementWithoutTryCatch(txtBoxLastName);
        verifyElementWithoutTryCatch(txtBoxEmail);
        swipeByElement(txtBoxEmail, txtBoxBusinessName);
        verifyElementWithoutTryCatch(txtBoxPhoneNumber);
        verifyElementWithoutTryCatch(txtBoxPassword);
        verifyElementWithoutTryCatch(txtBoxConfirmPassword);
        return true;
    }

    public boolean verifyForgotPasswordScreen() {
        verifyElementWithoutTryCatch(labelForgotPasswordInForgotPasswordScreen);
        verifyElementWithoutTryCatch(txtBoxEmailInForgotPasswordScreen);
        verifyElementWithoutTryCatch(btnResetInForgotPasswordScreen);
        verifyElementWithoutTryCatch(labelNotRegisteredInForgotPasswordScreen);
        return true;
    }

    public void tapOnLoginButtonFromSignUpScreen() {
        if (!verifyElement(btnLoginInSignUp)) {
            swipeUp();
        }
        waitForElementClickable(btnLoginInSignUp);
        click(btnLoginInSignUp);
    }

    public void tapForgotPasswordButtonFromLoginScreen() {
        waitForElementClickable(btnForgotPasswordInLoginScreen);
        click(btnForgotPasswordInLoginScreen);

    }

    public boolean verifyTermsOfUse() {
        boolean isVerified = false;
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            waitForElement(txtTermsOfUseVerification);
            isVerified = verifyElement(txtTermsOfUseVerification);
        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            waitForElement(txtTermsOfUseVerification);
            isVerified = verifyElement(txtTermsOfUseVerification);
        }
        return isVerified;
    }

    public boolean verifyPrivacyPolicy() {
        boolean isVerified = false;
        isVerified = verifyElement(txtPrivacyPolicy);
        return isVerified;
    }

    public void closePrivacyPolicyAndTermsOfUse() {
        if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
            driver.navigate().back();
            driver.navigate().back();

        } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
            waitForElementClickable(btnDoneInPrivacyPolicyAndTermsOfUse);
            click(btnDoneInPrivacyPolicyAndTermsOfUse);
            swipeLeftIOS();
        }
    }

}

