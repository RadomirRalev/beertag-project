package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    private static int nextId = 0;
    private List<Beer> beerList;

    @Autowired
    public BeerRepositoryImpl(StyleRepository styleRepository,
                              CountryRepository countryRepository,
                              BreweryRepository breweryRepository, TagRepository tagRepository) {
        beerList = new ArrayList<>();
        Beer beer = new Beer("Zagorka","okok", "2.14", "pop");
        beer.setId(BeerRepositoryImpl.nextId++);
        beer.setStyle(styleRepository.getStyleById(0));
        beer.setOriginCountry(countryRepository.getCountryById(0));
        beer.setBrewery(breweryRepository.getBreweryById(0));
        beer.setTag(tagRepository.getTagById(0));
        beerList.add(beer);
        beer = new Beer("Shumensko","okgsdgok", "3.12", "ads");
        beer.setId(BeerRepositoryImpl.nextId++);
        beer.setStyle(styleRepository.getStyleById(1));
        beer.setOriginCountry(countryRepository.getCountryById(1));
        beer.setBrewery(breweryRepository.getBreweryById(1));
        beer.setTag(tagRepository.getTagById(1));
        beerList.add(beer);
        beer = new Beer("Pirinsko", "qwerrr", "2.84", "arghhtr");
        beer.setId(BeerRepositoryImpl.nextId++);
        beer.setStyle(styleRepository.getStyleById(2));
        beer.setOriginCountry(countryRepository.getCountryById(2));
        beer.setBrewery(breweryRepository.getBreweryById(2));
        beer.setTag(tagRepository.getTagById(2));
        beerList.add(beer);
    }

    @Override
    public Beer getById(int id) {
        return beerList.stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    @Override
    public List<Beer> getBeerList() {
        return beerList;
    }

    @Override
    public Beer getSpecificBeer(String name) {
        return getBeers(name);
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        beerList.add(newBeer);
        return newBeer;
    }

    @Override
    public void deleteBeer(String name) {
        Beer beerToBeRemoved = getBeers(name);
        beerList.remove(beerToBeRemoved);
    }

    @Override
    public boolean checkBeerExists(String name) {
        return beerList.stream()
                .anyMatch(beer -> beer.getName().equals(name));
    }

    @Override
    public Beer update(int id, Beer beer) {
        Beer beerToUpdate = getById(id);
        int index = beerList.indexOf(beerToUpdate);
        beer.setId(beerToUpdate.getId());
        beer.setStyle(beerToUpdate.getStyle());
        beerList.set(index, beer);
        return beer;
    }

    private Beer getBeers(@PathVariable String name) {
        return beerList.stream()
                .filter(beers -> beers.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Beer %s not found in the database", name)));
    }
}