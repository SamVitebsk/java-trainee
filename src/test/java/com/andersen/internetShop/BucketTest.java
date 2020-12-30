package com.andersen.internetShop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BucketTest {
    Bucket bucket;
    Product product = new Product("test", BigDecimal.TEN, ProductCategory.NOT_FOOD);;
    @BeforeEach
    void setUp() {
        bucket = new Bucket();
    }

    @Test
    void addProduct_productIsNull() {
        bucket.addProduct(null);

        assertEquals(0, (int) bucket.size());
    }

    @Test
    void addProduct() {
        bucket.addProduct(product);
        Product newProduct = new Product("product", BigDecimal.TEN, ProductCategory.NOT_FOOD);
        bucket.addProduct(newProduct);

        assertEquals(2, (int) bucket.size());
        assertTrue(bucket.getAll().contains(newProduct));
    }

    @Test
    void removeProduct_currentCountGreatThan() {
        bucket.addProduct(product);
        bucket.addProduct(product);
        bucket.addProduct(product);

        bucket.removeProduct(product);

        assertTrue(bucket.getAll().contains(product));
        assertEquals(2, (int) bucket.size());
    }

    @Test
    void removeProduct_countLessThen() {
        bucket.addProduct(product);

        bucket.removeProduct(product);

        assertFalse(bucket.getAll().contains(product));
        assertEquals(0, (int) bucket.size());
    }

    @Test
    void clear() {
        bucket.addProduct(product);

        bucket.clear();

        assertEquals(0, (int) bucket.size());
    }

    @Test
    void getAll() {
        bucket.addProduct(product);
        Product newProduct = new Product("product", BigDecimal.TEN, ProductCategory.NOT_FOOD);
        bucket.addProduct(newProduct);

        List<Product> result = bucket.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void size() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        assertEquals(2, (int) bucket.size());
    }
}