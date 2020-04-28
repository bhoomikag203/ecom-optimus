import com.optimusEcom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductFromFeatureCollectionTest extends BaseTest {
    HomePage homePage;

    @Test
    public void shouldAddProductFromFeatureCollection() {
        homePage = new LoginPage(driver).login();
        ProductPage productPage = homePage.addProductFromFeatureCollection();
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.addToCart(ProductSize.M, ProductColor.Silver);
        Assert.assertEquals(cartPage.getProductName(),productName);
    }

}
