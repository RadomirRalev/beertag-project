package com.beertag.demo.repositories;

import com.beertag.demo.models.Brewery;

import java.util.List;

public interface BreweryRepository {
    Brewery getBreweryById(int id);

    List<Brewery> getBreweriesList();

    Brewery getSpecificBrewery(String name);

    void update(int id, Brewery brewery);

    void createBrewery(Brewery newBrewery);

    boolean checkBreweryExists(String name);

    void deleteBrewery(String name);

}
