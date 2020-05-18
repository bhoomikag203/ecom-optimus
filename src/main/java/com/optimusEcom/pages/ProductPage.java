package com.optimusEcom.pages;

import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

    @FindBy(name = "add")
    WebElement addToCartButton;

    @FindBy(xpath = "//a[@class='cart-popup__cta-link btn btn--secondary-accent']")
    WebElement viewCartLink;

    @FindBy(className = "product-single__title")
    WebElement productName;

    @FindBy(id = "SingleOptionSelector-0")
    WebElement colorOption;

    @FindBy(id = "SingleOptionSelector-1")
    WebElement sizeOption;

    public String getProductName() {
        return productName.getText();
    }

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPage addToCart(ProductSize size, ProductColor color) {

        waitForElementToBeClickable(sizeOption);
        Select selectSize = new Select(sizeOption);
        selectSize.selectByValue(String.valueOf(size));

        waitForElementToBeClickable(colorOption);
        Select selectColor = new Select(colorOption);
        selectColor.selectByValue(String.valueOf(color));

        click(addToCartButton);
        click(viewCartLink);
        return new CartPage(driver);
    }

}
