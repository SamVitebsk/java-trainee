package com.andersen.internetShop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Product {
    private static Long CURRENT_ID = 1L;
    private Long id;
    private String name;
    private BigDecimal price;
    private ProductCategory category;

    Product(String name, BigDecimal price, ProductCategory category) {
        this.id = CURRENT_ID++;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
