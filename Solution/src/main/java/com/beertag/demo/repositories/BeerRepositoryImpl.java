package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Beer getById(int id) {
        return null;
    }

    @Override
    public List<Beer> getBeerList() {
        sessionFactory.openSession();
        return null;
    }

    @Override
    public Beer getSpecificBeer(String name) {
        return null;
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        return null;
    }

    @Override
    public void deleteBeer(String name) {

    }

    @Override
    public boolean checkBeerExists(String name) {
        return false;
    }

    @Override
    public Beer update(int id, Beer beerToUpdate) {
        return null;
    }
}
