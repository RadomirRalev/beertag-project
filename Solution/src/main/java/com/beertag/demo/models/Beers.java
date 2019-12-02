package com.beertag.demo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Beers {
    @PositiveOrZero(message = "Id should be positive or zero")
    private int id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 symbols long")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Country of origin is mandatory")
    private String originCountry;
    @NotBlank(message = "Brewery is mandatory")
    private String brewery;
    @NotBlank(message = "Style is mandatory")
    private String style;
    @NotBlank(message = "ABV tag is mandatory")
    private String abvTag;
    @NotBlank(message = "Picture is mandatory")
    private String picture;
    @NotBlank(message = "Tag is mandatory")
    private String tag;

    public Beers() {

    }

    public Beers(int id, String name, String description, String originCountry,
                 String brewery, String style, String abvTag, String picture, String tag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.originCountry = originCountry;
        this.brewery = brewery;
        this.style = style;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
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
