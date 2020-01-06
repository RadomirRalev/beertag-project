package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Tag;

import java.util.List;

public interface BeerRepository {
    Beer getById(int id);

    List<Beer> getBeerList();

    List<Beer> getBeerByName(String name);

    List<Beer> getBeersByStyleId(int styleId);

    List<Beer> getBeersByCountryId(int styleId);

    List<Beer> getBeersByBreweryId(int countryId);

    Beer createBeer(Beer newBeer);

    void deleteBeer(int id);

    List<Beer> getBeersByBreweryName(String breweryName);

    List<Beer> getBeersByOriginCountry(String originCountry);

    boolean checkBeerExists(String name);

    Beer update(int id, Beer beerToUpdate);

    List<Beer> getBeersByStyleName(String styleName);

    void updateAvgRating(int beerId, double avgRating);

    List<Tag> getBeerTags(int id);
}
