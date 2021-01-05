package com.andersen.internetShop.dao;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductRepository {
    public List<Product> products = new ArrayList<>();
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Moscow";

    public ProductRepository() {
    }

    public boolean create(String name, BigDecimal price, ProductCategory category) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("insert into products (name, price, category) values (?, ?, ?);");
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

    public Product getById(Integer id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from products where id = ?;");
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

    public List<Product> getAll() {
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
            return products;
        }
    }
}
