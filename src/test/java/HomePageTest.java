import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.CatalogPage;
import com.optimusEcom.pages.HomePage;
import com.optimusEcom.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    HomePage homePage;

    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setup() {
        initialize();
        homePage = new LoginPage().login(prop.getProperty("password"));
        this.homePage = new HomePage();
    }

    @Test
    public void shouldDisplayLogoLink(){
        Assert.assertTrue(homePage.validateLogo());
    }

    @Test
    public void shouldNavigateToCatalogPage(){
        homePage.clickCatalogLink();
        String title = driver.getTitle();
        Assert.assertEquals(title,"Products – ecom.optimus");
    }

    @Test
    public void shouldNavigateToCartPage(){
        homePage.navigateToCart();
        String title = driver.getTitle();
        Assert.assertEquals(title,"Your Shopping Cart – ecom.optimus");
    }

    @Test
    public void shouldSearchTheProduct(){
        homePage.searchProduct("Round Neck Shirt");
        String title = driver.getTitle();
        Assert.assertEquals(title,"Round Neck Shirt – ecom.optimus");
    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }
}
