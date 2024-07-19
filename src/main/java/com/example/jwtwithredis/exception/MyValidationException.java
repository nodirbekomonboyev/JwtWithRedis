package com.example.jwtwithredis.exception;

public class MyValidationException extends RuntimeException {
    public MyValidationException(String message) {
        super(message);
    }
}
