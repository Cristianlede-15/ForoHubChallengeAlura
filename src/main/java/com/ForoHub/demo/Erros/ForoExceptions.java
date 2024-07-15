package com.ForoHub.demo.Erros;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

public class ForoExceptions extends RuntimeException{
    private final HttpStatus status;

    public ForoExceptions(String message) {
        super(message);
        this.status = CONFLICT;
    }

    public HttpStatus getStatus() {
        return status;
    }
}