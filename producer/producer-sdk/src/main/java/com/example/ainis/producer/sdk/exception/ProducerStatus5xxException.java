package com.example.ainis.producer.sdk.exception;

public class ProducerStatus5xxException extends Exception {
    public ProducerStatus5xxException(String message, Throwable cause) {
        super(message, cause);
    }
}
