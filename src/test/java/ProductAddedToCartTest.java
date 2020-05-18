import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import org.testng.annotations.Test;

public class ProductAddedToCartTest extends BaseTest {

    @Test
    public void shouldCheckIfProductIsAddedToCart() {
        Product product = new ProductBuilder().withName("Round Neck Shirt 16").
                build();

        new LoginPage(driver)
                .login()
                .searchProduct(product)
                .addToCart()
                .assertProductAddedToCart(product);

    }

}
