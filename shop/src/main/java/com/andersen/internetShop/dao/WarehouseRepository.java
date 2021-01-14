package com.andersen.internetShop.dao;

import com.andersen.internetShop.utils.ExpiryDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
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

    @Transactional
    public void addProduct(Product product, Integer count) {
        count = checkCount(count);
        checkExpiryDate(product);

        Product item = getById(product.getId());

        if (Objects.nonNull(item)) {
            increaseCountProducts(product.getId(), count);
        } else {
            insertProduct(product.getId(), count);
        }
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    @Transactional
    protected void insertProduct(Integer productId, Integer count) {
        checkCount(count);
        jdbcTemplate.update("insert into warehouse (product_id, count) values (?, ?)", productId, count);
    }

    @Transactional
    public Product getById(Integer productId, Integer count) {
        return jdbcTemplate.queryForObject(
                "select * from warehouse where product_id = ?",
                new Object[]{productId},
                (rs, rowNums) -> productRepository.getById(rs.getInt("product_id"))
        );
    }

    public Product getById(Integer id) {
        return getById(id, 1);
    }

    @Transactional
    public Integer countItems() {
        return jdbcTemplate.queryForObject(
                "select sum(count) as total from warehouse",
                (rs, i) -> rs.getInt("total")
        );
    }

    @Transactional
    public Integer countProducts() {
        return jdbcTemplate.queryForObject(
                "select count(*) as quantity from warehouse",
                (rs, i) -> rs.getInt("quantity")
        );
    }

    @Transactional
    public Integer countProductById(Integer productId) {
        return jdbcTemplate.queryForObject(
                "select count from warehouse where product_id = ?",
                new Object[]{productId},
                (rs, rowNum) -> rs.getInt("count")
        );
    }

    public boolean isEmpty() {
        return countItems() == 0;
    }

    private Integer checkCount(Integer count) {
        return count < 0 ? 0 : count;
    }

    @Transactional
    public Map<Product, Integer> getAll() {
        Map<Product, Integer> products = new LinkedHashMap<>();

        List<ProductCount> productCounts = jdbcTemplate.query(
                "select * from warehouse order by product_id",
                (rs, rowNum) -> new ProductCount(
                        getById(rs.getInt("product_id")),
                        rs.getInt("count")
                )
        );
        productCounts.forEach(pc -> products.put(pc.getProduct(), pc.getCount()));

        return products;
    }

    @Transactional
    public boolean update(Integer productId, Integer count) {
        int rows = jdbcTemplate.update("update warehouse set count = ? where product_id = ?", count, productId);

        return rows != 0;
    }

    @Transactional
    public boolean increaseCountProducts(Integer productId, Integer count) {
        int rows = jdbcTemplate.update(
                "update warehouse set count = (count + ?) where product_id = ?",
                count,
                productId
        );

        return rows != 0;
    }

    @Transactional
    public boolean reduceCountProducts(Integer productId, Integer count) {
        int rows = jdbcTemplate.update("update warehouse set count = count - ? where product_id = ?", count, productId);

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
