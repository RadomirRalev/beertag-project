package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Beers;
import com.beertag.demo.repositories.BeersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeersServiceImpl implements BeersService {
    private BeersRepository repository;

    @Autowired
    public BeersServiceImpl(BeersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Beers getById(int id) {
        try {
            return repository.getById(id);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Beer", id);
        }
    }

    @Override
    public List<Beers> getBeersList() {
        return repository.getBeersList();
    }

    @Override
    public Beers getSpecificBeer(String name) {
        try {
            return repository.getSpecificBeer(name);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(name, "name");
        }
    }

    @Override
    public void createBeer(Beers newBeer) {
        if (repository.checkBeerExists(newBeer.getName())) {
            throw new DuplicateEntityException(newBeer.getName());
        }
        repository.createBeer(newBeer);
    }

    @Override
    public void deleteBeer(String name) {
        if (repository.checkBeerExists(name)) {
            throw new EntityNotFoundException(name, "name");
        }
        repository.deleteBeer(name);
    }

    @Override
    public Beers update(int id, Beers beerToBeUpdated) {
        repository.update(id, beerToBeUpdated);
        return beerToBeUpdated;
    }
}