package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestBase {

    @FindBy(id = "PasswordNewsletterForm-email")
    WebElement emailID;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement notifyMeButton;

    @FindBy(xpath = "//a[@href='#LoginModal']")
    WebElement enterUsingPasswordLink;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(xpath = "//button[contains(text(), 'Enter')]")
    WebElement enterButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String validateTitle() {
        return driver.getTitle();
    }

    public void notifyMe(String email) {
        emailID.sendKeys(email);
        notifyMeButton.click();
    }

    public HomePage login(String pwd) {
        wait.until(ExpectedConditions.elementToBeClickable(enterUsingPasswordLink));
        enterUsingPasswordLink.click();
        password.sendKeys(pwd);
        enterButton.click();
        return new HomePage(driver);
    }
}
