package com.netflow.stocks;

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableJpaRepositories("com.netflow.stocks")
@EnableScheduling
@EnableTransactionManagement
public class Application {

    @Bean
    public RestTemplate yahooRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Lists.newArrayList(new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }

    @Bean
    public RestTemplate yahooLookupRestTemplate(){
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setMessageConverters(Lists.newArrayList(new MappingJackson2XmlHttpMessageConverter()));
        return restTemplate;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(500);
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}