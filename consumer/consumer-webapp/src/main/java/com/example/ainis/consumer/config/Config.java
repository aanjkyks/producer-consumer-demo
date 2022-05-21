package com.example.ainis.consumer.config;

import com.example.ainis.producer.sdk.ProducerConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Bean
    public ProducerConsumer producerConsumer(WebClient webClient, @Value("${rest.producer.base-url}") String baseUrl) {
        return new ProducerConsumer(webClient, baseUrl);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
