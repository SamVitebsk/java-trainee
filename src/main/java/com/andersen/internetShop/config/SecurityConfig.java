package com.andersen.internetShop.config;

import com.andersen.internetShop.dao.*;
import com.andersen.internetShop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:app.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());
        super.configure(auth);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/auth", "/registration").permitAll()
                    .antMatchers("/**").hasAuthority(Role.USER.getAuthority())
            .and()
                .formLogin()
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .loginPage("/auth")
                    .loginProcessingUrl("/login")
                    .successHandler(successHandler())
//                    .successForwardUrl("/main")
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/auth");
    }


    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (req, resp, authentication) -> resp.sendRedirect("/main");
    }

    @Bean
    public UserDetailsService userDetailsServiceBean() {
        return new CustomUserDetailsService(userRepository());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
    public UserRepository userRepository() {
        return new UserRepository(jdbcTemplate(), passwordEncoder());
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository(jdbcTemplate());
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