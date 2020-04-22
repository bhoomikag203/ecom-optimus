package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends TestBase {
    @FindBy(className = "cart__product-title")
    List<WebElement> productsName;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public String getProductName(){
        return productsName.get(0).getText();
    }

}
