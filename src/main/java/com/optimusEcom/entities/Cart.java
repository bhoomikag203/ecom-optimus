package com.optimusEcom.entities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    List<Product> products = new ArrayList<>();
    double totalPrice;

    public void addProductToCart(Product product) {
        products.add(product);
    }

    public void removeProductFromCart(Product product) {
        products.remove(product);
    }

    public Product getProduct(Product product) {
        for (Product p :
                products) {
            if (p.equals(product)) {
                return p;
            }
        }
        return null;
    }

    public boolean searchProductInCart(Product product) {
        for (Product p : products) {
            if (Objects.equals(p, product)) {
                return true;
            }
        }
        return false;
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