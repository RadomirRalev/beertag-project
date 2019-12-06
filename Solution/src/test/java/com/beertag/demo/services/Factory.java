package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.Brewery;
import com.beertag.demo.repositories.BeersRepository;

public class Factory {
    public static final int INDEX = 0;
    public static final String NAME = "X";


    public static Beers createBeer(){
        return new Beers("Zagorka", "okok", "2.14", "pop");
    }

    public static Brewery createBrewery(){
        return new Brewery("Zagorka");
    }
}
