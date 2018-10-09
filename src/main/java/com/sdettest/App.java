package com.sdettest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"com.sdettest"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}