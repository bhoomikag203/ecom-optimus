package com.optimusEcom.pages;

import com.optimusEcom.entities.Product;
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

    @FindBy(id = "SingleOptionSelector-0")
    WebElement colorOption;

    @FindBy(id = "SingleOptionSelector-1")
    WebElement sizeOption;

    @FindBy(css = ".cart-popup__dismiss")
    WebElement continueShoppingButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public CartPage addToCart(Product product) {
        waitForElementToBeVisible(sizeOption);
        Select selectSize = new Select(sizeOption);
        selectSize.selectByValue(String.valueOf(product.getSize()));

        waitForElementToBeVisible(colorOption);
        Select selectColor = new Select(colorOption);
        selectColor.selectByValue(String.valueOf(product.getColor()));

        click(addToCartButton);
        click(viewCartLink);
        return new CartPage(driver);
    }

    public CartPage selectProductWithMultipleSizes(ProductSize size1, ProductSize size2) {

        try {
            waitForElementToBeVisible(sizeOption);
            Select selectSize = new Select(sizeOption);

            selectSize.selectByValue(String.valueOf(size1));
            click(addToCartButton);

            waitForElementToBeVisible(continueShoppingButton);
            click(continueShoppingButton);

            selectSize.selectByValue(String.valueOf(size2));
            click(addToCartButton);

            waitForElementToBeVisible(viewCartLink);
            Thread.sleep(500);
            click(viewCartLink);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new CartPage(driver);
    }
}
