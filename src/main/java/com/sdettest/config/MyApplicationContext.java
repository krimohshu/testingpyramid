package com.sdettest.config;

import com.sdettest.dto.Filter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class MyApplicationContext {
    @Bean
    public Filter filter(ApplicationContext context) {
        return new Filter();
    }
}
