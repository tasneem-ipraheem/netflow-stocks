package com.netflow.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableJpaRepositories("com.netflow.stocks")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}