import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAddedToCartTest extends BaseTest {
    ProductPage productPage;

    @Test
    public void shouldCheckIfProductIsAddedToCart() {
        productPage = new LoginPage(driver)
                .login(prop.getProperty("password"))
                .searchProduct("Round Neck Shirt 16");
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.addToCart("S", "White");
        Assert.assertEquals(cartPage.getProductName(), productName);
    }

}
