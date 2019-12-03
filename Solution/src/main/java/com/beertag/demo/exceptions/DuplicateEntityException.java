package com.beertag.demo.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String name) {
        super(String.format("Beer with name %s already exists.", name));
    }
}
