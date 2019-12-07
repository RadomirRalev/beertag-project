package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Brewery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Repository
public class BreweryRepositoryImpl implements BreweryRepository {
    private static int breweryID;
    private List<Brewery> breweriesList;

    public BreweryRepositoryImpl() {
        breweriesList = new ArrayList<>();
        Brewery brewery = new Brewery("Anheuser-Busch InBev");
        brewery.setId(BreweryRepositoryImpl.breweryID++);
        breweriesList.add(brewery);
        brewery = new Brewery("Heineken");
        brewery.setId(BreweryRepositoryImpl.breweryID++);
        breweriesList.add(brewery);
        brewery = new Brewery("Molson Coors Brewing");
        brewery.setId(BreweryRepositoryImpl.breweryID++);
        breweriesList.add(brewery);
    }

    @Override
    public Brewery getBreweryById(int id) {
        return breweriesList.stream()
                .filter(brewery -> brewery.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(BREWERY_ID_NOT_FOUND, id));
    }

    @Override
    public List<Brewery> getBreweriesList() {
        try {
            return breweriesList;
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public Brewery getSpecificBrewery(String name) {
        try {
            return getBrewery(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(BREWERY_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Brewery update(int id, Brewery brewery) {
        for (int i = 0; i < breweriesList.size(); i++) {
            if (breweriesList.get(i).getId() == id) {
                breweriesList.set(i, brewery);
                return brewery;
            }
            if (i == breweriesList.size() - 1) {
                throw new EntityNotFoundException(BREWERY_NAME_NOT_FOUND, brewery.getName());
            }
        }
        return brewery;
    }

    @Override
    public Brewery createBrewery(Brewery newBrewery) {
        try {
            breweriesList.add(newBrewery);
            return newBrewery;
        } catch (Exception e) {
            throw new DuplicateEntityException(BREWERY_NAME_EXISTS, newBrewery.getName());
        }
    }

    @Override
    public boolean checkBreweryExists(String name) {
        return breweriesList.stream()
                .anyMatch(brewery -> brewery.getName().equals(name));
    }

    @Override
    public void deleteBrewery(String name) {
        try {
            Brewery breweryToBeRemoved = getBrewery(name);
            breweriesList.remove(breweryToBeRemoved);
        } catch (Exception e) {
            throw new EntityNotFoundException(BREWERY_NAME_NOT_FOUND, name);
        }
    }

    private Brewery getBrewery(String name) {
        return breweriesList.stream()
                .filter(brewery -> brewery.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(BREWERY_NAME_NOT_FOUND, name)));
    }
}
