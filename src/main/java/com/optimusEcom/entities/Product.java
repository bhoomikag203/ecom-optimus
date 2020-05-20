package com.optimusEcom.entities;

import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;

import java.util.Objects;

public class Product {
    String name;
    int quantity;
    double price;
    ProductSize size;
    ProductColor color;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        return Objects.equals(name, product.name)
                && Objects.equals(quantity, product.quantity)
                && Objects.equals(size, product.size)
                && Objects.equals(price, product.price)
                && Objects.equals(color, product.color);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}