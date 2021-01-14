package com.andersen.internetShop.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public User getByLoginAndPassword(String login, String password) {
        String hash = DigestUtils.sha512_224Hex(password);

        return jdbcTemplate.queryForObject(
                "select * from customers where login = ? and password = ?",
                new Object[] {login, hash},
                (rs, i) -> new User(UUID.fromString(rs.getString("id")), login, hash)
        );
    }

    public User create(String login, String password) {
        User user = null;

        int rows = jdbcTemplate.update(
                "insert into customers (id, login, password) values (?, ?, ?)",
                UUID.randomUUID().toString(),
                login,
                DigestUtils.sha512_224Hex(password)
        );
        if (rows != 0) {
            user = getByLoginAndPassword(login, password);
        }

        return user;
    }

    public UserDetails getByLogin(String login) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(
                    "select * from customers where login = ?",
                    new Object[]{login},
                    (rs, i) -> new User(UUID.fromString(rs.getString("id")), login, rs.getString("password"))
            );
        } catch (Exception e) {
            log.error("User not fount: {}", e.getMessage());
        }

        return user;
    }
}
