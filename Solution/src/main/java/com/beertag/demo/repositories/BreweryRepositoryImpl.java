package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Brewery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
                .orElseThrow(() -> new EntityNotFoundException("Brewery", id));
    }

    @Override
    public List<Brewery> getBreweriesList() {
        return breweriesList;
    }

    @Override
    public Brewery getSpecificBrewery(String name) {
        try {
            return getBrewery(name);
        } catch (Exception e) {
            throw new EntityNotFoundException("Brewery", name);
        }
    }

    @Override
    public void update(int id, Brewery brewery) {
        for (int i = 0; i < breweriesList.size(); i++) {
            if (breweriesList.get(i).getId() == id) {
                breweriesList.set(i, brewery);
                break;
            }
            if (i == breweriesList.size() - 1) {
                throw new EntityNotFoundException("Brewery", id);
            }
        }
    }

    @Override
    public void createBrewery(Brewery newBrewery) {
        try {
            breweriesList.add(newBrewery);
        } catch (Exception e) {
            throw new DuplicateEntityException(newBrewery.getName());
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
            throw new EntityNotFoundException("Brewery", name);
        }
    }

    private Brewery getBrewery(String name) {
        return breweriesList.stream()
                .filter(brewery -> brewery.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Brewery %s not found in the database", name)));
    }
}
