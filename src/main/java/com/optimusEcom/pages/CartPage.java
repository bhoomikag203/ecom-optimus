package com.optimusEcom.pages;

import com.optimusEcom.entities.Cart;
import com.optimusEcom.entities.Product;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartPage extends BasePage {

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

    List<Product> productList = new ArrayList<>();

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage increaseQuantity(Product product, int count) {
        Product p = getProductFromProductList(product);

        if (product.equals(p)) {
            int i = buildProductListFromCartPage().indexOf(p);

            productsQuantity.get(i).clear();
            productsQuantity.get(i).sendKeys(String.valueOf(count));

            product.setQuantity(count);
            p.setQuantity(count);
        }
        return this;

    }


    public CartPage removeProduct(Product product, Cart cart) {
        cart.removeProductFromCart(product);
        removeProductFromProductList(product);
        click(removeProductLink);
        return this;

    }

    public void assertProductAddedToCart(Product product, Cart cart) {
        Assert.assertEquals(getProductFromProductList(product), cart.getProduct(product));
    }

    public void assertProductRemovedFromCart() {
        waitForElementToBeVisible(cartEmptyMessage);
        Assert.assertEquals(cartEmptyMessage.getText(), "Your cart is currently empty.");
    }


    public void assertMultipleProductsAddedToCart(List<Product> products, Cart cart) {
        boolean isProductPresent = true;
        for (Product product : products) {
            if (cart.getProduct(product).equals(getProductFromProductList(product)) && isProductPresent)
                isProductPresent = true;
            else
                isProductPresent = false;
        }
        Assert.assertTrue(isProductPresent);
    }

    public void assertSubTotal(Cart cart) {
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double totalPrice = cart.computeTotalPrice();
        totalPrice = formatPrice(totalPrice);

        Assert.assertEquals(totalPrice, getSubTotalPrice());
    }


    private List<Product> buildProductListFromCartPage() {
        List list = new ArrayList();
        waitForElementsToBeVisible(productsName);
        for (int i = 0; i < productsName.size(); i++) {
            Product product = new Product();
            product.setName(productsName.get(i).getText());
            product.setQuantity(Integer.parseInt(String.valueOf(getProductQuantity(i))));
            product.setPrice(getProductPrice(i));
            product.setSize(ProductSize.valueOf(getSize(i)));
            product.setColor(ProductColor.valueOf(getColor(i)));
            list.add(product);
        }
        return list;
    }

    private Product getProductFromProductList(Product product) {
        productList = buildProductListFromCartPage();
        for (Product p : productList) {
            if (Objects.equals(p, product)) {
                return p;
            }
        }
        return null;
    }

    private void removeProductFromProductList(Product product) {
        getProductFromProductList(product);
        productList.remove(product);
    }

    private double getSubTotalPrice() {
        waitForElementToBeVisible(subTotalPrice);
        String[] subTotalPriceArray = subTotalPrice.getText().split(" ");
        String price = subTotalPriceArray[1];
        double subPrice = Double.parseDouble(price.replaceAll(",", ""));
        return subPrice;
    }

    private double getProductPrice(int i) {
        waitForElementsToBeVisible(productsPrice);
        String[] productPriceArray = productsPrice.get(i).getText().split(" ");
        String price = productPriceArray[2].replace("Qty", "");
        double productPrice = Double.parseDouble(price.replaceAll(",", ""));
        return productPrice;
    }

    private double formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("####.00");
        String formattedPrice = decimalFormat.format(price);
        return Double.parseDouble(formattedPrice);
    }

    private int getProductQuantity(int i) {
        String text = productsQuantity.get(i).getAttribute("value");
        return Integer.parseInt(text);
    }

    private String getSize(int i) {
        return sizeList.get(i).getText().split(" ")[1];
    }

    private String getColor(int i) {
        return colorList.get(i).getText().split(" ")[1];
    }

}