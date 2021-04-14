package com.revature.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for WebBuilder that allows the API to communicate
 * with the load balancer and other services.
 */
@Configuration
public class WebBuilderConfig {

    // bean to be used in production
    @Bean
    @LoadBalanced
    @Profile("default")
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    // bean to be used for testing
    @Bean
    @Profile("test")
    public WebClient.Builder nonloadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}
