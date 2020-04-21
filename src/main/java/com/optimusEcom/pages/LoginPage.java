package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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
    private WebDriverWait webDriverWait;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public String validateTitle(){
        return driver.getTitle();
    }

    public void notifyMe(String email){
        emailID.sendKeys(email);
        notifyMeButton.click();
    }

    public HomePage login(String pwd){
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        webDriverWait = new WebDriverWait(driver, 100);
        enterUsingPasswordLink.click();
        password.sendKeys(pwd);
        enterButton.click();
//        webDriverWait = new WebDriverWait(driver, 100);
        return new HomePage();
    }
}
