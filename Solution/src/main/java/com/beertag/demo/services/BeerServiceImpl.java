package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Beer;
import com.beertag.demo.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new EntityNotFoundException("Beer", id);
        }
    }

    @Override
    public List<Beer> getBeersList() {
        return repository.getBeerList();
    }

    @Override
    public Beer getSpecificBeer(String name) {
        try {
            return repository.getSpecificBeer(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(name, "name");
        }
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        try {
            repository.createBeer(newBeer);
            return newBeer;
        } catch (Exception ex) {
            throw new DuplicateEntityException(newBeer.getName());
        }
    }

    @Override
    public void deleteBeer(String name) {
        if (repository.checkBeerExists(name)) {
            throw new EntityNotFoundException(name, "name");
        }
        repository.deleteBeer(name);
    }

    @Override
    public Beer update(int id, Beer beerToBeUpdated) {
        repository.update(id, beerToBeUpdated);
        return beerToBeUpdated;
    }
}