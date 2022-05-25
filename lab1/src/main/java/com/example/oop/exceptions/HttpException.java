package com.example.oop.exceptions;

public class HttpException extends RuntimeException {
    private final int httpCode;

    public HttpException(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
