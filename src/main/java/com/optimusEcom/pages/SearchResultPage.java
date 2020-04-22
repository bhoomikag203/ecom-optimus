package com.optimusEcom.pages;

import com.optimusEcom.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends TestBase{
    @FindBy(xpath = "//li[1]//div[1]//a[1]")
    WebElement firstProduct;

    public ProductPage clickProduct(){
        firstProduct.click();
        return new ProductPage();
    }

}
