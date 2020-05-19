package com.optimusEcom.pages;

import com.optimusEcom.entities.Product;
import com.optimusEcom.productConstants.ProductSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends BasePage {

    @FindBy(className = "cart__row")
    List<WebElement> products;

    @FindBy(css = ".cart__price.text-right")
    List<WebElement> productsPrice;

    @FindBy(className = "cart__product-title")
    List<WebElement> productsName;

    @FindBy(css = ".cart__final-price")
    List<WebElement> totalProductsPrice;

    @FindBy(xpath = "//li[contains(text(),'Size')]")
    List<WebElement> sizeList;

    @FindBy(xpath = "//li[contains(text(),'Color')]")
    List<WebElement> colorList;

    @FindBy(name = "updates[]")
    List<WebElement> productsQuantity;

    @FindBy(css = ".cart-subtotal__price")
    WebElement subTotalPrice;

    @FindBy(linkText = "Remove")
    WebElement removeProductLink;

    @FindBy(css = ".cart--empty-message")
    WebElement cartEmptyMessage;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        waitForElementsToBeVisible(productsName);
        return productsName.get(0).getText();
    }

    public double getSubTotalPrice() {
        waitForElementToBeVisible(subTotalPrice);
        String[] subTotalPriceArray = subTotalPrice.getText().split(" ");
        String price = subTotalPriceArray[1];
        double subPrice = Double.parseDouble(price.replaceAll(",", ""));
        return subPrice;
    }

    public double getProductPrice() {
        waitForElementsToBeVisible(productsPrice);
        String[] productPriceArray = productsPrice.get(0).getText().split(" ");
        System.out.println("array" + productPriceArray[2].replace("Qty", ""));
        String price = productPriceArray[2].replace("Qty", "");
        double productPrice = Double.parseDouble(price.replaceAll(",", ""));
        return productPrice;
    }


    public double getTotalPrice() {
        waitForElementsToBeVisible(totalProductsPrice);
        String[] productPriceArray = totalProductsPrice.get(0).getText().split(" ");
        String price = productPriceArray[1];
        double totalPrice = Double.parseDouble(price.replaceAll(",", ""));
        return totalPrice;
    }

    public CartPage increaseQuantity(Product product) {
        for (int i = 0; i < products.size() - 1; i++) {
            if (product.getName().equalsIgnoreCase(productsName.get(i).getText())) {
                productsQuantity.get(i).clear();
                productsQuantity.get(i).sendKeys(String.valueOf(product.getQuantity()));
            }
        }
        return this;
    }

    public CartPage removeProduct(Product product) {
        waitForElementsToBeVisible(productsName);
        for (int i = 0; i < productsName.size(); i++) {
            if (productsName.get(i).getText().equalsIgnoreCase(product.getName())
                    && sizeList.get(i).getText().split(" ")[1].equalsIgnoreCase(String.valueOf(product.getSize()))
                    && colorList.get(i).getText().split(" ")[1].equalsIgnoreCase(String.valueOf(product.getColor())))
                click(removeProductLink);
        }
        return this;
    }

    public void assertMultipleSizesAddedToCart(ProductSize size1, ProductSize size2) {
        for (WebElement sizeText : sizeList) {
            System.out.println("size text " + sizeText.getText());
            String size = (sizeText.getText().split(" "))[1];

            if (size.equals(size1.toString()) || size.equals(size2.toString())) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

    public void assertProductAddedToCart(Product product) {
        Assert.assertEquals(product.getName(), getProductName());
    }

    public void assertIncreaseProductQuantity(Product product) {
        double totalPrice = 0;
        try {
            Thread.sleep(2000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            totalPrice = product.getQuantity() * getProductPrice();

            DecimalFormat df = new DecimalFormat("####0.00");
            totalPrice = Double.valueOf(df.format(totalPrice));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(totalPrice, getSubTotalPrice());
    }

    public void assertProductRemovedFromCart() {
        waitForElementToBeVisible(cartEmptyMessage);
        Assert.assertEquals(cartEmptyMessage.getText(), "Your cart is currently empty.");
    }
}