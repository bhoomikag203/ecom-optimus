package com.optimusEcom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        PageFactory.initElements(driver, this);
    }

    public CartPage addToCart(String size, String color) {
        Select selectSize = new Select(sizeOption);
        selectSize.selectByValue(size);
        Select selectColor = new Select(colorOption);
        selectColor.selectByValue(color);
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(viewCartLink));
        viewCartLink.click();
        return new CartPage(driver);
    }

}
