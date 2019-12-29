package com.beertag.demo.models.beer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class BeerDto {
    private String name;
    private String description;
    private int originCountryId;
    private int breweryId;
    private int styleId;
    private int tagId;
    private double abvTag;
    private double avgRating;
    private byte[] picture;
    private String tag;

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

    public double getAbvTag() {
        return abvTag;
    }

    public void setAbvTag(double abvTag) {
        this.abvTag = abvTag;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}
