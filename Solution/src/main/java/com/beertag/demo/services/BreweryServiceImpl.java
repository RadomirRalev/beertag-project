package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Brewery;
import com.beertag.demo.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Service
public class BreweryServiceImpl implements BreweryService {
    private BreweryRepository breweryRepository;

    @Autowired
    public BreweryServiceImpl(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    @Override
    public Brewery getBreweryById(int id) {
        try {
            return breweryRepository.getBreweryById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException (BREWERY_ID_NOT_FOUND, id);
        }
    }

    @Override
    public List<Brewery> getBreweriesList() {
        try {
            return breweryRepository.getBreweriesList();
        } catch (Exception e) {
            throw new EntityNotFoundException (LIST_EMPTY);
        }
    }

    @Override
    public Brewery getSpecificBrewery(String name) {
        try {
            return breweryRepository.getSpecificBrewery(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException (BREWERY_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Brewery update(int id, Brewery brewery) {
        try {
            breweryRepository.update(id, brewery);
            return brewery;
        } catch (Exception e) {
            throw new EntityNotFoundException (BREWERY_ID_NOT_FOUND, id);
        }
    }

    @Override
    public Brewery createBrewery(Brewery newBrewery) {
        try {
            breweryRepository.createBrewery(newBrewery);
            return newBrewery;
        } catch (Exception e) {
            throw new DuplicateEntityException(BREWERY_NAME_EXISTS, newBrewery.getName());
        }
    }

    @Override
    public void deleteBrewery(String name) {
        try {
            breweryRepository.deleteBrewery(name);
        } catch (Exception e) {
            throw new EntityNotFoundException (BREWERY_NAME_NOT_FOUND, name);
        }
    }
}
