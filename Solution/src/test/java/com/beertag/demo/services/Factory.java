package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.repositories.BeersRepository;

public class Factory {
    public static final int INDEX_OF_ARRAYLIST_ELEMENT = 0;
    public static final String NAME_OF_BEER = "X";


    public static Beers createBeer(){
        return new Beers("Zagorka", "okok", "2.14", "pop", "tag");
    }
}
