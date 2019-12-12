package com.beertag.demo.models.beer;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Style {
    private int id;
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Style field may contain only letters.")
    private String name;

    public Style() {

    }

    public Style(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}