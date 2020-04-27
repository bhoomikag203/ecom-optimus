import com.optimusEcom.pages.CartPage;
import com.optimusEcom.pages.HomePage;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class AddProductWithMultipleSizeAndColorTest extends BaseTest {
    ProductPage productPage;

    @Test
    public void shouldAddProductWithMultipleSizeAndColor() {
        String sizeM = "M";
        String sizeS = "S";
        ArrayList<String> sizes = new ArrayList<>();
        sizes.add(sizeM);
        sizes.add(sizeS);
        productPage = new LoginPage(driver).login(prop.getProperty("password"))
                .searchProduct("Round Neck Shirt 16");
        productPage.addToCart(sizeM, "White");
        driver.navigate().back();
        productPage.addToCart(sizeS, "Silver");
        CartPage cartPage = new HomePage(driver).navigateToCart();
        Collections.sort(sizes);
        Assert.assertEquals(cartPage.getSizeList(), sizes);
    }
}
