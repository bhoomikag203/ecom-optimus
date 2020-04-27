import com.optimusEcom.driver.DInitializer;
import com.optimusEcom.util.ScreenshotHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.internal.TestResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties prop;

    @BeforeMethod
    public void setup() {
        //Driver initializer
        driver = new DInitializer("chrome").initialize();
        wait = new WebDriverWait(driver, 100);
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if(TestResult.FAILURE == result.getStatus()){
            new ScreenshotHelper().getScreenshot(driver);
        }
        driver.close();
    }
}