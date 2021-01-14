package com.andersen.internetShop.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ProductCount {
    private Product product;
    private Integer count;
}
