import com.optimusEcom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

public class AddProductWithMultipleSizeAndColorTest extends BaseTest {
    ProductPage productPage;

    @Test
    public void shouldAddProductWithMultipleSizeAndColor() {
        ProductSize sizeM = ProductSize.M;
        ProductSize sizeS = ProductSize.S;
        ArrayList<ProductSize> sizes = new ArrayList<>();
        sizes.add(sizeM);
        sizes.add(sizeS);
        productPage = new LoginPage(driver).login()
                .searchProduct("Round Neck Shirt 16");
        productPage.addToCart(sizes.get(0), "White");
        driver.navigate().back();
        productPage.addToCart(sizes.get(1), "Silver");
        CartPage cartPage = new HomePage(driver).navigateToCart();
        Collections.sort(sizes);
        Assert.assertEquals(cartPage.getSizeList(), sizes);
    }
}
