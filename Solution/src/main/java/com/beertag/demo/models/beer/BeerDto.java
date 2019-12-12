package com.beertag.demo.models.beer;

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
    private int originCountryId;
    @NotBlank(message = "Brewery is mandatory")
    @NotNull
    private int breweryId;

    private int styleId;
    @NotBlank
    @NotNull
    private int tagId;

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

    public int getOriginCountryId() {
        return originCountryId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setOriginCountryId(int setOriginCountryId) {
        this.originCountryId = originCountryId;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBrewery(int breweryId) {
        this.breweryId = breweryId;
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
