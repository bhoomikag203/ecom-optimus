package com.optimusEcom.pages;

import com.optimusEcom.properties.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//a[@href='#LoginModal']")
    WebElement enterUsingPasswordLink;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(), 'Enter')]")
    WebElement enterButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage login() {
        wait.until(ExpectedConditions.elementToBeClickable(enterUsingPasswordLink));
        enterUsingPasswordLink.click();
        password.sendKeys(Properties.password);
        enterButton.click();
        return new HomePage(driver);
    }
}
