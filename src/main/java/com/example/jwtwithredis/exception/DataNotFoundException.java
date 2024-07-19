package com.example.jwtwithredis.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
