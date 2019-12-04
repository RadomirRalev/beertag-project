package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Beers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BeersRepositoryImpl implements BeersRepository {
    private static int nextId = 0;
    private List<Beers> beersList;

    @Autowired
    public BeersRepositoryImpl(StylesRepository stylesRepository, CountriesRepository countriesRepository) {
        beersList = new ArrayList<>();
        Beers beer = new Beers("Zagorka", "kkkk",
                "okok", "2.14", "pop", "tag");
        beer.setId(BeersRepositoryImpl.nextId++);
        beer.setStyle(stylesRepository.getStyleById(0));
        beer.setOriginCountry(countriesRepository.getCountryById(0));
        beersList.add(beer);
        beer = new Beers("Shumensko", "dgfdgd",
                "okgsdgok", "3.12", "ads", "tag");
        beer.setId(BeersRepositoryImpl.nextId++);
        beer.setStyle(stylesRepository.getStyleById(1));
        beer.setOriginCountry(countriesRepository.getCountryById(1));
        beersList.add(beer);
        beer = new Beers("Pirinsko", "dsfdf",
                "qwerrr", "2.84", "arghhtr", "tag");
        beer.setId(BeersRepositoryImpl.nextId++);
        beer.setStyle(stylesRepository.getStyleById(2));
        beer.setOriginCountry(countriesRepository.getCountryById(2));
        beersList.add(beer);
    }

    @Override
    public Beers getById(int id) {
        return beersList.stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    @Override
    public List<Beers> getBeersList() {
        return beersList;
    }

    @Override
    public Beers getSpecificBeer(String name) {
        return getBeers(name);
    }

    @Override
    public void createBeer(Beers newBeer) {
        beersList.add(newBeer);
    }

    @Override
    public void deleteBeer(String name) {
        Beers beerToBeRemoved = getBeers(name);
        beersList.remove(beerToBeRemoved);
    }

    @Override
    public boolean checkBeerExists(String name) {
        return beersList.stream()
                .anyMatch(beer -> beer.getName().equals(name));
    }

    @Override
    public void update(int id, Beers beer) {
        Beers beerToUpdate = getById(id);
        int index = beersList.indexOf(beerToUpdate);
        beer.setId(beerToUpdate.getId());
        beer.setStyle(beerToUpdate.getStyle());
        beersList.set(index, beer);
    }

    private Beers getBeers(@PathVariable String name) {
        return beersList.stream()
                .filter(beers -> beers.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Beer %s not found in the database", name)));
    }
}