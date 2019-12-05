package com.beertag.demo.services;

import com.beertag.demo.models.Brewery;

import java.util.List;

public interface BreweryService {
    Brewery getBreweryById(int id);

    List<Brewery> getBreweriesList();

    Brewery getSpecificBrewery(String name);

    Brewery update(int id, Brewery brewery);

    void createBrewery(Brewery newBrewery);

    void deleteBrewery(String name);
}
