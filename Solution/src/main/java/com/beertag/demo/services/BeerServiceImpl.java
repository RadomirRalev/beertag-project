package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Service
public class BeerServiceImpl implements BeerService {
    private BeerRepository repository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Beer getById(int id) {
        try {
            return repository.getById(id);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(BEER_ID_NOT_FOUND, id);
        }
    }

    @Override
    public List<Beer> getBeersList() {
        try {
            return repository.getBeerList();
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public Beer getSpecificBeer(String name) {
        try {
            return repository.getSpecificBeer(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(BEER_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        try {
            return repository.createBeer(newBeer);
        } catch (Exception ex) {
            throw new DuplicateEntityException(BEER_NAME_EXISTS, newBeer.getName());
        }
    }

    @Override
    public void deleteBeer(String name) {
        try {
            repository.deleteBeer(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(BEER_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Beer update(int id, Beer beerToBeUpdated) {
        try {
            return repository.update(id, beerToBeUpdated);
        } catch (Exception e) {
            throw new EntityNotFoundException(BEER_ID_NOT_FOUND, id);
        }
    }
}