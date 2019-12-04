package com.beertag.demo.repositories;

import com.beertag.demo.models.Beers;
import java.util.List;

public interface BeersRepository {
    Beers getById(int id);

    List<Beers> getBeersList();

    Beers getSpecificBeer(String name);

    void createBeer(Beers newBeer);

    void deleteBeer(String name);

    boolean checkBeerExists(String name);

    void update(int id, Beers beerToUpdate);
}
