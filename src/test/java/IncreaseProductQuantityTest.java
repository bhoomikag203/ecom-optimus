import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.HomePage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IncreaseProductQuantityTest extends BaseTest {
    CartPage cartPage;

    @Test
    public void shouldIncreaseProductQuantity() throws InterruptedException {
        HomePage homePage = new LoginPage(driver).login(prop.getProperty("password"));
        ProductPage productPage = homePage.searchProduct("Round Neck Shirt");
        String productName = productPage.getProductName();
        cartPage = productPage.addToCart("M", "White");
        double total = cartPage.increaseQuantity(productName, 7);
        Assert.assertEquals(total, cartPage.getTotalPrice());
    }

}
