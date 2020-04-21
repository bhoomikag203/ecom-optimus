import com.optimusEcom.base.TestBase;
import com.optimusEcom.pages.HomePage;
import com.optimusEcom.pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    private WebDriverWait webDriverWait;

    public LoginPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() {
        initialize();
        loginPage = new LoginPage();
    }

    @Test
    public void shouldValidateTitle() {
        String title = loginPage.validateTitle();
        Assert.assertEquals(title, "ecom.optimus – Opening Soon");
    }

    @Test
    public void shouldLogin() {
//        loginPage.notifyMe(prop.getProperty("email"));
//        webDriverWait = new WebDriverWait(driver, 80);
        HomePage homePage = loginPage.login(prop.getProperty("password"));
    }

    @AfterMethod
    public void teardown() {
        driver.close();
        driver.quit();
    }

}