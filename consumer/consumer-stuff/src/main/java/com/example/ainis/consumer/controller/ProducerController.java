package com.example.ainis.consumer.controller;

import com.example.ainis.producer.model.Greeting;
import com.example.ainis.producer.sdk.ProducerConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {

    private final ProducerConsumer consumer;

    public ProducerController(ProducerConsumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping("/greeting")
    public Greeting getGreeting() {
        return consumer.getGreeting();
    }
}
