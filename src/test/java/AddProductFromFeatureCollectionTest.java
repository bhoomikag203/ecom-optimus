import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import org.testng.annotations.Test;

public class AddProductFromFeatureCollectionTest extends BaseTest {

    @Test
    public void shouldAddProductFromFeatureCollection() {
        Product product=new ProductBuilder().build();

        new LoginPage(driver).login()
                .selectProductFromFeatureCollection(product)
                .addToCart()
                .assertProductAddedToCart(product);
    }

}
