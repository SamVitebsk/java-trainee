package com.andersen.internetShop.config;

import com.andersen.internetShop.dao.*;
import com.andersen.internetShop.service.AuthService;
import com.andersen.internetShop.service.BucketService;
import com.andersen.internetShop.service.OrderService;
import com.andersen.internetShop.service.WarehouseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:app.properties")
public class ApplicationConfig {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository(jdbcTemplate());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository(jdbcTemplate());
    }

    @Bean
    public WarehouseRepository warehouseRepository() {
        return new WarehouseRepository(productRepository(), jdbcTemplate());
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository(jdbcTemplate());
    }

    @Bean
    public BucketRepository bucketRepository() {
        return new BucketRepository(
                productRepository(),
                warehouseRepository(),
                authService(),
                jdbcTemplate()
        );
    }

    @Bean
    public AuthService authService() {
        return new AuthService(userRepository());
    }

    @Bean
    public BucketService bucketService() {
        return new BucketService(warehouseService() ,bucketRepository());
    }

    @Bean
    public WarehouseService warehouseService() {
        return new WarehouseService(warehouseRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(orderRepository());
    }
}
