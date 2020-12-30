package com.andersen.internetShop;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product implements Serializable {
    private static Integer CURRENT_ID = 0;
    private Integer id;
    private String name;
    private BigDecimal price;
    private ProductCategory category;

    Product(String name, BigDecimal price, ProductCategory category) {
        this.id = ++CURRENT_ID;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
