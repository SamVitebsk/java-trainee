package com.andersen.internetShop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();

    @Test
    void getAll() {
        List<Product> products = repository.getAll();

        assertEquals(6, products.size());
    }
}