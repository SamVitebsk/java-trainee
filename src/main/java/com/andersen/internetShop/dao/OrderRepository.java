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

    public List<Order> getAll(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            CallableStatement cs = connection.prepareCall("{call get_orders(?)}");
            cs.setString(1, user.getId().toString());
            ResultSet rs = cs.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"),
                        rs.getDate("created_at").toLocalDate(),
                        UUID.fromString(rs.getString("user_id")),
                        rs.getBigDecimal("total"),
                        rs.getBoolean("accepted")
                ));
            }
            return orders;
        } catch (SQLException e) {
            log.error("Order SQL error: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public Order getById(User user, Integer orderId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from orders where id = ? and user_id = ?");
            ps.setInt(1, orderId);
            ps.setString(2, user.getId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getDate("created_at").toLocalDate(),
                        UUID.fromString(rs.getString("user_id")),
                        rs.getBigDecimal("total"),
                        rs.getBoolean("accepted")
                );
            }
            return null;
        } catch (SQLException e) {
            log.error("Order SQL error: {}", e.getMessage());
            return null;
        }
    }

    public boolean acceptOrder(User user, Integer orderId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("update orders set accepted = true where id = ? and user_id = ?");
            ps.setInt(1, orderId);
            ps.setString(2, user.getId().toString());
            int rows = ps.executeUpdate();
            return rows != 0;
        } catch (SQLException e) {
            log.error("Order SQL error: {}", e.getMessage());
            return false;
        }
    }

    public List<Order> getNotAcceptedOrders(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from orders where accepted = false and user_id = ?");
            ps.setString(1, user.getId().toString());
            ResultSet rs = ps.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getDate("created_at").toLocalDate(),
                        UUID.fromString(rs.getString("user_id")),
                        rs.getBigDecimal("total"),
                        rs.getBoolean("accepted")
                ));
            }
            return orders;
        } catch (SQLException e) {
            log.error("Order SQL error: {}", e.getMessage());
            return null;
        }
    }
}