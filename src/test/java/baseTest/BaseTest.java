package baseTest;

import com.optimusEcom.driver.DriverInitialize;
import com.optimusEcom.util.ScreenshotHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.internal.TestResult;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        //Driver initializer
        driver = new DriverInitialize("firefox").setUp();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if(TestResult.FAILURE == result.getStatus()){
            new ScreenshotHelper().getScreenshot(driver);
        }
        driver.close();
    }
}