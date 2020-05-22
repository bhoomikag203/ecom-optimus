package com.optimusEcom.pages;

import com.optimusEcom.entities.Cart;
import com.optimusEcom.entities.Product;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(name = "add")
    WebElement addToCartButton;
    @FindBy(css = ".cart-popup__cta-link")
    WebElement viewCartLink;
    @FindBy(id = "SingleOptionSelector-0")
    WebElement colorOption;
    @FindBy(id = "SingleOptionSelector-1")
    WebElement sizeOption;
    @FindBy(css = ".price-item")
    WebElement productPrice;
    @FindBy(css = ".product-single__title")
    WebElement productName;
    @FindBy(css = ".site-header__logo-link")
    WebElement logoLink;

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public ProductPage addToCart(Product product, Cart cart) {
        selectProductSize(product);
        selectProductColor(product);

        product = buildProduct(product);
        cart.addProductToCart(product);

        click(addToCartButton);

        return this;
    }

    public ProductPage addMultipleProducts(List<Product> products, Cart cart) {
        for (Product product : products) {
            navigateToHomePage()
                    .searchProduct(product)
                    .addToCart(product, cart);
        }
        return this;

    }

    public HomePage navigateToHomePage() {
        click(logoLink);
        return getInstance(HomePage.class);

    }

    public CartPage viewCart() {
        waitForElementToBeClickable(viewCartLink);
        click(viewCartLink);
        return this.getInstance(CartPage.class);

    }

    private Product buildProduct(Product product) {
        product.setPrice(getProductPrice());
        product.setColor(ProductColor.valueOf(colorOption.getAttribute("value")));
        product.setSize(ProductSize.valueOf(sizeOption.getAttribute("value")));
        product.setName(productName.getText());
        return product;

    }

    private double getProductPrice() {
        waitForElementToBeVisible(productPrice);
        String[] productPriceArray = productPrice.getText().split(" ");
        String price = productPriceArray[1];
        double productPrice = Double.parseDouble(price.replaceAll(",", ""));
        return productPrice;

    }

    private ProductPage selectProductSize(Product product) {
        waitForElementToBeVisible(sizeOption);

        Select selectSize = new Select(sizeOption);
        selectSize.selectByValue(String.valueOf(product.getSize()));
        return this;

    }

    private ProductPage selectProductColor(Product product) {
        waitForElementToBeVisible(colorOption);

        Select selectColor = new Select(colorOption);
        selectColor.selectByValue(String.valueOf(product.getColor()));
        return this;

    }

}
