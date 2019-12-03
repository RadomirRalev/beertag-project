package com.beertag.demo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class BeerDto {
    @PositiveOrZero(message = "Id should be positive or zero")
    @NotNull
    private int id;
    @NotBlank(message = "Name is mandatory")
    @NotNull
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 symbols long")
    private String name;
    @NotBlank(message = "Description is mandatory")
    @NotNull
    private String description;
    @NotBlank(message = "Country of origin is mandatory")
    @NotNull
    private String originCountry;
    @NotBlank(message = "Brewery is mandatory")
    @NotNull
    private String brewery;

    private int styleId;

    @NotBlank(message = "ABV tag is mandatory")
    @NotNull
    private String abvTag;
    @NotBlank(message = "Picture is mandatory")
    @NotNull
    private String picture;
    @NotBlank(message = "Tag is mandatory")
    @NotNull
    private String tag;

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

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
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
