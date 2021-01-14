package com.andersen.internetShop.config;

import com.andersen.internetShop.dao.UserRepository;
import com.andersen.internetShop.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        .anyRequest()
                .authenticated()
                .and()
                .formLogin();

//        http.csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/auth", "/registration").permitAll()
//                    .antMatchers("/*").hasRole(Role.USER.getAuthority())
//            .and()
//                .formLogin()
//                    .usernameParameter("login")
//                    .passwordParameter("password")
//                    .loginPage("/auth")
//                    .loginProcessingUrl("/login")
//                    .successForwardUrl("/main")
//            .and()
//                .logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/auth");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() {
        return new CustomUserDetailsService(userRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.parentAuthenticationManager(new ProviderManager(daoAuthenticationProvider()))
//                .userDetailsService(userDetailsService());

        auth.authenticationProvider(daoAuthenticationProvider());

//        auth.jdbcAuthentication().
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());

        return daoAuthenticationProvider;
    }
}
