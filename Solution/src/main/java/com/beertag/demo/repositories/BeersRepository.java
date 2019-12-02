package com.beertag.demo.repositories;


import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;

import java.util.List;
import java.util.function.Function;

public interface BeersRepository {
    Beers getById(int id);

    List<Beers> getBeersList();

    Beers getSpecificBeer(String name);

    List<Beers> filterBeers(String filterType, String filterQuery);

    void createBeer(Beers newBeer);

    void deleteBeer(String name);

    List<Beers> sortEntries(String sortType);

    boolean checkBeerExists(String name);
}
