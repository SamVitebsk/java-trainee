package com.andersen.internetShop.dao;

import com.andersen.internetShop.utils.ExpiryDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class WarehouseRepository {
    private final ProductRepository productRepository;
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public void addProduct(Product product, Integer count) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            count = checkCount(count);
            checkExpiryDate(product);

            Product item = getById(product.getId());

            if (Objects.nonNull(item)) {
                increaseCountProducts(product.getId(), count);
            } else {
                insertProduct(product.getId(), count);
            }
            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse add product exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    private void insertProduct(Integer productId, Integer count) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            checkCount(count);
            jdbcTemplate.update("insert into warehouse (product_id, count) values (?, ?)", productId, count);

            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse insert exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }
    }

    public Product getById(Integer productId, Integer count) {
        Product product = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            checkCount(count);
            product = jdbcTemplate.queryForObject(
                    "select * from warehouse where product_id = ?",
                    new Object[]{productId},
                    (rs, rowNums) -> productRepository.getById(rs.getInt("product_id"))
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse get by id exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return product;
    }

    public Product getById(Integer id) {
        return getById(id, 1);
    }

    public Integer countItems() {
        Integer count = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            count = jdbcTemplate.queryForObject(
                    "select sum(count) as total from warehouse",
                    (rs, i) -> rs.getInt("total")
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse items count exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return count;
    }

    public Integer countProducts() {
        Integer count = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            count = jdbcTemplate.queryForObject(
                    "select count(*) as quantity from warehouse",
                    (rs, i) -> rs.getInt("quantity")
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse products count exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return count;
    }

    public Integer countProductById(Integer productId) {
        Integer count = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            count = jdbcTemplate.queryForObject(
                    "select count from warehouse where product_id = ?",
                    new Object[]{productId},
                    (rs, rowNum) -> rs.getInt("count")
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse products count by id exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return count;
    }

    public boolean isEmpty() {
        return countItems() == 0;
    }

    private Integer checkCount(Integer count) {
        return count < 0 ? 0 : count;
    }

    public Map<Product, Integer> getAll() {
        Map<Product, Integer> products = new LinkedHashMap<>();

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            List<ProductCount> productCounts = jdbcTemplate.query(
                    "select * from warehouse order by product_id",
                    (rs, rowNum) -> new ProductCount(
                            getById(rs.getInt("product_id")),
                            rs.getInt("count")
                    )
            );
            productCounts.forEach(pc -> products.put(pc.getProduct(), pc.getCount()));
            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse get all exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return products;
    }

    public boolean update(Integer productId, Integer count) {
        int rows = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            rows = jdbcTemplate.update("update warehouse set count = ? where product_id = ?", count, productId);

            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse update exception: {}", e.getMessage());
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

    public boolean increaseCountProducts(Integer productId, Integer count) {
        int rows = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            rows = jdbcTemplate.update(
                    "update warehouse set count = (count + ?) where product_id = ?",
                    count,
                    productId
            );
            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse increase count exception: {}", e.getMessage());
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

    public boolean reduceCountProducts(Integer productId, Integer count) {
        int rows = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            rows = jdbcTemplate.update("update warehouse set count = count - ? where product_id = ?", count, productId);

            connection.commit();
        } catch (Exception e) {
            log.error("Warehouse reduce count exception: {}", e.getMessage());
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
