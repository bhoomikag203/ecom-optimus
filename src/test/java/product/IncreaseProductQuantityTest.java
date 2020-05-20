package product;

import baseTest.BaseTest;
import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.annotations.Test;

public class IncreaseProductQuantityTest extends BaseTest {

    @Test
    public void shouldIncreaseProductQuantity() {
        Product product = new ProductBuilder().withName("Round Neck Shirt 16")
                .withSize(ProductSize.M)
                .withColor(ProductColor.White)
                .build();

        new LoginPage(driver)
                .login()
                .searchProduct(product)
                .addToCart(product)
                .increaseQuantity(product,10)
                .assertSubTotal();

    }

}
