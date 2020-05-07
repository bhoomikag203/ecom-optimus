package com.optimusEcom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    @FindBy(css = ".product-details__item:nth-child(2)")
    List<WebElement> sizeList;

    @FindBy(name = "updates[]")
    List<WebElement> productsQuantity;

    @FindBy(css = ".cart-subtotal__price")
    WebElement subTotalPrice;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        waitForElementsToBeVisible(productsName);
        return productsName.get(0).getText();
    }

    public double getSubTotalPrice(){
        waitForElementToBeVisible(subTotalPrice);
        String[] subTotalPriceArray = subTotalPrice.getText().split(" ");
        String price = subTotalPriceArray[1];
        double subPrice = Double.parseDouble(price.replaceAll(",", ""));
        return subPrice;
    }

    public double getProductPrice() {
        waitForElementsToBeVisible(productsPrice);
        String[] productPriceArray = productsPrice.get(0).getText().split(" ");
        System.out.println("array" +productPriceArray[2].replace("Qty", ""));
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

    public String getShirtSize() {
        String size = (sizeList.get(0).getText().split(" "))[1];
        System.out.println("Size == " + size);
        return size;
    }

    public double increaseQuantity(String productName, int count) {
        double totalPrice = 0;
        try {
            for (int i = 0; i < products.size() - 1; i++) {
                if (productName.equalsIgnoreCase(productsName.get(i).getText())) {
                    productsQuantity.get(i).clear();
                    productsQuantity.get(i).sendKeys(String.valueOf(count));
                    Thread.sleep(2000);
                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    totalPrice = count * getProductPrice();
                    DecimalFormat df = new DecimalFormat("####0.00");
                    totalPrice = Double.valueOf(df.format(totalPrice));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return totalPrice;
    }

    public List<ProductSize> getSizeList() {
        ArrayList<ProductSize> sizes = new ArrayList<>();
        for (int i = 0; i < sizeList.size(); i++) {
            String size = (sizeList.get(i).getText().split(" "))[1];
            ProductSize s = ProductSize.valueOf(size);
            sizes.add(s);
        }
        Collections.sort(sizes);
        return sizes;
    }
}
