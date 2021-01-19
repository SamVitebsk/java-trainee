package com.andersen.internetShop.dao;

import com.andersen.internetShop.utils.ExpiryDate;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class LimitedShelfLifeProduct extends Product {
    @ExpiryDate(shelfLifeInDays = 5)
    private LocalDate expiryDate;

    LimitedShelfLifeProduct(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return super.toString() + " expiryDate=" + expiryDate;
    }
}
