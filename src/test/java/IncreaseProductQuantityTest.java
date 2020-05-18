import com.optimusEcom.pages.*;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IncreaseProductQuantityTest extends BaseTest {
    CartPage cartPage;

    @Test
    public void shouldIncreaseProductQuantity() {
        HomePage homePage = new LoginPage(driver).login();
        ProductPage productPage = homePage.searchProduct("Round Neck Shirt");
        String productName = productPage.getProductName();
        cartPage = productPage.addToCart(ProductSize.M, ProductColor.White);
        double total = cartPage.increaseQuantity(productName, 7);Assert.assertEquals(total, cartPage.getSubTotalPrice());
    }

}
