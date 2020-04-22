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
        homePage = new HomePage();
    }

    @Test
    public void shouldDisplayLogoLink(){
        Assert.assertTrue(new LoginPage()
                .login(prop.getProperty("password"))
                .validateLogo());
    }

    @Test
    public void shouldNavigateToCatalogPage(){
         new LoginPage()
                .login(prop.getProperty("password"))
                .clickCatalogLink();
        String title = driver.getTitle();
        Assert.assertEquals(title,"Products – ecom.optimus");
    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }
}
