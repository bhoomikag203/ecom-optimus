package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TestBase {
    @FindBy(xpath = "//site-nav__label[contains(text(), 'Home')]")
    WebElement homeLink;

    @FindBy(xpath = "//a[@href='/collections/all']")
    WebElement catalogLink;

    @FindBy(className = "site-header__search-toggle")
    WebElement searchIcon;

    @FindBy(xpath = "//a[@class='site-header__icon site-header__cart']")
    WebElement cartIcon;

    @FindBy(xpath = "//a[contains(text(), 'ecom.optimus')]")
    WebElement logoLink;

    private WebDriverWait webDriverWait;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public boolean validateLogo() {
        return logoLink.isDisplayed();
    }

    public CatalogPage clickCatalogLink(){
        catalogLink.click();
        return new CatalogPage();
    }

    public CartPage navigateToCart(){
        cartIcon.click();
        return new CartPage();
    }

}
