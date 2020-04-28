import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import com.optimusEcom.pages.ProductSize;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAddedToCartTest extends BaseTest {
    ProductPage productPage;

    @Test
    public void shouldCheckIfProductIsAddedToCart() {
        productPage = new LoginPage(driver)
                .login()
                .searchProduct("Round Neck Shirt 16");
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.addToCart(ProductSize.S, "White");
        Assert.assertEquals(cartPage.getProductName(), productName);
    }

}
