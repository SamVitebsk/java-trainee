package com.andersen.internetShop.dao;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ProductRepository {
    public List<Product> products = new ArrayList<>();

    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Moscow";

    public boolean create(String name, BigDecimal price, ProductCategory category) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("insert into products (name, price, category) values (?, ?, ?)");
            ps.setString(1, name);
            ps.setBigDecimal(2, price);
            ps.setString(3, category.name());
            int rows = ps.executeUpdate();
            return rows != 0;
        } catch (SQLException e) {
            log.error("Order SQL error: {}", e.getMessage());
            return false;
        }
    }

    public ProductRepository() {
//        products = List.of(
//                new Product("table", BigDecimal.valueOf(250), ProductCategory.NOT_FOOD),
//                new Product("notebook", BigDecimal.valueOf(1200), ProductCategory.NOT_FOOD),
//                new Product("water", BigDecimal.valueOf(5), ProductCategory.FOOD),
//                new Product("apple", BigDecimal.valueOf(10), ProductCategory.FOOD),
//                new Product("bread", BigDecimal.valueOf(4), ProductCategory.FOOD),
//                new Product("meat", BigDecimal.valueOf(15), ProductCategory.FOOD),
//                new LimitedShelfLifeProduct("cheese", BigDecimal.valueOf(40), ProductCategory.MILK),
//                new LimitedShelfLifeProduct("oil", BigDecimal.valueOf(30), ProductCategory.MILK),
//                new LimitedShelfLifeProduct("yogurt", BigDecimal.valueOf(20), ProductCategory.MILK)
//        );
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

    public Product getByIdFromDb(Integer id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from products where id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    ProductCategory.valueOf(rs.getString("category"))
                );
            }
            return null;
        } catch (SQLException e) {
            log.error("Product SQL error: {}", e.getMessage());
            return null;
        }
    }

    public List<Product> getAllFromDb() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from products");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    ProductCategory.valueOf(rs.getString("category"))
                ));
            }
            return products;
        } catch (SQLException e) {
            log.error("Product SQL error: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
