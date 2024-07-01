package PayzliPOS_MobileApp.cukes;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        monochrome = true,
//        tags = "@PayzliPOS_Login",
//        tags = "@PayzliPOS_SignUp",
//        tags = "@PayzliPOS_ForgotPassword",
//        tags = "@PayzliPOS_Administration",
//        tags = "@PayzliPOS_MyProfile",
//        tags = "@PayzliPOS_Customer",
//        tags = "@PayzliPOS_NewSale",
        tags = "@Regression",
//        tags = "@PayzliPOS_Reports",
//        tags = "@NewAppointments",
//        tags = "@PayzliPOS_Appointments12",
//        tags = "@PayzliPOS_Dashboard",
//        tags = "@TimeTracker",
        glue = {"PayzliPOS_MobileApp"},
//        dryRun = true,
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//                "cap.utilities.CustomGherkinStepListener"
                }
)

public class PayzliPOSIOSCukes extends AbstractTestNGCucumberTests {

}
