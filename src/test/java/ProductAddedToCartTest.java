import com.optimusEcom.pages.*;
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
        CartPage cartPage = productPage.addToCart(ProductSize.S, ProductColor.White);
        Assert.assertEquals(cartPage.getProductName(), productName);
    }

}
