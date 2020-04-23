import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.*;
import org.openqa.selenium.WebElement;
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
        this.homePage = new HomePage();
    }

    @Test
    public void shouldDisplayLogoLink(){
        homePage = new LoginPage().login(prop.getProperty("password"));
        Assert.assertTrue(homePage.validateLogo());
    }

    @Test
    public void shouldNavigateToCatalogPage(){
        homePage = new LoginPage().login(prop.getProperty("password"));
        homePage.clickCatalogLink();
        String title = driver.getTitle();
        Assert.assertEquals(title,"Products – ecom.optimus");
    }

    @Test
    public void shouldNavigateToCartPage(){
        homePage = new LoginPage().login(prop.getProperty("password"));
        homePage.navigateToCart();
        String title = driver.getTitle();
        Assert.assertEquals(title,"Your Shopping Cart – ecom.optimus");
    }

    @Test
    public void shouldSearchTheProduct(){
        ProductPage productPage = new LoginPage().login(prop.getProperty("password")).searchProduct("Round Neck Shirt");
        String title = driver.getTitle();
        Assert.assertEquals(title,productPage.getProductName()+" – ecom.optimus");
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }
}
