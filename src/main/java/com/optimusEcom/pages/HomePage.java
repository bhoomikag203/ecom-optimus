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

    @FindBy(xpath = "//header//button[1]//*[local-name()='svg']")
    WebElement searchIcon;

    @FindBy(xpath = "//header//div[2]//a")
    WebElement cartIcon;

    @FindBy(className = "site-header__logo-link")
    WebElement logoLink;

    @FindBy(name = "q")
    WebElement searchBox;

    @FindBy(className = "predictive-search-view-all__button")
    WebElement searchButton;

    @FindBy(css = "#search-result-0")
    WebElement selectProduct;

    private String productName;

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
        this.productName=productName;
        searchIcon.click();
        searchBox.sendKeys(productName);
        selectProduct.click();
        return new ProductPage();
    }

   /*public SearchResultPage searchProduct(String productName){
       SearchResultPage searchResultPage = new SearchResultPage();
       this.productName = productName;
       searchIcon.click();
       searchBox.sendKeys(productName);
       searchButton.click();
       return searchResultPage;
   }*/

}
