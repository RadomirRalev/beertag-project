package com.beertag.demo.services;

import com.beertag.demo.models.Beers;

import java.util.List;

public interface BeersService {

    Beers getById(int id);

    List<Beers> getBeersList();

    Beers getSpecificBeer(String name);

    List<Beers> filterBeers(String filterType, String filterQuery);

    void createBeer(Beers newBeer);

    void deleteBeer(String name);

    List<Beers> sortEntries(String sortType);
}
