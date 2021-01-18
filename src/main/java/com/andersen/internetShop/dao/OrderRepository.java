package com.andersen.internetShop.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Order> orderRowMapper = (rs, rowNums) -> new Order(
            rs.getInt("id"),
            rs.getDate("created_at").toLocalDate(),
            rs.getLong("user_id"),
            rs.getBigDecimal("total"),
            rs.getBoolean("accepted")
    );

    public boolean create(Long userId, BigDecimal total, Boolean accepted) {
        int rows = jdbcTemplate.update(
                "insert into orders (user_id, created_at, total, accepted) values (?, ?, ?, ?)",
                userId,
                Date.valueOf(LocalDate.now()),
                total,
                accepted
        );

        return rows != 0;
    }

    public List<Order> getAll(User user) {
        return jdbcTemplate.query(
                "call get_orders(?)",
                new Object[] {user.getId().toString()},
                orderRowMapper
        );
    }

    public Order getById(User user, Integer orderId) {
        return jdbcTemplate.queryForObject(
                        "select * from orders where id = ? and user_id = ?",
                        new Object[] {orderId, user.getId().toString()},
                        orderRowMapper
        );
    }

    public boolean acceptOrder(User user, Integer orderId) {
        int rows = jdbcTemplate.update(
                "update orders set accepted = true where id = ? and user_id = ?",
                orderId,
                user.getId()
        );

        return rows != 0;
    }

    public List<Order> getNotAcceptedOrders(User user) {
        return jdbcTemplate.query(
                "select * from orders where accepted = false and user_id = ?",
                new Object[] {user.getId().toString()},
                orderRowMapper
        );
    }
}