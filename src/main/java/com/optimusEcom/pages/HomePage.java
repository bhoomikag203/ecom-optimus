package com.optimusEcom.pages;

import com.optimusEcom.entities.Product;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    @FindBy(css = ".site-header__search-toggle")
    WebElement searchIcon;
    @FindBy(css = ".site-header__cart")
    WebElement cartIcon;
    @FindBy(name = "q")
    WebElement searchBox;
    @FindBy(css = "#search-result-0")
    WebElement selectProduct;
    @FindBy(css = "div.shopify-section.index-section:nth-child(3)")
    WebElement featureCollection;
    @FindBy(css = ".grid-view-item__image-container")
    List<WebElement> productNameList;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CartPage navigateToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public ProductPage searchProduct(Product product) {
        click(searchIcon);
        sendKeys(searchBox, product.getName());
        click(selectProduct);
        return this.getInstance(ProductPage.class);
    }

    public ProductPage selectProductFromFeatureCollection(Product product) {
        waitForElementToBeVisible(featureCollection);
        click(productNameList.get(0));
        return this.getInstance(ProductPage.class);
    }
}
