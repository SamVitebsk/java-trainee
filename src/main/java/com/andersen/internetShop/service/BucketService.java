package com.andersen.internetShop.service;

import com.andersen.internetShop.dao.Bucket;
import com.andersen.internetShop.dao.Warehouse;
import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.exceptions.NegativeNumberProductsException;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.exceptions.SoManyProductsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class BucketService {
    private final Warehouse warehouse;
    private final Bucket bucket;

    public void showProductList() {
        warehouse.getProducts()
                .forEach((product, count) -> log.info("{}: count = {}", product, count));
    }

    public boolean addProductToBucket(Integer productId, Integer count) {
        try {
            bucket.addProduct(warehouse.getById(productId, count), count);
            return true;
        } catch (SoManyProductsException e) {
            log.info("*** Not enough products in warehouse ***");
            return false;
        } catch (ProductNotFoundException e) {
            log.info("*** Product not found ***");
            return false;
        }
    }

    public boolean deleteProductFromTheBucket(Integer productId, Integer count) {
        if (bucket.isEmpty()) {
            log.info("*** Bucket is empty, nothing to remove ***");
            return false;
        }

        try {
            bucket.removeProduct(warehouse.getById(productId), count);
            return true;
        } catch (ProductNotFoundException e) {
            log.info("*** Product not found ***");
            return false;
        } catch (NegativeNumberProductsException e) {
            log.info("*** Wrong number of products: {} ***", count);
            return false;
        }
    }

    public boolean showProductsInTheBucket() {
        if (! bucket.isEmpty()) {
            bucket.getAll()
                    .forEach((product, count) -> log.info("{}: count = {}", product, count));
            return true;
        }

        return false;
    }

    public void clearBucket() {
        bucket.clear();
    }

    public BigDecimal makeOrder(Currency currency) {
        return bucket.calculateTotal(currency);
    }
}
