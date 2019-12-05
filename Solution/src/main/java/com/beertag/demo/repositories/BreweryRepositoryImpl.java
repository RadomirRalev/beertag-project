package com.beertag.demo.repositories;

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
    public List<Brewery> getBreweryList() {
        return breweriesList;
    }

    @Override
    public Brewery getSpecificBrewery(String name) {
        return getBrewery(name);
    }

    @Override
    public void update(int id, Brewery brewery) {
        for (int i = 0; i < breweriesList.size(); i++) {
            if (breweriesList.get(i).getId() == id) {
                breweriesList.set(i, brewery);
                break;
            }
        }
    }

    @Override
    public void createBrewery(Brewery newBrewery) {
        breweriesList.add(newBrewery);
    }

    @Override
    public boolean checkBreweryExists(String name) {
        return breweriesList.stream()
                .anyMatch(brewery -> brewery.getName().equals(name));
    }

    @Override
    public void deleteBrewery(String name) {
        Brewery breweryToBeRemoved = getBrewery(name);
        breweriesList.remove(breweryToBeRemoved);
    }

    private Brewery getBrewery(String name) {
        return breweriesList.stream()
                .filter(brewery -> brewery.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Brewery %s not found in the database", name)));
    }
}
