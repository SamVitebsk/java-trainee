package com.andersen.internetShop.service;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.dao.BucketRepository;
import com.andersen.internetShop.dao.Product;
import com.andersen.internetShop.dao.Warehouse;
import com.andersen.internetShop.exceptions.NegativeNumberProductsException;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.exceptions.SoManyProductsException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
public class BucketService {
    private final Warehouse warehouse;
    private final BucketRepository bucketRepository;

    public BucketService(Warehouse warehouse, BucketRepository bucketRepository) {
        this.warehouse = warehouse;
        this.bucketRepository = bucketRepository;
    }

    public void showProductList() {
        warehouse.getAll()
                .forEach((product, count) -> log.info("{}: count = {}", product, count));
    }

    public boolean addProductToBucket(Integer productId, Integer count) {
        boolean added = false;
        try {
            bucketRepository.addProduct(warehouse.getById(productId, count), count);
            added = true;
        } catch (SoManyProductsException e) {
            log.info("*** Not enough products in warehouse ***");
        } catch (ProductNotFoundException e) {
            log.info("*** Product not found ***");
        }

        return added;
    }

    public boolean addProductToBucket(Integer productId) {
        return addProductToBucket(productId, 1);
    }

    public boolean deleteProductFromTheBucket(Integer productId, Integer count) {
        if (bucketRepository.isEmpty()) {
            log.info("*** Bucket is empty, nothing to remove ***");
            return false;
        }

        boolean deleted = false;
        try {
            bucketRepository.removeProduct(warehouse.getById(productId), count);
            deleted = true;
        } catch (ProductNotFoundException e) {
            log.info("*** Product not found ***");
        } catch (NegativeNumberProductsException e) {
            log.info("*** Wrong number of products: {} ***", count);
        }

        return deleted;
    }

    public boolean deleteProductFromTheBucket(Integer productId) {
        return deleteProductFromTheBucket(productId, 1);
    }

    public void showProductsInTheBucket() {
        bucketRepository.getAll()
                .forEach((product, count) -> log.info("{}: count = {}", product, count));
    }

    public Map<Product, Integer> getProducts() {
        return bucketRepository.getAll();
    }

    public void clearBucket() {
        bucketRepository.clear();
        log.info("*** Bucket cleared ***");
    }

    public boolean bucketIsEmpty() {
        return bucketRepository.isEmpty();
    }

    public BigDecimal getTotal(Currency currency) {
        return bucketRepository.calculateTotal(currency);
    }
}
