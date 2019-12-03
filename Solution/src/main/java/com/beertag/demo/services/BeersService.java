package com.beertag.demo.services;

import com.beertag.demo.models.Beers;

import java.util.List;

public interface BeersService {

    Beers getById(int id);

    List<Beers> getBeersList();

    Beers getSpecificBeer(String name);

    void createBeer(Beers newBeer);

    void deleteBeer(String name);

    List<Beers> sortEntries(String sortType);

    Beers update(int id, Beers beerToBeUpdated);
}
