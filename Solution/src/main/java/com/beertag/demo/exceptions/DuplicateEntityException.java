package com.beertag.demo.exceptions;

public class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super((message));
        }
}
