package com.andersen.internetShop;

import java.math.BigDecimal;
import java.time.LocalDate;


public class LimitedShelfLifeProduct extends Product {
    @ExpiryDate(shelfLifeInDays = 5)
    private LocalDate expiryDate;

    LimitedShelfLifeProduct(String name, BigDecimal price, ProductCategory category) {
        super(name, price, category);
    }

    @Override
    public String toString() {
        return super.toString() + " expiryDate=" + expiryDate;
    }
}
