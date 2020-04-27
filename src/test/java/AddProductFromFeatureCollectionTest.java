import com.optimusEcom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductFromFeatureCollectionTest extends BaseTest {
    HomePage homePage;

    @Test
    public void shouldAddProductFromFeatureCollection() {
        homePage = new LoginPage(driver).login(prop.getProperty("password"));
        ProductPage productPage = homePage.addProductFromFeatureCollection();
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.addToCart("S", "White");
        Assert.assertEquals(cartPage.getProductName(),productName);
    }

}
