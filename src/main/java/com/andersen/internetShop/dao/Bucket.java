package com.andersen.internetShop.dao;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.exceptions.NegativeNumberProductsException;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class Bucket implements Serializable {
    private final UUID userId;
    private final Map<Product, Integer> products;

    public Bucket(UUID userId) {
        this.userId = userId;
        this.products = new HashMap<>();
    }


    public void addProduct(Product product, Integer count) {
        checkInput(product, count);

        Integer currentCount = products.get(product);
        if (Objects.nonNull(currentCount)) {
            products.put(product, currentCount + count);
        } else {
            products.put(product, count);
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void removeProduct(Product product, Integer count) {
        checkInput(product, count);

        Integer currentCount = products.get(product);

        if (Objects.isNull(currentCount)) {
            throw new ProductNotFoundException();
        }

        currentCount -= count;
        if (currentCount <= 0) {
            products.remove(product);
        } else {
            products.put(product, currentCount);
        }
    }

    public void removeProduct(Product product) {
        removeProduct(product, 1);
    }

    public void clear() {
        products.clear();
    }

    public Map<Product, Integer> getAll() {
        return products;
    }

    public Integer countProducts() {
        return products.size();
    }

    public Integer countItems() {
        return products.values().stream().reduce(0, Integer::sum);
    }

    public boolean isEmpty() {
        return countProducts() == 0;
    }

    public BigDecimal calculateTotal(Currency currency) {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> pair : products.entrySet()) {
            total = total.add(pair.getKey().getPrice().multiply(BigDecimal.valueOf(pair.getValue())));
        }

        return total.multiply(BigDecimal.valueOf(currency.getMultiplicity()))
                .multiply(BigDecimal.valueOf(currency.getCourse()));
    }

    private void checkInput(Product product, Integer count) {
        if (Objects.isNull(product)) {
            throw new ProductNotFoundException();
        }

        if (count <= 0) {
            throw new NegativeNumberProductsException();
        }
    }
}
