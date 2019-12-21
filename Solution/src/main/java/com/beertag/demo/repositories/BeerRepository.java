package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import java.util.List;

public interface BeerRepository {
    Beer getById(int id);

    List<Beer> getBeerList();

    List<Beer> getBeerByName(String name);

    Beer createBeer(Beer newBeer);

    void deleteBeer(String name);

    boolean checkBeerExists(String name);

    Beer update(int id, Beer beerToUpdate);
}
