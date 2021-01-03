package com.andersen.internetShop.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
public class Connect implements AutoCloseable{
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Moscow";
    private static Connection connection;

    public static Connection getConnection() {
        if (Objects.isNull(connection)) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                log.error("SQL Exception: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(connection)) {
            connection.close();
        }
    }
}
