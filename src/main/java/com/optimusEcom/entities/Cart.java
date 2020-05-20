package com.optimusEcom.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Product> products = new ArrayList<>();
    double totalPrice;

    public void addProductToCart(Product product) {
        products.add(product);
        System.out.println(product);
    }

    public void removeProductFromCart(Product product) {
        products.remove(product);
        System.out.println(product);
    }

    public double computeTotalPrice() {
        for (Product product : products)
            totalPrice += product.getQuantity() * product.getPrice();
        return totalPrice;
    }

    public void printProducts() {
        System.out.println("Cart Items");
        for (Product product : products) {
            System.out.println(product);
            System.out.println(product.getName());
            System.out.println(product.getQuantity());
            System.out.println(product.getSize());
            System.out.println(product.getColor());
            System.out.println(product.getPrice());
        }
    }
}