package com.optimusEcom.entities;

import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;

public class Product {
    String name;
    int quantity;
    double price;
    ProductSize size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductSize getSize() {
        return size;
    }

    public void setSize(ProductSize size) {
        this.size = size;
    }

    public ProductColor getColor() {
        return color;
    }

    public void setColor(ProductColor color) {
        this.color = color;
    }

    ProductColor color;

}