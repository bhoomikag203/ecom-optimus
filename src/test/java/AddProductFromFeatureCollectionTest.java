import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Product;
import com.optimusEcom.pages.*;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.testng.annotations.Test;

public class AddProductFromFeatureCollectionTest extends BaseTest {

    @Test
    public void shouldAddProductFromFeatureCollection() {
        Product product=new ProductBuilder()
                .withSize(ProductSize.M)
                .withColor(ProductColor.White)
                .build();

        new LoginPage(driver).login()
                .selectProductFromFeatureCollection(product)
                .addToCart(product)
                .assertProductAddedToCart(product);
    }

}
