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
        return repository.getById(id);
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
            throw new EntityNotFoundException(String.format("Beer with name %s already exists", name));
        }
    }

    @Override
    public List<Beers> filterBeers(String filterType, String filterQuery) {
        try {
            return repository.filterBeers(filterType, filterQuery);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(String.format("We didn't found a beer with %s property", filterQuery));
        }
    }

    @Override
    public void createBeer(Beers newBeer) {
        if (repository.checkBeerExists(newBeer.getName())) {
            throw new DuplicateEntityException(
                    String.format("Beer with name %s already exists", newBeer.getName())
            );
        }
        repository.createBeer(newBeer);
    }

    @Override
    public void deleteBeer(String name) {
        if (repository.checkBeerExists(name)) {
            throw new EntityNotFoundException(
                    String.format("Beer with name %s does not exist", name)
            );
        }
        repository.deleteBeer(name);
    }

    @Override
    public List<Beers> sortEntries(String sortType) {
        return repository.sortEntries(sortType);
    }
}