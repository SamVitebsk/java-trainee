package com.andersen.internetShop;

import com.andersen.internetShop.currency.CurrencyCode;
import com.andersen.internetShop.currency.CurrencyFactory;
import com.andersen.internetShop.dao.Bucket;
import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.dao.ProductCategory;
import com.andersen.internetShop.dao.User;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BucketTest {
    Bucket bucket;
    Product product = new Product("test", BigDecimal.TEN, ProductCategory.NOT_FOOD);
    User user = new User(UUID.randomUUID(), "test", "test");
    @BeforeEach
    void setUp() {
        bucket = new Bucket(user.getId());
    }

    @Test
    void addProduct_productIsNull() {
        assertThrows(
                ProductNotFoundException.class,
                () -> bucket.addProduct(null)
        );
    }

    @Test
    void addProduct() {
        bucket.addProduct(product);
        Product newProduct = new Product("product", BigDecimal.TEN, ProductCategory.NOT_FOOD);
        bucket.addProduct(newProduct);

        assertEquals(2, (int) bucket.countProducts());
        assertTrue(bucket.getAll().keySet().contains(newProduct));
    }

    @Test
    void removeProduct_currentCountGreatThan() {
        bucket.addProduct(product, 3);

        bucket.removeProduct(product);

//        assertTrue(bucket.getAll().contains(product));
        assertEquals(2, (int) bucket.countItems());
        assertEquals(1, (int) bucket.countProducts());
    }

    @Test
    void removeProduct_countLessThen() {
        bucket.addProduct(product);

        bucket.removeProduct(product);

        assertFalse(bucket.getAll().keySet().contains(product));
        assertEquals(0, (int) bucket.countProducts());
    }

    @Test
    void clear() {
        bucket.addProduct(product);

        bucket.clear();

        assertEquals(0, (int) bucket.countProducts());
    }

    @Test
    void getAll() {
        bucket.addProduct(product);
        Product newProduct = new Product("product", BigDecimal.TEN, ProductCategory.NOT_FOOD);
        bucket.addProduct(newProduct);

        assertEquals(2, (int) bucket.countProducts());
    }

    @Test
    void countProducts() {
        bucket.addProduct(product,2);

        assertEquals(1, (int) bucket.countProducts());
    }

    @Test
    void countItems() {
        bucket.addProduct(product,2);

        assertEquals(2, (int) bucket.countItems());
    }

    @Test
    void calculateTotal_withBYN() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        BigDecimal total = bucket.calculateTotal(CurrencyFactory.getCurrency(CurrencyCode.BYN));

        assertEquals(20, total.doubleValue());
    }

    @Test
    void calculateTotal_withUAH() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        BigDecimal total = bucket.calculateTotal(CurrencyFactory.getCurrency(CurrencyCode.UAH));

        assertEquals(4400, total.doubleValue());
    }

    @Test
    void calculateTotal_withUSD() {
        bucket.addProduct(product);
        bucket.addProduct(product);

        BigDecimal total = bucket.calculateTotal(CurrencyFactory.getCurrency(CurrencyCode.USD));

        assertEquals(240, total.doubleValue());
    }
}