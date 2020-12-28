package com.andersen.internetShop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainController {
    private final ProductRepository productRepository;
    private final Bucket bucket;

    public void showProductList() {
        productRepository.getAll().forEach(System.out::println);
    }

    public void addProductToBucket(Long productId) {
        bucket.addProduct(productRepository.getById(productId));
    }

    public boolean deleteProductFromTheBucket(Long productId) {
        if (! bucket.isEmpty()) {
            bucket.removeProduct(productRepository.getById(productId));
            return true;
        }

        return false;
    }

    public boolean showProductsInTheBucket() {
        if (! bucket.isEmpty()) {
            bucket.getAll().forEach(System.out::println);
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
}
