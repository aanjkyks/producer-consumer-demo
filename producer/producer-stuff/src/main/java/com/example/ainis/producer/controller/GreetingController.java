package com.example.ainis.producer.controller;

import com.example.ainis.producer.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting greeting(){
        return new Greeting("Hello from producer");
    }
}
