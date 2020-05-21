package com.optimusEcom.builders;

import com.optimusEcom.entities.Product;
import com.optimusEcom.productConstants.ProductColor;
import com.optimusEcom.productConstants.ProductSize;

public class ProductBuilder {
    private Product product;

    public ProductBuilder() {
        product = new Product();
        product.setQuantity(1);
    }

    public ProductBuilder withName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder withPrice(double price) {
        product.setPrice(price);
        return this;
    }

    public ProductBuilder withQuantity(int quantity) {
        product.setQuantity(quantity);
        return this;
    }

    public ProductBuilder withSize(ProductSize size) {
        product.setSize(size);
        return this;
    }

    public ProductBuilder withColor(ProductColor color) {
        product.setColor(color);
        return this;
    }

    public Product build() {
        return this.product;
    }
}