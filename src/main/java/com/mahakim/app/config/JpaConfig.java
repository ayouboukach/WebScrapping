package com.mahakim.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "com.mahakim.app.repos" })
@ComponentScan(basePackages = "com.mahakim.app.repos")
//@EntityScan(basePackages = "com.mahakim.app.entities")
public class JpaConfig {

}