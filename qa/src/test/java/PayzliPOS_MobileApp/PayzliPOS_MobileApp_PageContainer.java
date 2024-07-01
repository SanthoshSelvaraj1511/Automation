package PayzliPOS_MobileApp;


import PayzliPOS_MobileApp.screens.*;
import cap.utilities.DateUtil;
import cap.utilities.SharedDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;
import java.util.Map;


public class PayzliPOS_MobileApp_PageContainer {


    private WebDriver driver;

    //Demo Mobile Application Screens
    public static SignUpScreen signUpScreen;
    public static LoginScreen loginScreen;


    public static ForgotPasswordScreen forgotPasswordScreen;

    public static Scenario myScenario;

    public static LinkedHashMap<String, String> printTestDataMap = new LinkedHashMap<String, String>();

    public PayzliPOS_MobileApp_PageContainer() {
        driver = SharedDriver.getMobileDriver();
        initScreens();
    }

    private void initScreens() {
        //Mobile Application Screens
        signUpScreen = new SignUpScreen(driver);
        loginScreen = new LoginScreen(driver);


        forgotPasswordScreen = new ForgotPasswordScreen(driver);


    }


    @Before("@mobile")
    public void before(Scenario scenario1) {
        System.out.println("Scenario Name: " + scenario1.getName());
        printTestDataMap.clear();
        myScenario = scenario1;
    }

    @After("@mobile")
    public void after(Scenario scenario) {
        if (printTestDataMap.size() > 0) {
            for (Map.Entry<String, String> entry : printTestDataMap.entrySet()) {
                scenario.log(entry.getKey().concat(": ").concat(entry.getValue()));
            }
        }

        System.out.println("Scenario Completed at :: " + DateUtil.getCurrentDate("MM/dd/yy hh:mm:ss aaa"));
        scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES),
                "image/png", "");
        System.out.println("Scenario Name: " + scenario.getName());
        System.out.println("Scenario Status: " + scenario.getStatus());
    }


}
