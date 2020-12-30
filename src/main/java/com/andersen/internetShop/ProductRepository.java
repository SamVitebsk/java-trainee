package com.andersen.internetShop;

import java.math.BigDecimal;
import java.util.List;

public class ProductRepository {
    public List<Product> products;

    ProductRepository() {
        products = List.of(
                new Product("table", BigDecimal.valueOf(250), ProductCategory.NOT_FOOD),
                new Product("notebook", BigDecimal.valueOf(1200), ProductCategory.NOT_FOOD),
                new Product("water", BigDecimal.valueOf(5), ProductCategory.FOOD),
                new Product("apple", BigDecimal.valueOf(10), ProductCategory.FOOD),
                new Product("bread", BigDecimal.valueOf(4), ProductCategory.FOOD),
                new Product("meat", BigDecimal.valueOf(15), ProductCategory.FOOD),
                new LimitedShelfLifeProduct("cheese", BigDecimal.valueOf(40), ProductCategory.MILK),
                new LimitedShelfLifeProduct("oil", BigDecimal.valueOf(30), ProductCategory.MILK),
                new LimitedShelfLifeProduct("yogurt", BigDecimal.valueOf(20), ProductCategory.MILK)
        );
    }

    public List<Product> getAll() {
        return products;
    }

    public Product getById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
