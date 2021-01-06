package com.andersen.internetShop.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.UUID;

@Slf4j
public class UserRepository extends BaseRepository {

    public User getByLoginAndPassword(String login, String password) {
        User user = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from customers where login = ? and password = ?");

            ps.setString(1, login);
            String hash = DigestUtils.sha512_224Hex(password);
            ps.setString(2, hash);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                user = new User(UUID.fromString(id), login, hash);
                log.info("Welcome: {}", user.toString());
            }
        } catch (SQLException e) {
            log.error("Login SQL exception: {}", e.getMessage());
        }

        return user;
    }

    public User create(String login, String password) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("insert into customers (id, login, password) values (?, ?, ?)");
            String id = UUID.randomUUID().toString();
            ps.setString(1, id);
            ps.setString(2, login);
            ps.setString(3, DigestUtils.sha512_224Hex(password));
            int rows = ps.executeUpdate();

            if (rows != 0) {
                user = getByLoginAndPassword(login, password);
            }
        } catch (SQLException e) {
            log.error("Registration SQL exception: {}", e.getMessage());
        }

        return user;
    }
}
