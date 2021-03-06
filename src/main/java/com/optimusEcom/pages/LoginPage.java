package com.optimusEcom.pages;

import com.optimusEcom.properties.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "[aria-haspopup='dialog']")
    WebElement enterUsingPasswordLink;
    @FindBy(id = "Password")
    WebElement password;
    @FindBy(css = ".btn--narrow")
    WebElement enterButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage login() {
        click(enterUsingPasswordLink);
        sendKeys(password, Properties.password);
        click(enterButton);
        return this.getInstance(HomePage.class);
    }
}
