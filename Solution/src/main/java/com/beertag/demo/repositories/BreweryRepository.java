package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Brewery;

import java.util.List;

public interface BreweryRepository {
    Brewery getBreweryById(int id);

    List<Brewery> getBreweriesList();

    List<Brewery> getBreweryByName(String name);

    Brewery update(int id, Brewery brewery);

    Brewery createBrewery(Brewery newBrewery);

    boolean checkBreweryExists(String name);

    void deleteBrewery(int id);
}
