package com.maleki.narges.msscbeerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("local-discovery")
public class RestTemplateConfig {



    @Bean("lbRestTemplate")
    @LoadBalanced
    public RestTemplate lbRestTemplate(RestTemplateBuilder restTemplateBuilder,
                                       @Value("${inventory-credentials.userName}") String userName,
                                       @Value("${inventory-credentials.password}") String password
                                       ) {

        return restTemplateBuilder
                .basicAuthentication(userName,password)
                .build();
    }
}
