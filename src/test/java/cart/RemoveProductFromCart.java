package cart;

import baseTest.BaseTest;
import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Cart;
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

    Cart cart = new Cart();

    @Test
    public void shouldRemoveProductFromCart() {
        page.getInstance(LoginPage.class)
                .login()
                .searchProduct(product)
                .addToCart(product,cart)
                .removeProduct(product, cart)
                .assertProductRemovedFromCart();

    }
}