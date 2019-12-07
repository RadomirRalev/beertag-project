package com.beertag.demo.models.beer;

public class Brewery {
    private int id;
    private String name;

    public Brewery() {

    }

    public Brewery(String name) {
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
