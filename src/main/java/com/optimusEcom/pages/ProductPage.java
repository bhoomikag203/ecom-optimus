package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends TestBase {
    @FindBy(name = "add")
    WebElement addToCartButton;

    @FindBy(id = "CartPopupHeading")
    WebElement cartPopupHeading;

    @FindBy(xpath = "//a[@class='cart-popup__cta-link btn btn--secondary-accent']")
    WebElement viewCartLink;

    @FindBy(className = "product-single__title")
    WebElement productName;

    @FindBy(className = "cart-popup__close")
    WebElement cartPopupCloseButton;

    @FindBy(id = "SingleOptionSelector-0")
    WebElement colorOption;

    @FindBy(id = "SingleOptionSelector-1")
    WebElement sizeOption;

    @FindBy(css = ".cart-popup__dismiss-button")
    WebElement continueShoppingButton;

    public String getProductName() {
        return productName.getText();
    }

    private String size;

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    public CartPage addToCart(String size, String color) {
        this.size = size;
        Select selectSize = new Select(sizeOption);
        selectSize.selectByValue(size);
        Select selectColor = new Select(colorOption);
        selectColor.selectByValue(color);
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(viewCartLink));
        viewCartLink.click();
        return new CartPage();
    }

    public CartPage addProductWithMultipleSizes(){
        addToCart("S", "White");

        return new CartPage();
    }
}
