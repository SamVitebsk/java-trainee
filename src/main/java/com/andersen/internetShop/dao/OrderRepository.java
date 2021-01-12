package com.andersen.internetShop.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    private final RowMapper<Order> orderRowMapper = (rs, rowNums) -> new Order(
            rs.getInt("id"),
            rs.getDate("created_at").toLocalDate(),
            UUID.fromString(rs.getString("user_id")),
            rs.getBigDecimal("total"),
            rs.getBoolean("accepted")
    );

    public boolean create(UUID userId, BigDecimal total, Boolean accepted) {
        int rows = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            rows = jdbcTemplate.update(
                    "insert into orders (user_id, created_at, total, accepted) values (?, ?, ?, ?)",
                    userId.toString(),
                    Date.valueOf(LocalDate.now()),
                    total,
                    accepted
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Order create exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return rows != 0;
    }


    public List<Order> getAll(User user) {
        List<Order> orders = Collections.emptyList();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            orders = jdbcTemplate.query(
                    "call get_orders(?)",
                    new Object[] {user.getId().toString()},
                    orderRowMapper
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Order get all exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return orders;
    }

    public Order getById(User user, Integer orderId) {
        Order order = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            order = jdbcTemplate.queryForObject(
                    "select * from orders where id = ? and user_id = ?",
                    new Object[] {orderId, user.getId().toString()},
                    orderRowMapper
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Order get by id exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return order;
    }

    public boolean acceptOrder(User user, Integer orderId) {
        int rows = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            rows = jdbcTemplate.update(
                    "update orders set accepted = true where id = ? and user_id = ?",
                    orderId,
                    user.getId().toString()
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Order accept exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return rows != 0;
    }

    public List<Order> getNotAcceptedOrders(User user) {
        List<Order> orders = Collections.emptyList();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            orders = jdbcTemplate.query(
                    "select * from orders where accepted = false and user_id = ?",
                    new Object[] {user.getId().toString()},
                    orderRowMapper
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Not accepted orders exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return orders;
    }
}