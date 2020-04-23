package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends TestBase {
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

    @FindBy(css = "#search-result-0")
    WebElement selectProduct;

    @FindBy(css = "div.shopify-section.index-section:nth-child(3)")
    WebElement featureCollection;

    @FindBy(css = ".full-width-link")
    List<WebElement> featureCollectionProducts;

    @FindBy(id = "CartCount")
    WebElement cartCount;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public boolean validateLogo() {
        return logoLink.isDisplayed();
    }

    public CatalogPage clickCatalogLink() {
        catalogLink.click();
        return new CatalogPage();
    }

    public CartPage navigateToCart() {
        cartIcon.click();
        return new CartPage();
    }

    public String getCartCount(){
        return cartCount.getText();
    }
    //    selecting a product from predictive search box
    public ProductPage searchProduct(String productName) {
        searchIcon.click();
        searchBox.sendKeys(productName);
        selectProduct.click();
        return new ProductPage();
    }

    public ProductPage addProductFromFeatureCollection() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", featureCollection);
        Thread.sleep(2000);
        featureCollectionProducts.get(0).click();
        return new ProductPage();
    }
}
