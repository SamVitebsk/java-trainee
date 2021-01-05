package com.andersen.internetShop;

import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.dao.ProductCategory;
import com.andersen.internetShop.dao.ProductRepository;
import com.andersen.internetShop.dao.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    private Warehouse warehouse = new Warehouse(new ProductRepository());
    private Product product = new Product("test product", BigDecimal.TEN, ProductCategory.NOT_FOOD);

    @BeforeEach
    void setUp() {
    }

    @Test
    void addProduct() {
        warehouse.addProduct(product, 3);

        assertEquals(1, (int) warehouse.countProducts());
        assertEquals(3, (int) warehouse.countItems());
    }

    @Test
    void countProducts_onEmptyWarehouse() {
        assertEquals(0, (int) warehouse.countProducts());
    }

    @Test
    void countProducts_onNotEmptyWarehouse() {
        warehouse.addProduct(product);
        warehouse.addProduct(product);
        warehouse.addProduct(product);

        assertEquals(1, (int) warehouse.countProducts());
    }

    @Test
    void countItems_onEmptyWarehouse() {
        assertEquals(0, (int) warehouse.countItems());
    }

    @Test
    void countItems_onNotEmptyWarehouse() {
        warehouse.addProduct(product);
        warehouse.addProduct(product);
        warehouse.addProduct(product);
        warehouse.addProduct(product, 3);

        assertEquals(6, (int) warehouse.countItems());
    }

    @Test
    void isEmpty_onNotEmptyWarehouse() {
        warehouse.addProduct(product);

        assertFalse(warehouse.isEmpty());
    }

    @Test
    void isEmpty_onEmptyWarehouse() {
        assertTrue(warehouse.isEmpty());
    }

    @Test
    void getById_enoughProducts() {
        warehouse.addProduct(product, 10);

        Product prod = warehouse.getById(product.getId(), 8);

        assertEquals(2, (int) warehouse.countItems());
        assertTrue(prod.equals(product));
    }

    @Test
    void getById_notEnoughProducts() {
        warehouse.addProduct(product, 10);

        assertThrows(
                RuntimeException.class,
                () -> warehouse.getById(product.getId(), 18)
        );
    }

    @Test
    void getById_withoutCountAndEnoughProducts() {
        warehouse.addProduct(product, 10);

        Product prod = warehouse.getById(product.getId());

        assertTrue(product.equals(prod));
        assertEquals(9, (int) warehouse.countItems());
    }

    @Test
    void getById_withoutCountAndNotEnoughProducts() {
        assertThrows(
                RuntimeException.class,
                () -> warehouse.getById(product.getId())
        );
    }

    @Test
    void getById_badId() {
        assertThrows(
                RuntimeException.class,
                () -> warehouse.getById(-123)
        );
    }
}