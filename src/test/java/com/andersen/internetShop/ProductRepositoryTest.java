package com.andersen.internetShop;

import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.dao.ProductCategory;
import com.andersen.internetShop.dao.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();

    @Test
    void getAll() {
        List<Product> products = repository.getAll();

        assertEquals(9, products.size());
    }

    @Test
    void create() {
        repository.create("milk", BigDecimal.valueOf(123), ProductCategory.MILK);
    }

    @Test
    void getByIdFromDb() {
        Product product = repository.getByIdFromDb(11);
        log.info("{}", product);
    }

    @Test
    void getAllFromDb() {
        List<Product> products = repository.getAllFromDb();

        products.forEach(System.out::println);

        assertTrue(products.size() != 0);
    }
}