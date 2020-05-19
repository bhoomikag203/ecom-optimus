package cart;

import baseTest.BaseTest;
import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.annotations.Test;

public class RemoveProductFromCart extends BaseTest {
    Product product = new ProductBuilder().withName("Round Neck Shirt 16")
            .withSize(ProductSize.M)
            .withColor(ProductColor.White)
            .build();
    @Test
    public void shouldRemoveProductFromCart(){
        new LoginPage(driver)
                .login()
                .searchProduct(product)
                .addToCart(product)
                .removeProduct(product)
                .assertProductRemovedFromCart();

    }
}