package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Brewery;
import com.beertag.demo.repositories.BeerRepository;
import com.beertag.demo.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.constants.ExceptionConstants.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

@Service
public class BreweryServiceImpl implements BreweryService {
    private BreweryRepository breweryRepository;
    private BeerRepository beerRepository;

    @Autowired
    public BreweryServiceImpl(BreweryRepository breweryRepository, BeerRepository beerRepository) {
        this.breweryRepository = breweryRepository;
        this.beerRepository = beerRepository;
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
    public List<Beer> getBeersByBreweryId(int breweryId) {
        return beerRepository.getBeersByBreweryId(breweryId);
    }

    @Override
    public List<Brewery> getBreweryByName(String name) {
        try {
            return breweryRepository.getBreweryByName(name);
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
        newBrewery.setStatus(ENABLE);
        try {
           return breweryRepository.createBrewery(newBrewery);
        } catch (Exception e) {
            throw new DuplicateEntityException(BREWERY_NAME_EXISTS, newBrewery.getName());
        }
    }

    @Override
    public void deleteBrewery(int id) {
         breweryRepository.deleteBrewery(id);
    }
}