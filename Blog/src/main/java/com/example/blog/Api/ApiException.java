package com.example.blog.Api;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
