package com.andersen.internetShop.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public boolean create(String name, BigDecimal price, ProductCategory category) {
        int rows = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            rows = jdbcTemplate.update("insert into products (name, price, category) values (?, ?, ?)", name, price, category.name());

            connection.commit();
        } catch (Exception e) {
            log.error("Product create exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return rows != 0;
    }

    public Product getById(Integer id) {
        Product product = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            product = jdbcTemplate.queryForObject(
                    "select * from products where id = ?",
                    new Object[] {id},
                    new ProductMapper());
            connection.commit();

        } catch (Exception e) {
            log.error("Product get by id exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return product;
    }

    public List<Product> getAll() {
        List<Product> products = Collections.emptyList();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            products = jdbcTemplate.query("select * from products", new ProductMapper());
            connection.commit();

        } catch (Exception e) {
            log.error("Products get all exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }
        return products;
    }
}

