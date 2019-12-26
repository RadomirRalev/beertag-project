package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.beertag.demo.exceptions.Constants.*;

@Repository
public class CountryRepositoryImpl implements CountryRepository {
    private SessionFactory sessionFactory;


    public CountryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country getCountryById(int id) {
        try(Session session = sessionFactory.openSession()) {
            Country country = session.get(Country.class, id);
            if (country == null) {
                throw new EntityNotFoundException(
                        String.format(COUNTRY_ID_NOT_FOUND, id));
            }
            return country;
        }
    }

    @Override
    public List<Country> getCountriesList() {
        try(Session session = sessionFactory.openSession()) {
            Query<Country> query = session.createQuery("from Country", Country.class);
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(LIST_EMPTY);
        }
    }

    @Override
    public List<Country> getCountryByName(String name) {
        try(Session session = sessionFactory.openSession()) {
            Query<Country> query = session.createQuery("from Country where name LIKE :name", Country.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            throw new EntityNotFoundException(COUNTRY_NAME_NOT_FOUND, name);
        }
    }

    @Override
    public Country update(int id, Country countryToUpdate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(countryToUpdate);
            session.getTransaction().commit();
            return countryToUpdate;
        }
    }

    @Override
    public Country createCountry(Country newCountry) {
        try (Session session = sessionFactory.openSession()) {
            session.save(newCountry);
        }
        return newCountry;
    }

    @Override
    public boolean checkCountryExists(String name) {
        return getCountryByName(name).size() != 0;
    }

    @Override
    public void deleteCountry(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Country countryToBeDeleted = session.get(Country.class, id);
            session.delete(countryToBeDeleted);
            session.getTransaction().commit();
        }
    }
}
