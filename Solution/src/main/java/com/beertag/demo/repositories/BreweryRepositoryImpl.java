package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Brewery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beertag.demo.exceptions.Constants.*;

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
            if (brewery == null || brewery.getStatus() != ENABLED) {
                throw new EntityNotFoundException(
                        String.format(BREWERY_ID_NOT_FOUND, id));
            }
            return brewery;
        }
    }

    @Override
    public List<Brewery> getBreweriesList() {
        try(Session session = sessionFactory.openSession()) {
            Query<Brewery> query = session.createQuery("from Brewery where status = :status", Brewery.class)
                    .setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public List<Brewery> getBreweryByName(String name) {
        try(Session session = sessionFactory.openSession()) {
            Query<Brewery> query = session.createQuery("from Brewery " +
                    "where name LIKE :name and status = :status", Brewery.class);
            query.setParameter("name", "%" + name + "%");
            query.setParameter("status", ENABLED);
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
            session.createQuery("update Brewery " +
                    "set status = :status where id = :id ")
                    .setParameter("id", id)
                    .setParameter("status", DISABLE)
                    .executeUpdate();
            session.
                    getTransaction()
                    .commit();
        }
    }
}
