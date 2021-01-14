package com.andersen.internetShop.dao;

import com.andersen.internetShop.currency.Currency;
import com.andersen.internetShop.exceptions.NegativeNumberProductsException;
import com.andersen.internetShop.exceptions.ProductNotFoundException;
import com.andersen.internetShop.exceptions.SoManyProductsException;
import com.andersen.internetShop.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
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

    @Transactional
    protected Bucket getOrCreateBucket() {
        Bucket bucket = null;

        User user = this.authService.getAuthUser();

        if (Objects.nonNull(user)) {
            bucket = getBucket(user);

            if (Objects.isNull(bucket)) {
                createBucket(user);
                bucket = getBucket(user);
            }
        }

        return bucket;
    }

    @Transactional
    protected Bucket getBucket(User user) {
        Bucket bucket = null;
        try {
            bucket = jdbcTemplate.queryForObject(
                    "select * from bucket where user_id = ?",
                    new Object[]{user.getId().toString()},
                    (rs, rowNum) -> new Bucket(rs.getInt("id"), user)
            );
        } catch (Exception e) {
            log.error("Bucket getBucket exception: {}", e.getMessage());
        }

        return bucket;
    }

    @Transactional
    protected void createBucket(User user) {
        jdbcTemplate.update("insert into bucket (user_id) values (?)", user.getId().toString());
    }

    @Transactional
    public boolean addProduct(Product product, Integer count) {
        checkInput(product, count);

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

        return  warehouseRepository.reduceCountProducts(product.getId(), count);
    }

    @Transactional
    protected void insertProduct(Integer productId, Integer count) {
        Bucket bucket = getOrCreateBucket();

        jdbcTemplate.update(
                "insert into bucket_product (bucket_id, product_id, count) values (?, ?, ?)",
                bucket.getId(),
                productId,
                count
        );
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    @Transactional
    public void removeProduct(Product product, Integer count) {
        checkInput(product, count);

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
    }

    @Transactional
    protected void updateCountProduct(Integer productId, Integer count) {
        Bucket bucket = getOrCreateBucket();
        jdbcTemplate.update(
                "update bucket_product set count = ? where bucket_id = ? and product_id = ?",
                count,
                bucket.getId(),
                productId
        );
    }

    @Transactional
    protected void increaseCountProduct(Integer productId, Integer count) {
        Bucket bucket = getOrCreateBucket();
        jdbcTemplate.update(
                "update bucket_product set count = count + ? where bucket_id = ? and product_id = ?",
                count,
                bucket.getId(),
                productId
        );
    }

    @Transactional
    protected void deleteFromBucket(Integer productId) {
        Bucket bucket = getOrCreateBucket();
        jdbcTemplate.update(
                "delete from bucket_product where bucket_id = ? and product_id = ?",
                bucket.getId(),
                productId
        );
    }

    public void removeProduct(Product product) {
        removeProduct(product, 1);
    }

    @Transactional
    public void clear() {
        Bucket bucket = getOrCreateBucket();
        jdbcTemplate.update("delete from bucket_product where bucket_id = ?", bucket.getId());
    }

    @Transactional
    public Map<Product, Integer> getAll() {
        Bucket bucket = getOrCreateBucket();

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

        return bucket.getProducts();
    }

    @Transactional
    public Product getById(Integer productId) {
        Product product = null;

        try {
            Bucket bucket = getOrCreateBucket();
            product = jdbcTemplate.queryForObject(
                    "select * from bucket_product where bucket_id = ? and product_id = ?",
                    new Object[] {bucket.getId(), productId},
                    (rs, rowNum) -> productRepository.getById(rs.getInt("product_id"))
            );
        } catch (Exception e) {
            log.error("Bucket get by id exception: {}", e.getMessage());
        }

        return  product;
    }

    @Transactional
    public Integer countProducts() {
        Bucket bucket = getOrCreateBucket();

        return jdbcTemplate.queryForObject(
                "select count(product_id) as quantity from bucket_product where bucket_id = ?",
                new Object[] {bucket.getId()},
                (rs, rowNum) -> rs.getInt("quantity")
        );
    }

    @Transactional
    public Integer countItems() {
        Bucket bucket = getOrCreateBucket();

        return jdbcTemplate.queryForObject(
                "select sum(count) as quantity from bucket_product where bucket_id = ?",
                new Object[] {bucket.getId()},
                (rs, numRows) -> rs.getInt("quantity")
        );
    }

    public boolean isEmpty() {
        return countItems() == 0;
    }

    @Transactional
    public BigDecimal calculateTotal(Currency currency) {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> pair : getAll().entrySet()) {
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
