package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beertag.demo.exceptions.Constants.*;

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
            Query<Beer> query = session.createQuery("from Beer where name LIKE :name", Beer.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByStyleId(int styleId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where style.id = :styleId", Beer.class);
            query.setParameter("styleId", styleId);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByStyleName(String styleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where style.name LIKE :styleName", Beer.class);
            query.setParameter("styleName", "%" + styleName + "%");
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByCountryId(int countryId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where originCountry.id = :countryId", Beer.class);
            query.setParameter("countryId", countryId);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByBreweryId(int breweryId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where brewery.id = :breweryId", Beer.class);
            query.setParameter("breweryId", breweryId);
            return query.list();
        }
    }

    @Override
    public Beer createBeer(Beer newBeer) {
        try (Session session = sessionFactory.openSession()) {
            session.save(newBeer);
        }
        return newBeer;
    }

    @Override
    public void deleteBeer(int id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Beer beerToBeDeleted = session.get(Beer.class, id);
            session.delete(beerToBeDeleted);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean checkBeerExists(String name) {
        return getBeerByName(name).size() != 0;
    }

    @Override
    public Beer update(int id, Beer beerToUpdate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(beerToUpdate);
            session.getTransaction().commit();
            return beerToUpdate;
        }
    }
}
