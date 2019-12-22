package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Brewery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Repository
public class BreweryRepositoryImpl implements BreweryRepository {
    SessionFactory sessionFactory;

    public BreweryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Brewery getBreweryById(int id) {
        try(Session session = sessionFactory.openSession()) {
            Brewery brewery = session.get(Brewery.class, id);
            if (brewery == null) {
                throw new EntityNotFoundException(
                        String.format(BREWERY_ID_NOT_FOUND, id));
            }
            return brewery;
        }
    }

    @Override
    public List<Brewery> getBreweriesList() {
        try(Session session = sessionFactory.openSession()) {
            Query<Brewery> query = session.createQuery("from Brewery", Brewery.class);
            return query.list();
        }
    }

    @Override
    public List<Brewery> getBreweryByName(String name) {
        try(Session session = sessionFactory.openSession()) {
            Query<Brewery> query = session.createQuery("from Brewery where name= :name", Brewery.class);
            query.setParameter(name, "name");
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(BREWERY_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Brewery update(int id, Brewery breweryToUpdate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(breweryToUpdate);
            session.getTransaction().commit();
            return breweryToUpdate;
        }
    }

    @Override
    public Brewery createBrewery(Brewery newBrewery) {
        try (Session session = sessionFactory.openSession()) {
            session.save(newBrewery);
        }
        return newBrewery;
    }

    @Override
    public boolean checkBreweryExists(String name) {
        return getBreweryByName(name).size() != 0;
    }

    @Override
    public void deleteBrewery(int id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Brewery breweryToBeDeleted = session.get(Brewery.class, id);
            session.delete(breweryToBeDeleted);
            session.getTransaction().commit();
        }
    }
}
