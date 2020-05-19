package com.optimusEcom.pages;

import com.optimusEcom.entities.Product;
import com.optimusEcom.productConstants.ProductSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends BasePage {

    @FindBy(className = "cart__row")
    List<WebElement> products;
    @FindBy(css = ".cart__price.text-right")
    List<WebElement> productsPrice;
    @FindBy(className = "cart__product-title")
    List<WebElement> productsName;
    @FindBy(xpath = "//li[contains(text(),'Size')]")
    List<WebElement> sizeList;
    @FindBy(xpath = "//li[contains(text(),'Color')]")
    List<WebElement> colorList;
    @FindBy(css = ".cart__qty [name='updates[]']")
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

    public double getProductPrice(int i) {
        waitForElementsToBeVisible(productsPrice);
        String[] productPriceArray = productsPrice.get(i).getText().split(" ");
        String price = productPriceArray[2].replace("Qty", "");
        double productPrice = Double.parseDouble(price.replaceAll(",", ""));
        return productPrice;
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

    public String getSize(int i) {
        return sizeList.get(i).getText().split(" ")[1];
    }

    public String getColor(int i) {
        return colorList.get(i).getText().split(" ")[1];
    }

    public CartPage removeProduct(Product product) {
        waitForElementsToBeVisible(productsName);

        for (int i = 0; i < productsName.size(); i++) {
            if (productsName.get(i).getText().equalsIgnoreCase(product.getName())
                    && getSize(i).equalsIgnoreCase(String.valueOf(product.getSize()))
                    && getColor(i).equalsIgnoreCase(String.valueOf(product.getColor())))

                click(removeProductLink);
        }

        return this;
    }

    public void assertMultipleSizesAddedToCart(ProductSize size1, ProductSize size2) {
        for (WebElement sizeText : sizeList) {
            String size = (sizeText.getText().split(" "))[1];

            if (size.equals(size1.toString()) || size.equals(size2.toString())) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

    public int getProductQuantity(int i) {
        String text = productsQuantity.get(i).getAttribute("value");
        return Integer.parseInt(text);
    }

    public void assertSubTotal() {
        double totalPrice = 0;

        try {
            Thread.sleep(2000);
            for (int i = 0; i < productsName.size(); i++)
                totalPrice += getProductQuantity(i) * getProductPrice(i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(totalPrice, getSubTotalPrice());
    }

    public void assertProductAddedToCart(Product product) {
        Assert.assertEquals(product.getName(), getProductName());
    }

    public void assertProductRemovedFromCart() {
        waitForElementToBeVisible(cartEmptyMessage);
        Assert.assertEquals(cartEmptyMessage.getText(), "Your cart is currently empty.");
    }
}