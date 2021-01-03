package com.andersen.internetShop;

import com.andersen.internetShop.utils.Connect;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBTest {
//    @Disabled
    @Test
    void insert() {
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            System.out.println(
                    Objects.isNull(connection) ? "NULL" : "NOT NULL"
            );
            UUID userId = UUID.randomUUID();
            PreparedStatement ps = connection.prepareStatement("insert into customers (id, login, password) values (?, ?, ?) ");
            ps.setString(1, userId.toString());
            ps.setString(2, "admin-" + userId.toString().substring(0, 4));
            ps.setString(3, "admin");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


    }
}