package com.beertag.demo.models.beer;

public class Parametres {
    String brewery;
    String tag;
    String country;
    String style;

    public Parametres() {
    }

    public Parametres(String style, String brewery, String tag, String country) {
        this.style = style;
        this.brewery = brewery;
        this.tag = tag;
        this.country = country;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
