package com.andersen.internetShop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class WebConfig extends AbstractSecurityWebApplicationInitializer {
    public WebConfig() {
        super(SecurityConfig.class);
    }
}
