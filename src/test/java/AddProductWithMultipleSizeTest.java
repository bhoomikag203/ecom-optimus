import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.annotations.Test;


public class AddProductWithMultipleSizeTest extends BaseTest {

    @Test
    public void shouldAddProductWithMultipleSizeAndColor() {
        Product product = new ProductBuilder().withName("Round Neck Shirt 16").build();

        new LoginPage(driver).login()
                .searchProduct(product)
                .selectProductWithMultipleSizes(ProductSize.M, ProductSize.S)
                .assertMultipleSizesAddedToCart(ProductSize.M, ProductSize.S);
    }
}
