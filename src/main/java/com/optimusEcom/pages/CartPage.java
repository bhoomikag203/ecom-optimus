package com.optimusEcom.pages;

import com.optimusEcom.entities.Product;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    public CartPage increaseQuantity(Product product, int count) {
        for (int i = 0; i < products.size() - 1; i++) {
            if (product.getName().equalsIgnoreCase(productsName.get(i).getText())) {
                productsQuantity.get(i).clear();
                productsQuantity.get(i).sendKeys(String.valueOf(count));
                product.setQuantity(count);
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
                    && getColor(i).equalsIgnoreCase(String.valueOf(product.getColor()))) {

                click(removeProductLink);
                cart.removeProductFromCart(product);
            }

        }

        return this;
    }

    public void assertMultipleSizesAddedToCart(Product product, List<ProductSize> productSizes) {
        getProducts();
        printProducts();
        System.out.println("============================");
        cart.printProducts();
        List<String> sizeListOne = new ArrayList<>();
        List<String> sizesListTwo = new ArrayList<>();


        for (int i = 0; i < productSizes.size(); i++) {
            if (product.getName().equalsIgnoreCase(productsName.get(i).getText())) {
                sizeListOne.add(getSize(i));
                sizesListTwo.add(String.valueOf(productSizes.get(i)));
            }
        }
        Collections.sort(sizeListOne);
        Collections.sort(sizesListTwo);
        Assert.assertEquals(sizeListOne, sizesListTwo);
//        if(getProduct(product).equals(product))

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

        totalPrice = formatPrice(totalPrice);
        Assert.assertEquals(totalPrice, getSubTotalPrice());
    }

    private double formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("####.00");
        String formattedPrice = decimalFormat.format(price);
        return Double.parseDouble(formattedPrice);
    }

    public void assertProductAddedToCart(Product product) {
        getProduct(product);
        System.out.println("Product added");
        printProduct(product);
        Assert.assertEquals(product.getName(), getProductName());
    }

    public void assertProductRemovedFromCart() {
        waitForElementToBeVisible(cartEmptyMessage);
        Assert.assertEquals(cartEmptyMessage.getText(), "Your cart is currently empty.");
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productsName.size(); i++) {
            Product product = new Product();
            product.setName(productsName.get(i).getText());
            product.setQuantity(Integer.parseInt(String.valueOf(getProductQuantity(i))));
            product.setPrice(getProductPrice(i));
            product.setSize(ProductSize.valueOf(getSize(i)));
            product.setColor(ProductColor.valueOf(getColor(i)));
            products.add(product);
        }
        return products;
    }

    public Product getProduct(Product product) {
        for (Product p : getProducts()) {
            System.out.println("=========");
            System.out.println(" product from cart");

            printProduct(p);
            Assert.assertEquals(p, product);

            if (Objects.equals(p, product)) {
                Assert.assertEquals(p, product);
                System.out.println("TRUE!!!!!");
                return product;
            }
        }
        return product;
    }

    public void printProducts() {
        for (Product product :
                getProducts()) {
            printProduct(product);
        }
    }

    public void printProduct(Product product) {
        System.out.println(product);
        System.out.println(product.getName());
        System.out.println(product.getQuantity());
        System.out.println(product.getSize());
        System.out.println(product.getColor());
        System.out.println(product.getPrice());
    }

}