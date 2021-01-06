package com.andersen.internetShop.dao;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.exceptions.NegativeNumberProductsException;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.exceptions.SoManyProductsException;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class BucketRepository extends BaseRepository implements Serializable {
    private final ProductRepository productRepository;
    private final Warehouse warehouse;
    private Bucket bucket;

    public BucketRepository(User user, ProductRepository productRepository, Warehouse warehouse) {
        this.productRepository = productRepository;
        this.warehouse = warehouse;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from bucket where user_id = ?;");
            ps.setString(1, user.getId().toString());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bucket = new Bucket(rs.getInt("id"), user);
            } else {
                ps = connection.prepareStatement("insert into bucket (user_id) values (?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getId().toString());
                ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    bucket = new Bucket(generatedKeys.getInt(1), user);
                }
            }
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }
    }

    public void addProduct(Product product, Integer count) {
        checkInput(product, count);

        int countOnWarehouse = warehouse.countProductById(product.getId());
        if (countOnWarehouse < count) {
            throw new SoManyProductsException();
        }

        Product productById = getById(product.getId());

        if (Objects.nonNull(productById)) {
            increaseCountProduct(product.getId(), count);
        } else {
            insertProduct(product.getId(), count);
        }

        warehouse.reduceCountProducts(product.getId(), count);
    }

    private void insertProduct(Integer productId, Integer count) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into bucket_product (bucket_id, product_id, count) values (?, ?, ?)"
            );
            ps.setInt(1, bucket.getId());
            ps.setInt(2, productId);
            ps.setInt(3, count);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void removeProduct(Product product, Integer count) {
        checkInput(product, count);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from bucket_product where bucket_id = ? and product_id = ?;"
            );
            ps.setInt(1, bucket.getId());
            ps.setInt(2, product.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int currentCount = rs.getInt("count") - count;
                if (currentCount <= 0) {
                    updateCountProduct(product.getId(), rs.getInt("count"));
                    deleteFromBucket(product.getId());
                    warehouse.increaseCountProducts(product.getId(), rs.getInt("count"));
                } else {
                    updateCountProduct(product.getId(), currentCount);
                    warehouse.increaseCountProducts(product.getId(), count);
                }
            } else {
                throw new ProductNotFoundException();
            }
        } catch (SQLException e) {
            log.error("Bucket DELETE SQL error: {}", e.getMessage());
        }
    }

    private void updateCountProduct(Integer productId, Integer count) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "update bucket_product set count = ? where bucket_id = ? and product_id = ?;"
            );
            ps.setInt(1, count);
            ps.setInt(2, bucket.getId());
            ps.setInt(3, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }
    }

    private void increaseCountProduct(Integer productId, Integer count) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "update bucket_product set count = count + ? where bucket_id = ? and product_id = ?;"
            );
            ps.setInt(1, count);
            ps.setInt(2, bucket.getId());
            ps.setInt(3, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }
    }

    private void deleteFromBucket(Integer productId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "delete from bucket_product where bucket_id = ? and product_id = ?;"
            );
            ps.setInt(1, bucket.getId());
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }
    }

    public void removeProduct(Product product) {
        removeProduct(product, 1);
    }

    public void clear() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("delete from bucket_product where bucket_id = ?;");
            ps.setInt(1, bucket.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }
    }

    public Map<Product, Integer> getAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select * from bucket_product where bucket_id = ?");
            ps.setInt(1, bucket.getId());
            ResultSet rs = ps.executeQuery();
            bucket.getProducts().clear();

            while (rs.next()) {
                bucket.getProducts().put(
                        productRepository.getById(rs.getInt("product_id")),
                        rs.getInt("count")
                );
            }
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }

        return bucket.getProducts();
    }

    public Product getById(Integer productId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from bucket_product where bucket_id = ? and product_id = ?");
            ps.setInt(1, bucket.getId());
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return productRepository.getById(rs.getInt("product_id"));
            }
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }

        return null;
    }

    public Integer countProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select count(product_id) as quantity from bucket_product where bucket_id = ?");
            ps.setInt(1, bucket.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }

        return 0;
    }

    public Integer countItems() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement("select sum(count) as quantity from bucket_product where bucket_id = ?");
            ps.setInt(1, bucket.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            log.error("Bucket SQL error: {}", e.getMessage());
        }

        return 0;
    }

    public boolean isEmpty() {
        return countItems() == 0;
    }

    public BigDecimal calculateTotal(Currency currency) {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> pair : bucket.getProducts().entrySet()) {
            total = total.add(pair.getKey().getPrice().multiply(BigDecimal.valueOf(pair.getValue())));
        }

        return total.multiply(BigDecimal.valueOf(currency.getMultiplicity()))
                .multiply(BigDecimal.valueOf(currency.getCourse()));
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
