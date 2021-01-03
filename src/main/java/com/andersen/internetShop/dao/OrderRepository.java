package com.andersen.internetShop.dao;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
public class OrderRepository {
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Moscow";

    public boolean create(UUID userId, BigDecimal total, Boolean accepted) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("insert into orders (user_id, created_at, total, accepted) values (?, ?, ?, ?)");
            ps.setString(1, userId.toString());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setBigDecimal(3, total);
            ps.setBoolean(4, accepted);
            int rows = ps.executeUpdate();
            return rows != 0;
        } catch (SQLException e) {
            log.error("Order SQL error: {}", e.getMessage());
            return false;
        }
    }
}