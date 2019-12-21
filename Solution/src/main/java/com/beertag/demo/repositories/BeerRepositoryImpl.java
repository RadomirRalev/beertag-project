package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beertag.demo.models.Constants.BEER_ID_NOT_FOUND;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Beer getById(int id) {
        try(Session session = sessionFactory.openSession()) {
            Beer beer = session.get(Beer.class, id);
            if (beer == null) {
                throw new EntityNotFoundException(
                        String.format(BEER_ID_NOT_FOUND, id));
            }
            return beer;
        }
    }

    @Override
    public List<Beer> getBeerList() {
        try(Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer", Beer.class);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeerByName(String name) {
        try(Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where name= :name", Beer.class);
            query.setParameter(name, "name");
            return query.list();
        }
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
