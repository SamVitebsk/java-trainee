package com.andersen.internetShop.dao;

import com.andersen.internetShop.utils.Connect;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.UUID;

@Slf4j
public class UserRepository {
    public User getByLoginAndPassword(String login, String password) {
        try (Connection connection = Connect.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from customers where login = ? and password = ?");

            ps.setString(1, login);
            String hash = DigestUtils.sha512_224Hex(password);
            ps.setString(2, hash);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                User user = new User(UUID.fromString(id), login, hash);

                log.info("user: {}", user.toString());
                return user;
            }
            return null;
        } catch (SQLException e) {
            log.error("Login SQL exception: {}", e.getMessage());
            return null;
        }
    }

    public User create(String login, String password) {
        try (Connection connection = Connect.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into customers (id, login, password) values (?, ?, ?)");
            String id = UUID.randomUUID().toString();
            ps.setString(1, id);
            ps.setString(2, login);
            ps.setString(3, DigestUtils.sha512_224Hex(password));
            int rows = ps.executeUpdate();

            if (rows != 0) {
                return getByLoginAndPassword(login, password);
            }

            return null;
        } catch (SQLException e) {
            log.error("Registration SQL exception: {}", e.getMessage());
            return null;
        }
    }
}
