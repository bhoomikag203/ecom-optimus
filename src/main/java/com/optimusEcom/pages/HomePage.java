package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TestBase {
    @FindBy(xpath = "//site-nav__label[contains(text(), 'Home')]")
    WebElement homeLink;

    @FindBy(xpath = "//a[@href='/collections/all']")
    WebElement catalogLink;

    @FindBy(xpath = "//header//button[1]//*[local-name()='svg']")
    WebElement searchIcon;

    @FindBy(xpath = "//header//div[2]//a")
    WebElement cartIcon;

    @FindBy(xpath = "//a[contains(text(), 'ecom.optimus')]")
    WebElement logoLink;

    @FindBy(name = "q")
    WebElement searchBox;

    @FindBy(xpath = "//div[@class='predictive-search']//li[@id='search-result-0']")
    WebElement selectProduct;

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

    //selecting a product from predictive search box
    public ProductPage searchProduct(String productName){
        searchIcon.click();
        searchBox.sendKeys(productName);
        selectProduct.click();
        return new ProductPage();
    }
    
}
