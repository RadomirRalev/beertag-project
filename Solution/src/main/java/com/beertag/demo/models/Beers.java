package com.beertag.demo.models;

public class Beers {
    private int id;
    private String name;
    private String description;
    private String originCountry;
    private String brewery;
    private Style style;
    private String abvTag;
    private String picture;
    private String tag;

    public Beers() {

    }

    public Beers(String name, String description, String originCountry,
                 String brewery, String abvTag, String picture, String tag) {
        this.name = name;
        this.description = description;
        this.originCountry = originCountry;
        this.brewery = brewery;
        this.abvTag = abvTag;
        this.picture = picture;
        this.tag = tag;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getAbvTag() {
        return abvTag;
    }

    public void setAbvTag(String abvTag) {
        this.abvTag = abvTag;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}