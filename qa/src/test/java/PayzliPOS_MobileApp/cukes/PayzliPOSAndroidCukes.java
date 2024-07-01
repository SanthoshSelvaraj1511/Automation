package PayzliPOS_MobileApp.cukes;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        monochrome = true,
//        tags = "@Regression",
        tags = "@NewAppointments",
        glue = {"PayzliPOS_MobileApp"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//                "cap.utilities.CustomGherkinStepListener"
                }
)

public class PayzliPOSAndroidCukes extends AbstractTestNGCucumberTests {

}
