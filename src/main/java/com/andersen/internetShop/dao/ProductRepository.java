package com.andersen.internetShop.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public boolean create(String name, BigDecimal price, ProductCategory category) {
        int rows = jdbcTemplate.update("insert into products (name, price, category) values (?, ?, ?)", name, price, category.name());

        return rows != 0;
    }

    public Product getById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from products where id = ?",
                new Object[] {id},
                new ProductMapper());
    }

    public List<Product> getAll() {
        return jdbcTemplate.query("select * from products", new ProductMapper());
    }
}

