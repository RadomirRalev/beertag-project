package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Brewery;
import com.beertag.demo.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryServiceImpl implements BreweryService {
    private BreweryRepository breweryRepository;

    @Autowired
    public BreweryServiceImpl(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    @Override
    public Brewery getBreweryById(int id) {
        return breweryRepository.getBreweryById(id);
    }

    @Override
    public List<Brewery> getBreweriesList() {
        return breweryRepository.getBreweryList();
    }

    @Override
    public Brewery getSpecificBrewery(String name) {
        try {
            return breweryRepository.getSpecificBrewery(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException (String.format("Brewery %s not found in the database", name));
        }
    }

    @Override
    public Brewery update(int id, Brewery brewery) {
        breweryRepository.update(id, brewery);
        return brewery;
    }

    @Override
    public void createBrewery(Brewery newBrewery) {
        if (breweryRepository.checkBreweryExists(newBrewery.getName())) {
            throw new DuplicateEntityException("Brewery", newBrewery.getName());
        }
        breweryRepository.createBrewery(newBrewery);
    }

    @Override
    public void deleteBrewery(String name) {
        if (breweryRepository.checkBreweryExists(name)) {
            throw new EntityNotFoundException(String.format("Brewery %s does not exist.", name));
        }
        breweryRepository.deleteBrewery(name);
    }
}
