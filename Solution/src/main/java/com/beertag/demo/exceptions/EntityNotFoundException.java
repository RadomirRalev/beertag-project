package com.beertag.demo.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String itemName, String itemType) {
        super(String.format("We didn't found a beer with %s %s.", itemName, itemType));
    }

    public EntityNotFoundException(String itemType, int id) {
        this(itemType, "id", String.valueOf(id));
    }

    public EntityNotFoundException(String itemType, String attribute, String value) {
        super(String.format("%s with %s %s not found.", itemType, attribute, value));
    }
}

