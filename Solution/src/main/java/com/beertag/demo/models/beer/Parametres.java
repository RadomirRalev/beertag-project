package com.beertag.demo.models.beer;

public class Parametres {
    public String styleSearch;
    public String countrySearch;
    public String tagSearch;
    public String brewerySearch;
    public String alcoholSearch;

    public Parametres() {
    }

    public Parametres(String styleSearch, String countrySearch, String tagSearch, String brewerySearch, String alcoholSearch) {
        this.styleSearch = styleSearch;
        this.countrySearch = countrySearch;
        this.tagSearch = tagSearch;
        this.brewerySearch = brewerySearch;
        this.alcoholSearch = alcoholSearch;
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
}
