package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    @FindBy(xpath = "//site-nav__label[contains(text(), 'Home')]")
    WebElement homeLink;

    @FindBy(xpath = "//a[@href='/collections/all']")
    WebElement catalogLink;

    @FindBy(className = "site-header__search-toggle")
    WebElement searchIcon;

    @FindBy(xpath = "//a[@href='/cart']")
    WebElement cartIcon;

    @FindBy(xpath = "//a[contains(text(), 'ecom.optimus')]")
    WebElement logoLink;

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

}
