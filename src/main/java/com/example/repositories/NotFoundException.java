package com.example.repositories;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}