package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Tag;

import java.util.List;

public interface BeerService {

    Beer getById(int id);

    List<Beer> getBeersList();

    List<Beer> getBeerByName(String name);

    Beer createBeer(Beer newBeer);

    void deleteBeer(int id);

    Beer update(int id, Beer beerToBeUpdated);

    List<Beer> getBeersByStyleName(String styleName);

    List<Beer> getBeersByTagName(String tagName);

    List<Beer> getBeersByBreweryName(String breweryName);

    List<Beer> getBeersByOriginCountry(String originCountry);

    void updateAvgRatingOfBeer(int beerId);

    List<Tag> getTags(int id);
}
