import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import org.testng.annotations.Test;

public class IncreaseProductQuantityTest extends BaseTest {

    @Test
    public void shouldIncreaseProductQuantity() {
        Product product = new ProductBuilder().withName("Round Neck Shirt 16")
                .withQuantity(4)
                .build();

        new LoginPage(driver)
                .login()
                .searchProduct(product)
                .addToCart()
                .increaseQuantity(product)
                .assertIncreaseProductQuantity(product);

    }

}
