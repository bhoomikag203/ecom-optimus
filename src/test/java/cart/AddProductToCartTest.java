package cart;

import baseTest.BaseTest;
import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.annotations.Test;

public class AddProductToCartTest extends BaseTest {

    @Test
    public void shouldCheckIfProductIsAddedToCart() {
        Product product = new ProductBuilder().withName("Round Neck Shirt 16")
                .withSize(ProductSize.M)
                .withColor(ProductColor.White)
                .build();

        page.getInstance(LoginPage.class)
                .login()
                .searchProduct(product)
                .addToCart(product)
                .assertProductAddedToCart(product);

    }

}
