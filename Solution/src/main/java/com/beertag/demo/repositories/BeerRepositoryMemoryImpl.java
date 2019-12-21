package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.models.Constants.BEER_ID_NOT_FOUND;
import static com.beertag.demo.models.Constants.BEER_NAME_NOT_FOUND;
import static com.beertag.demo.models.Constants.BEER_NAME_EXISTS;


//@Repository
public class BeerRepositoryMemoryImpl implements BeerRepository {
    private static int nextId = 0;
    private List<Beer> beerList;

    @Autowired
    public BeerRepositoryMemoryImpl(StyleRepository styleRepository,
                                    CountryRepository countryRepository,
                                    BreweryRepository breweryRepository, TagRepository tagRepository) {
        beerList = new ArrayList<>();
        Beer beer = new Beer("Zagorka","okok", "2.14", "pop");
        beer.setId(BeerRepositoryMemoryImpl.nextId++);
        beer.setStyle(styleRepository.getStyleById(0));
        beer.setOriginCountry(countryRepository.getCountryById(0));
        beer.setBrewery(breweryRepository.getBreweryById(0));
        beer.setTag(tagRepository.getTagById(0));
        beerList.add(beer);
        beer = new Beer("Shumensko","okgsdgok", "3.12", "ads");
        beer.setId(BeerRepositoryMemoryImpl.nextId++);
        beer.setStyle(styleRepository.getStyleById(1));
        beer.setOriginCountry(countryRepository.getCountryById(1));
        beer.setBrewery(breweryRepository.getBreweryById(1));
        beer.setTag(tagRepository.getTagById(1));
        beerList.add(beer);
        beer = new Beer("Pirinsko", "qwerrr", "2.84", "arghhtr");
        beer.setId(BeerRepositoryMemoryImpl.nextId++);
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
        return getBeer(name);
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        try {
            beerList.add(newBeer);
        } catch (Exception e) {
            throw new DuplicateEntityException(BEER_NAME_EXISTS, newBeer.getName());
        }
        return newBeer;
    }

    @Override
    public void deleteBeer(String name) {
        try {
            Beer beerToBeRemoved = getBeer(name);
            beerList.remove(beerToBeRemoved);
        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format(BEER_NAME_NOT_FOUND, name));
        }
    }

    @Override
    public boolean checkBeerExists(String name) {
        return beerList.stream()
                .anyMatch(beer -> beer.getName().equals(name));
    }

    @Override
    public Beer update(int id, Beer beer) {
        try {
            Beer beerToUpdate = getById(id);
            int index = beerList.indexOf(beerToUpdate);
            beer.setId(beerToUpdate.getId());
            beer.setStyle(beerToUpdate.getStyle());
            beerList.set(index, beer);
        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format(BEER_ID_NOT_FOUND, id));
        }
        return beer;
    }

    private Beer getBeer(@PathVariable String name) {
        return beerList.stream()
                .filter(beers -> beers.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(BEER_NAME_NOT_FOUND, name)));
    }
}