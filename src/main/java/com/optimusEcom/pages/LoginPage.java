package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
        enterUsingPasswordLink.click();
        password.sendKeys(pwd);
        enterButton.click();
        return new HomePage();
    }
}
