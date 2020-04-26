import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.HomePage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CartPageTest extends TestBase {
    CartPage cartPage;

    public CartPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        driver = initialize();
        this.cartPage = new CartPage(driver);
    }

    @Test
    public void shouldIncreaseProductQuantity() throws InterruptedException {
//        this.cartPage = new CartPage();
        HomePage homePage = new LoginPage(driver).login(prop.getProperty("password"));
        ProductPage productPage = homePage.searchProduct("Round Neck Shirt");
        String productName = productPage.getProductName();
        cartPage = productPage.addToCart("M", "White");
        double total = cartPage.increaseQuantity(productName, 7);
        Assert.assertEquals(total, cartPage.getTotalPrice());
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }
}
