package com.andersen.internetShop.dao;

import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.utils.ExpiryDate;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Warehouse {
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Moscow";

    private final ProductRepository productRepository;

    public Warehouse(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product, Integer count) {
        count = checkCount(count);
        checkExpiryDate(product);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Product item = getById(product.getId());

            PreparedStatement ps;
            if (Objects.nonNull(item)) {
                ps = connection.prepareStatement("update warehouse as w set w.count = w.count + ? where product_id = ?;");
                ps.setInt(1, count);
                ps.setInt(2, product.getId());
            } else {
                ps = connection.prepareStatement("insert into warehouse (product_id, count) values (?, ?);");
                ps.setInt(1, product.getId());
                ps.setInt(2, count);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Warehouse SQL error: {}", e.getMessage());
        }

    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public Product getById(Integer productId, Integer count) {
        count = checkCount(count);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from warehouse where product_id = ?;");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return productRepository.getById(rs.getInt("product_id"));
            }
            throw new ProductNotFoundException();
        } catch (SQLException e) {
            log.error("Warehouse SQL error: {}", e.getMessage());
            throw new ProductNotFoundException();
        }
    }

    public Product getById(Integer id) {
        return getById(id, 1);
    }

    public Integer countItems() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select sum(count) as total from warehouse;");
            if (rs.next()) {
                int total = rs.getInt("total");
                log.info("countItems: {}", total);
                return total;
            }
            return 0;
        } catch (SQLException e) {
            log.error("Warehouse SQL error: {}", e.getMessage());
            return 0;
        }
    }

    public Integer countProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select count(*) as quantity from warehouse;");
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                log.info("SIZE: {}", quantity);
                return quantity;
            }
            return 0;
        } catch (SQLException e) {
            log.error("Warehouse SQL error: {}", e.getMessage());
            return 0;
        }
    }

    public boolean isEmpty() {
        return countItems() == 0;
    }

    private Integer checkCount(Integer count) {
        return count < 0 ? 0 : count;
    }

    public Map<Product, Integer> getAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from warehouse order by product_id;");
            ResultSet rs = ps.executeQuery();
            Map<Product, Integer> products = new LinkedHashMap<>();
            while (rs.next()) {
                Product product = productRepository.getById(rs.getInt("product_id"));
                products.put(product, rs.getInt("count"));
            }
            return products;
        } catch (SQLException e) {
            log.error("Warehouse SQL error: {}", e.getMessage());
            return null;
        }
    }

    public boolean update(Integer productId, Integer count) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("update warehouse set count = ? where product_id = ?;");
            ps.setInt(1, count);
            ps.setInt(2, productId);
            int rows = ps.executeUpdate();
            return rows != 0;

        } catch (SQLException e) {
            log.error("Warehouse SQL error: {}", e.getMessage());
            return false;
        }
    }

    private void checkExpiryDate(Product product) {
        if (product instanceof LimitedShelfLifeProduct) {
            try {
                Field expiryDateField = product.getClass().getDeclaredField("expiryDate");
                expiryDateField.setAccessible(true);
                ExpiryDate annotation = expiryDateField.getDeclaredAnnotation(ExpiryDate.class);
                int lifeDays = annotation.shelfLifeInDays();
                expiryDateField.set(product, LocalDate.now().plusDays(lifeDays));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("ExpiryDate: something wrong");
            }
        }
    }
}
