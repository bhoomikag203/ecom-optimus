import com.optimusEcom.pages.*;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
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
        productPage.addToCart(sizeM, ProductColor.White);
        driver.navigate().back();
        productPage.addToCart(sizeS, ProductColor.White);
        CartPage cartPage = new HomePage(driver).navigateToCart();
        Collections.sort(sizes);
        Assert.assertEquals(cartPage.getSizeList(), sizes);
    }
}
