package com.optimusEcom.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    @FindBy(xpath = "//header//button[1]//*[local-name()='svg']")
    WebElement searchIcon;

    @FindBy(xpath = "//header//div[2]//a")
    WebElement cartIcon;

    @FindBy(name = "q")
    WebElement searchBox;

    @FindBy(css = "#search-result-0")
    WebElement selectProduct;

    @FindBy(css = "div.shopify-section.index-section:nth-child(3)")
    WebElement featureCollection;

    @FindBy(css = ".full-width-link")
    List<WebElement> featureCollectionProducts;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage navigateToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public ProductPage searchProduct(String productName) {
        click(searchIcon);
        sendKeys(searchBox, productName);
        click(selectProduct);
        return new ProductPage(driver);
    }

    public ProductPage addProductFromFeatureCollection() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", featureCollection);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        featureCollectionProducts.get(0).click();
        return new ProductPage(driver);
    }
}
