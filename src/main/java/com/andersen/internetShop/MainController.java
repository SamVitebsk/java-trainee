package com.andersen.internetShop;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.exceptions.NegativeNumberProductsException;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.exceptions.SoManyProductsException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class MainController {
    private final Warehouse warehouse;
    private final Bucket bucket;

    public void showProductList() {
        warehouse.getProducts()
                .forEach((product, count) -> System.out.println(product + ": count = " + count));
    }

    public boolean addProductToBucket(Integer productId, Integer count) {
        try {
            bucket.addProduct(warehouse.getById(productId, count), count);
            return true;
        } catch (SoManyProductsException e) {
            System.out.println("*** Not enough products in warehouse ***");
            return false;
        } catch (ProductNotFoundException e) {
            System.out.println("*** Product not found ***");
            return false;
        }
    }

    public boolean deleteProductFromTheBucket(Integer productId, Integer count) {
        if (bucket.isEmpty()) {
            System.out.println("*** Bucket is empty, nothing to remove ***");
            return false;
        }

        try {
            bucket.removeProduct(warehouse.getById(productId), count);
            return true;
        } catch (ProductNotFoundException e) {
            System.out.println("*** Product not found ***");
            return false;
        } catch (NegativeNumberProductsException e) {
            System.out.printf("*** Wrong number of products: %d ***", count);
            return false;
        }
    }

    public boolean showProductsInTheBucket() {
        if (! bucket.isEmpty()) {
            bucket.getAll()
                    .forEach((product, count) -> System.out.println(product + ": count = " + count));
            return true;
        }

        return false;
    }

    public void clearBucket() {
        bucket.clear();
    }

    public void exit() {
        System.out.println("Good bye");
    }

    public BigDecimal makeOrder(Currency currency) {
        return bucket.calculateTotal(currency);
    }
}
