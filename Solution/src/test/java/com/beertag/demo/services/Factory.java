package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Brewery;

public class Factory {
    public static final int INDEX = 0;
    public static final String NAME = "X";


    public static Beer createBeer(){
        return new Beer("Zagorka", "okok", "2.14", "pop");
    }

    public static Brewery createBrewery(){
        return new Brewery("Zagorka");
    }
}
