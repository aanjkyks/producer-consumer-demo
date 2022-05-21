package com.example.ainis.producer.sdk;

import com.example.ainis.producer.model.Greeting;
import com.example.ainis.producer.sdk.exception.ProducerStatus4xxException;
import com.example.ainis.producer.sdk.exception.ProducerStatus5xxException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

public class ProducerConsumer {

    private final WebClient webClient;
    private final String baseUrl;

    public ProducerConsumer(WebClient webClient, @Value("${rest.producer.base-url}") String baseUrl) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
    }

    public Greeting getGreeting() {
        var uri = UriComponentsBuilder.fromUriString(baseUrl)
                .pathSegment("api", "greeting")
                .build()
                .toUri();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new ProducerStatus4xxException("4xx status observed", response.createException().block())))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new ProducerStatus5xxException("5xx status observed", response.createException().block())))
                .bodyToMono(Greeting.class)
                .block();
    }
}
