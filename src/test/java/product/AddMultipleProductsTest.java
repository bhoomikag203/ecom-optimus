package product;

import baseTest.BaseTest;
import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Cart;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.LoginPage;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AddMultipleProductsTest extends BaseTest {
    Product firstProduct = new ProductBuilder()
            .withName("Round Neck Shirt")
            .withColor(ProductColor.White)
            .withSize(ProductSize.M)
            .build();

    Product secondProduct = new ProductBuilder()
            .withName("Round Neck Shirt 16")
            .withColor(ProductColor.White)
            .withSize(ProductSize.M)
            .build();

    List<Product> products = new ArrayList();

    @Test
    public void shouldAddMultipleProductsToCart() {
        Cart cart = new Cart();
        products.add(firstProduct);
        products.add(secondProduct);

        page.getInstance(LoginPage.class).login()
                .searchProduct(firstProduct)
                .addMultipleProducts(products, cart)
                .viewCart()
                .assertMultipleProductsAddedToCart(products, cart);


    }

}