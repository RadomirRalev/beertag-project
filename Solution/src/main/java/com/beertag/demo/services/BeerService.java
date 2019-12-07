package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;

import java.util.List;

public interface BeerService {

    Beer getById(int id);

    List<Beer> getBeersList();

    Beer getSpecificBeer(String name);

    Beer createBeer(Beer newBeer);

    void deleteBeer(String name);

    Beer update(int id, Beer beerToBeUpdated);

}
