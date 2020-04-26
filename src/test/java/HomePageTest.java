import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends TestBase {
    HomePage homePage;

    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        driver = initialize();
        this.homePage = new HomePage(driver);
    }

    @Test
    public void shouldAddProductFromFeatureCollection() throws InterruptedException {
        homePage = new LoginPage(driver).login(prop.getProperty("password"));
        ProductPage productPage = homePage.addProductFromFeatureCollection();
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.addToCart("S", "White");
        Assert.assertEquals(cartPage.getProductName(),productName);
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        getScreenShotOfFailedTest(result);
        driver.close();
    }
}
