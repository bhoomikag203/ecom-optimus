import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.HomePage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

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
    public void shouldCheckIfProductIsAddedToCart() throws InterruptedException {
        productPage = new LoginPage()
                .login(prop.getProperty("password"))
                .searchProduct("Round Neck Shirt 16");
        String productName = productPage.getProductName();
        CartPage cartPage = productPage.addToCart("S", "White");
        Assert.assertEquals(cartPage.getProductName(), productName);
    }

    @Test
    public void shouldAddProductWithMultipleSizeAndColor() throws InterruptedException {
        HomePage homePage = new LoginPage().login(prop.getProperty("password"));
        homePage.searchProduct("Round Neck Shirt 16");
        String sizeM = "M";
        String sizeS = "S";
        ArrayList<String> sizes = new ArrayList<>();
        sizes.add(sizeM);
        sizes.add(sizeS);
        productPage.addToCart(sizeM, "White");
        driver.navigate().back();
        productPage.addToCart(sizeS, "Silver");
        CartPage cartPage = homePage.navigateToCart();
        Collections.sort(sizes);
        Assert.assertEquals(cartPage.getSizeList(), sizes);
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }
}
