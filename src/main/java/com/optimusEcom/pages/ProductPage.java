package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    public String getSize() {
        return size;
    }

    private String size;

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean addToCart() throws InterruptedException {
        addToCartButton.click();
        Thread.sleep(3000);
        boolean isDisplayed = cartPopupHeading.isDisplayed();
        cartPopupCloseButton.click();
        Thread.sleep(3000);
        return isDisplayed;
    }

    public void addToCart(String size, String color) throws InterruptedException {
        this.size = size;
        Thread.sleep(2000);
        Select selectSize = new Select(sizeOption);
        selectSize.selectByValue(size);
        Select selectColor = new Select(colorOption);
        selectColor.selectByValue(color);
        addToCartButton.click();
        Thread.sleep(2000);
        continueShoppingButton.click();
        Thread.sleep(2000);
    }

    public CartPage viewCart() throws InterruptedException {
        CartPage cartPage = new CartPage();
        Thread.sleep(2000);
        viewCartLink.click();
        Thread.sleep(2000);
        return cartPage;
    }

}
