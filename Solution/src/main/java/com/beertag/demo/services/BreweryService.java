package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Brewery;

import java.util.List;

public interface BreweryService {
    Brewery getBreweryById(int id);

    List<Brewery> getBreweriesList();

    List<Brewery> getBreweryByName(String name);

    List<Beer> getBeersByBreweryId(int breweryId);

    Brewery update(int id, Brewery brewery);

    Brewery createBrewery(Brewery newBrewery);

    void deleteBrewery(int id);
}
