package product;

import baseTest.BaseTest;
import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class AddProductWithMultipleSizeTest extends BaseTest {

    /*@Test
    public void shouldAddProductWithMultipleSize() {
        Product product = new ProductBuilder().withName("Round Neck Shirt 16").build();
        List<ProductSize> productSizes = new ArrayList<>();
        productSizes.add(ProductSize.S);
        productSizes.add(ProductSize.M);

        page.getInstance(LoginPage.class)
                .login()
                .searchProduct(product)
                .selectProductWithMultipleSizes(product, productSizes)
                .assertMultipleSizesAddedToCart(product, productSizes);
    }*/
}
