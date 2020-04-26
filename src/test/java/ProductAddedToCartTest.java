import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductAddedToCartTest extends TestBase {
    ProductPage productPage;

    public ProductAddedToCartTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        driver = initialize();
        this.productPage = new ProductPage(driver);
    }

    @Test
    public void shouldCheckIfProductIsAddedToCart() {
        productPage = new LoginPage(driver)
                .login(prop.getProperty("password"))
                .searchProduct("Round Neck Shirt 16");
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.addToCart("S", "White");
        Assert.assertEquals(cartPage. getProductName(), productName);
    }

    @AfterMethod
    public void teardown(ITestResult result) throws IOException {
        getScreenShotOfFailedTest(result);
        driver.close();
    }
}
