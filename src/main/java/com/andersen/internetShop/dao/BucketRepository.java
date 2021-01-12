package com.andersen.internetShop.dao;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.exceptions.NegativeNumberProductsException;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.exceptions.SoManyProductsException;
import com.andersen.internetShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class BucketRepository implements Serializable {
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final AuthService authService;
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    private Bucket getOrCreateBucket() {
        Bucket bucket = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            User user = this.authService.getAuthUser();

            if (Objects.nonNull(user)) {
                bucket = getBucket(user);

                if (Objects.isNull(bucket)) {
                    createBucket(user);
                    bucket = getBucket(user);
                }
            }
            connection.commit();

        } catch (Exception e) {
            log.error("Get or create bucket exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return bucket;
    }

    private Bucket getBucket(User user) {
        Bucket bucket = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            bucket = jdbcTemplate.queryForObject(
                    "select * from bucket where user_id = ?",
                    new Object[]{user.getId().toString()},
                    (rs, rowNum) -> new Bucket(rs.getInt("id"), user)
            );
            connection.commit();

        } catch (Exception e) {
            log.info("Bucket not found: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return bucket;
    }

    private void createBucket(User user) {
        jdbcTemplate.update("insert into bucket (user_id) values (?)", user.getId().toString());
    }

    public boolean addProduct(Product product, Integer count) {
        checkInput(product, count);
        boolean result = false;

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            int countOnWarehouse = warehouseRepository.countProductById(product.getId());
            if (countOnWarehouse < count) {
                throw new SoManyProductsException();
            }

            Product productById = getById(product.getId());

            if (Objects.nonNull(productById)) {
                increaseCountProduct(product.getId(), count);
            } else {
                insertProduct(product.getId(), count);
            }

            result = warehouseRepository.reduceCountProducts(product.getId(), count);
            connection.commit();

        } catch (Exception e) {
            log.error("Add product bucket exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return result;
    }

    private void insertProduct(Integer productId, Integer count) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            jdbcTemplate.update(
                    "insert into bucket_product (bucket_id, product_id, count) values (?, ?, ?)",
                    bucket.getId(),
                    productId,
                    count
            );
            connection.commit();

        } catch (Exception e) {
            log.error("Insert product bucket exception: {}", e.getMessage());
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

    public void removeProduct(Product product, Integer count) {
        checkInput(product, count);
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            Integer countFromDb = jdbcTemplate.queryForObject(
                    "select count from bucket_product where bucket_id = ? and product_id = ?",
                    Integer.class,
                    bucket.getId(),
                    product.getId()
            );

            if (Objects.nonNull(countFromDb)) {
                int currentCount = countFromDb - count;
                if (currentCount <= 0) {
                    updateCountProduct(product.getId(), countFromDb);
                    deleteFromBucket(product.getId());
                    warehouseRepository.increaseCountProducts(product.getId(), countFromDb);
                } else {
                    updateCountProduct(product.getId(), currentCount);
                    warehouseRepository.increaseCountProducts(product.getId(), count);
                }
            } else {
                throw new ProductNotFoundException();
            }
            connection.commit();

        } catch (Exception e) {
            log.error("Remove product bucket exception: {}", e.getMessage());
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

    private void updateCountProduct(Integer productId, Integer count) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            jdbcTemplate.update(
                    "update bucket_product set count = ? where bucket_id = ? and product_id = ?",
                    count,
                    bucket.getId(),
                    productId
            );
            connection.commit();

        } catch (Exception e) {
            log.error("Update product bucket exception: {}", e.getMessage());
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

    private void increaseCountProduct(Integer productId, Integer count) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            jdbcTemplate.update(
                    "update bucket_product set count = count + ? where bucket_id = ? and product_id = ?",
                    count,
                    bucket.getId(),
                    productId
            );
            connection.commit();

        } catch (Exception e) {
            log.error("Increase product count exception: {}", e.getMessage());
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

    private void deleteFromBucket(Integer productId) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            jdbcTemplate.update(
                    "delete from bucket_product where bucket_id = ? and product_id = ?",
                    bucket.getId(),
                    productId
            );
            connection.commit();

        } catch (Exception e) {
            log.error("Delete from bucket exception: {}", e.getMessage());
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

    public void removeProduct(Product product) {
        removeProduct(product, 1);
    }

    public void clear() {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            jdbcTemplate.update("delete from bucket_product where bucket_id = ?", bucket.getId());

            connection.commit();

        } catch (Exception e) {
            log.error("Bucket clear exception: {}", e.getMessage());
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

    public Map<Product, Integer> getAll() {
        Bucket bucket = getOrCreateBucket();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            List<ProductCount> productCounts = jdbcTemplate.query(
                    "select * from bucket_product where bucket_id = ?",
                    new Object[]{bucket.getId()},
                    (rs, rowNum) -> new ProductCount(
                            getById(rs.getInt("product_id")),
                            rs.getInt("count")
                    )
            );
            bucket.getProducts().clear();

            productCounts.forEach(pc -> bucket.getProducts().put(
                    productRepository.getById(pc.getProduct().getId()),
                    pc.getCount()
            ));
            connection.commit();

        } catch (Exception e) {
            log.error("Get all from bucket exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return bucket.getProducts();
    }

    public Product getById(Integer productId) {
        Product product = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            product = jdbcTemplate.queryForObject(
                    "select * from bucket_product where bucket_id = ? and product_id = ?",
                    new Object[] {bucket.getId(), productId},
                    (rs, rowNum) -> productRepository.getById(rs.getInt("product_id"))
            );
            connection.commit();

        } catch (Exception e) {
            log.error("Get by id from bucket exception: {}", e.getMessage());
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

    public Integer countProducts() {
        Integer count = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            count = jdbcTemplate.queryForObject(
                    "select count(product_id) as quantity from bucket_product where bucket_id = ?",
                    new Object[] {bucket.getId()},
                    (rs, rowNum) -> rs.getInt("quantity")
            );

            connection.commit();

        } catch (Exception e) {
            log.error("Products count bucket exception: {}", e.getMessage());
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

    public Integer countItems() {
        Integer count = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Bucket bucket = getOrCreateBucket();
            count = jdbcTemplate.queryForObject(
                    "select sum(count) as quantity from bucket_product where bucket_id = ?",
                    new Object[] {bucket.getId()},
                    (rs, numRows) -> rs.getInt("quantity")
            );

            connection.commit();

        } catch (Exception e) {
            log.error("Items count bucket exception: {}", e.getMessage());
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

    public BigDecimal calculateTotal(Currency currency) {
        BigDecimal total = BigDecimal.ZERO;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            for (Map.Entry<Product, Integer> pair : getAll().entrySet()) {
                total = total.add(pair.getKey().getPrice().multiply(BigDecimal.valueOf(pair.getValue())));
            }

            total = total.multiply(BigDecimal.valueOf(currency.getMultiplicity()))
                    .multiply(BigDecimal.valueOf(currency.getCourse()));

            connection.commit();

        } catch (Exception e) {
            log.error("Calculate total bucket exception: {}", e.getMessage());
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex) {
                log.error("Rollback exception: {}", ex.getMessage());
            }
        }

        return total;
    }

    private void checkInput(Product product, Integer count) {
        if (Objects.isNull(product)) {
            throw new ProductNotFoundException();
        }

        if (count <= 0) {
            throw new NegativeNumberProductsException();
        }
    }
}
