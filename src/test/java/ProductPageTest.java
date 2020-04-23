import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductPageTest extends TestBase {
    ProductPage productPage;

    public ProductPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        initialize();
        productPage = new ProductPage();
    }

    @Test
    public void shouldAddProductToCart() throws InterruptedException {
        productPage = new LoginPage()
                .login(prop.getProperty("password"))
                .searchProduct("Round Neck Shirt 16");
        Assert.assertTrue(productPage.addToCart());
    }

    @Test
    public void shouldCheckIfProductIsAddedToCart() throws InterruptedException {
        productPage = new LoginPage()
                .login(prop.getProperty("password"))
                .searchProduct("Round Neck Shirt 16");
        productPage.addToCart();
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.viewCart();
        Assert.assertEquals(cartPage.getProductName(),productName);
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }
}
