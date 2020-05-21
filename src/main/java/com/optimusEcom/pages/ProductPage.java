package com.optimusEcom.pages;

import com.optimusEcom.builders.ProductBuilder;
import com.optimusEcom.entities.Cart;
import com.optimusEcom.entities.Product;
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
    @FindBy(css = ".product-single__title")
    WebElement productName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public CartPage addToCart(Product product, Cart cart) {

        selectProductSize(product);
        selectProductColor(product);

        product = buildProduct(product);
        cart.addProductToCart(product);

        click(addToCartButton);
        viewCart();

        return this.getInstance(CartPage.class);
    }

    public void viewCart() {

        waitForElementToBeClickable(viewCartLink);
        click(viewCartLink);
    }

   /* public CartPage selectProductWithMultipleSizes(Product product, List<ProductSize> sizes) {
        for (ProductSize size : sizes) {
            waitForElementToBeVisible(sizeOption);
            Select selectSize = new Select(sizeOption);

            selectSize.selectByValue(String.valueOf(size));

            cart.addProductToCart(buildProduct());
            click(addToCartButton);

            waitForElementToBeVisible(continueShoppingButton);
            click(continueShoppingButton);
        }*/

      /*  waitForElementToBeVisible(cartIcon);
        click(cartIcon);
        return this.getInstance(CartPage.class);
    }*/

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
