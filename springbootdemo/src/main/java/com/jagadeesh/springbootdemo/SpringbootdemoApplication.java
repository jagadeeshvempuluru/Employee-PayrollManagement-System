package com.jagadeesh.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
    "com.jagadeesh.springbootdemo",
    "com.payroll.controller",
    "com.payroll.service",
    "com.payroll.security"
})
@EntityScan(basePackages = {"com.payroll.model"})
@EnableJpaRepositories(basePackages = {"com.payroll.repository"})
public class SpringbootdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }
}