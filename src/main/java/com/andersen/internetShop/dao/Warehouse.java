package com.andersen.internetShop.dao;

import com.andersen.internetShop.dao.LimitedShelfLifeProduct;
import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.dao.ProductCategory;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.exceptions.SoManyProductsException;
import com.andersen.internetShop.utils.ExpiryDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Warehouse {
    private final Map<Product, Integer> products;

    public Warehouse() {
        products = new LinkedHashMap<>();
        boot();
    }

    public void addProduct(Product product, Integer count) {
        count = checkCount(count);
        checkExpiryDate(product);

        Integer currentCount = products.get(product);
        if (Objects.isNull(currentCount)) {
            products.put(product, count);
        } else {
            products.put(product, count + currentCount);
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Product getById(Integer id, Integer count) {
        count = checkCount(count);
        Product product = products.keySet().stream()
                .filter(prod -> prod.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (Objects.nonNull(product)) {
            Integer currentCount = products.get(product);

            if (currentCount >= count) {
                products.put(product, currentCount - count);
                return product;
            } else {
                throw new SoManyProductsException();
            }
        }

        throw new ProductNotFoundException();
    }

    public Product getById(Integer id) {
        return getById(id, 1);
    }

    public Integer countItems() {
        return products.values().stream().reduce(0, Integer::sum);
    }

    public Integer countProducts() {
        return products.size();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    private void checkExpiryDate(Product product) {
        if (product instanceof LimitedShelfLifeProduct) {
            try {
                Field expiryDateField = product.getClass().getDeclaredField("expiryDate");
                expiryDateField.setAccessible(true);
                ExpiryDate annotation = expiryDateField.getDeclaredAnnotation(ExpiryDate.class);
                int lifeDays = annotation.shelfLifeInDays();
                expiryDateField.set(product, LocalDate.now().plusDays(lifeDays));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("ExpiryDate: something wrong");
            }
        }
    }

    private Integer checkCount(Integer count) {
        return count < 0 ? 0 : count;
    }

    public void boot() {
        addProduct(new Product("table", BigDecimal.valueOf(250), ProductCategory.NOT_FOOD));
        addProduct(new Product("notebook", BigDecimal.valueOf(1200), ProductCategory.NOT_FOOD), 3);
        addProduct(new Product("water", BigDecimal.valueOf(5), ProductCategory.FOOD));
        addProduct(new Product("apple", BigDecimal.valueOf(10), ProductCategory.FOOD), 7);
        addProduct(new Product("bread", BigDecimal.valueOf(4), ProductCategory.FOOD));
        addProduct(new Product("meat", BigDecimal.valueOf(15), ProductCategory.FOOD));
        addProduct(new LimitedShelfLifeProduct("cheese", BigDecimal.valueOf(40), ProductCategory.MILK), 10);
        addProduct(new LimitedShelfLifeProduct("oil", BigDecimal.valueOf(30), ProductCategory.MILK), 50);
        addProduct(new LimitedShelfLifeProduct("yogurt", BigDecimal.valueOf(20), ProductCategory.MILK));
    }
}
