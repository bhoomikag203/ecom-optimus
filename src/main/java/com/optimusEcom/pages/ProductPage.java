package com.optimusEcom.pages;

import com.optimusEcom.entities.Cart;
import com.optimusEcom.entities.Product;
import com.optimusEcom.productConstants.ProductSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    @FindBy(css = ".site-header__cart")
    WebElement cartIcon;

    @FindBy(css = ".price-item")
    WebElement productPrice;

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

        buildProduct(product);
        click(addToCartButton);
        click(viewCartLink);
        cart.addProductToCart(product);
        return this.getInstance(CartPage.class);
    }

    public CartPage selectProductWithMultipleSizes(Product product, List<ProductSize> sizes) {
        for (ProductSize size : sizes) {
            waitForElementToBeVisible(sizeOption);
            Select selectSize = new Select(sizeOption);

            selectSize.selectByValue(String.valueOf(size));

            buildProduct(product);
            click(addToCartButton);
            cart.addProductToCart(product);

            waitForElementToBeVisible(continueShoppingButton);
            click(continueShoppingButton);
        }

        waitForElementToBeVisible(cartIcon);
        click(cartIcon);
        return this.getInstance(CartPage.class);
    }

    public void buildProduct(Product product) {
        product.setPrice(getProductPrice());
    }

    private double getProductPrice() {
        waitForElementToBeVisible(productPrice);
        String[] productPriceArray = productPrice.getText().split(" ");
        String price = productPriceArray[1];
        double productPrice = Double.parseDouble(price.replaceAll(",", ""));
        return productPrice;
    }

}
