package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;
import java.util.List;

public class CartPage extends TestBase {

    @FindBy(className = "cart__row")
    List<WebElement> products;

    @FindBy(css = ".cart__price.text-right")
    List<WebElement> productsPrice;

    @FindBy(className = "cart__product-title")
    List<WebElement> productsName;

    @FindBy(css = ".cart__final-price")
    List<WebElement> totalProductPrice;

    @FindBy(css = ".cart-subtotal__price")
    WebElement subTotalPrice;

    @FindBy(name = "updates[]")
    List<WebElement> productsQuantity;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return productsName.get(0).getText();
    }

    public double getProductPrice() {
        String[] productPriceArray = productsPrice.get(0).getText().split(" ");
        String price = productPriceArray[2];
        double productPrice = Double.parseDouble(price.replaceAll(",", ""));
        return productPrice;
    }


    public double getTotalPrice() {
        String[] productPriceArray = totalProductPrice.get(0).getText().split(" ");
        String price = productPriceArray[1];
        double totalPrice = Double.parseDouble(price.replaceAll(",", ""));
        return totalPrice;
    }

    public double increaseQuantity(String productName, int count) throws InterruptedException {
        double totalPrice = 0;
        for (int i = 0; i < products.size() - 1; i++) {
            if (productName.equalsIgnoreCase(productsName.get(i).getText())) {
                productsQuantity.get(i).clear();
                productsQuantity.get(i).sendKeys(String.valueOf(count));
                Thread.sleep(2000);
                totalPrice = count * getProductPrice();
                DecimalFormat df = new DecimalFormat("####0.00");
                totalPrice = Double.valueOf(df.format(totalPrice));
            }
        }
        return totalPrice;
    }
}
