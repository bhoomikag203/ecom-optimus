package com.optimusEcom.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Product> products = new ArrayList<>();
    double totalPrice;

    public void addProductToCart(Product product) {
        products.add(product);
        computeTotalPrice();
    }

    public void removeProductFromCart(Product product) {
        products.remove(product);
        computeTotalPrice();
    }

    public Product getProduct(Product product) {
        if (products.contains(product))
            return product;
        return null;
    }

    public double computeTotalPrice() {
        totalPrice = 0.0;
        for (Product product : products)
            totalPrice += product.getQuantity() * product.getPrice();
        return totalPrice;
    }

}