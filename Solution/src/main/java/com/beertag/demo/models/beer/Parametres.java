package com.beertag.demo.models.beer;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

public class Parametres {
    @Size(min = 2, max = 62, message = "Name must be between 2 and 32 characters long")
    private String nameParam;
    private double abvParam;
    private String descriptionParam;
    private String styleSearch;
    private String countrySearch;
    private String tagSearch;
    private String brewerySearch;
    private String alcoholSearch;
    private int beerId;
    private int styleParamId;
    private int countryParamId;
    private int breweryParamId;

    public Parametres() {
    }

    public Parametres(String styleSearch, String countrySearch, String tagSearch,
                      String brewerySearch, String alcoholSearch, String nameParam,
                      double abvParam, String descriptionParam, int styleParamId,
                      int countryParamId, int breweryParamId, int beerId) {
        this.styleSearch = styleSearch;
        this.countrySearch = countrySearch;
        this.tagSearch = tagSearch;
        this.brewerySearch = brewerySearch;
        this.alcoholSearch = alcoholSearch;
        this.nameParam = nameParam;
        this.abvParam = abvParam;
        this.descriptionParam = descriptionParam;
        this.styleParamId = styleParamId;
        this.countryParamId = countryParamId;
        this.breweryParamId = breweryParamId;
        this.beerId = beerId;
    }

    public String getStyleSearch() {
        return styleSearch;
    }

    public void setStyleSearch(String styleSearch) {
        this.styleSearch = styleSearch;
    }

    public String getCountrySearch() {
        return countrySearch;
    }

    public void setCountrySearch(String countrySearch) {
        this.countrySearch = countrySearch;
    }

    public String getTagSearch() {
        return tagSearch;
    }

    public void setTagSearch(String tagSearch) {
        this.tagSearch = tagSearch;
    }

    public String getBrewerySearch() {
        return brewerySearch;
    }

    public void setBrewerySearch(String brewerySearch) {
        this.brewerySearch = brewerySearch;
    }

    public String getAlcoholSearch() {
        return alcoholSearch;
    }

    public void setAlcoholSearch(String alcoholSearch) {
        this.alcoholSearch = alcoholSearch;
    }

    public String getNameParam() {
        return nameParam;
    }

    public void setNameParam(String name) {
        this.nameParam = name;
    }

    public double getAbvParam() {
        return abvParam;
    }

    public void setAbvParam(double abvParam) {
        this.abvParam = abvParam;
    }

    public String getDescriptionParam() {
        return descriptionParam;
    }

    public void setDescriptionParam(String descriptionParam) {
        this.descriptionParam = descriptionParam;
    }

    public int getStyleParamId() {
        return styleParamId;
    }

    public void setStyleParamId(int styleParamId) {
        this.styleParamId = styleParamId;
    }

    public int getCountryParamId() {
        return countryParamId;
    }

    public void setCountryParamId(int countryParamId) {
        this.countryParamId = countryParamId;
    }

    public int getBreweryParamId() {
        return breweryParamId;
    }

    public void setBreweryParamId(int breweryParamId) {
        this.breweryParamId = breweryParamId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }
}
