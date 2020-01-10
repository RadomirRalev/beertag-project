package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Tag;
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
            if (beer == null || beer.getStatus() != ENABLED) {
                throw new EntityNotFoundException(
                        String.format(BEER_ID_NOT_FOUND, id));
            }
            return beer;
        }
    }

    @Override
    public List<Beer> getBeerList() {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where status = :status", Beer.class);
            query.setParameter("status",ENABLED);
            if (query.list().isEmpty()) {
                throw new EntityNotFoundException("List is empty");
            } else {
                return query.list();
            }
        }
    }

    @Override
    public List<Beer> getBeerByName(String name) {
        try(Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer " +
                    "where name LIKE :name and status = :status", Beer.class);
            query.setParameter("name", "%" + name + "%");
            query.setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByStyleId(int styleId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer " +
                    "where style.id = :styleId and status = :status", Beer.class);
            query.setParameter("styleId", styleId);
            query.setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByStyleName(String styleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer " +
                    "where style.name LIKE :styleName and status = :status", Beer.class);
            query.setParameter("styleName", "%" + styleName + "%");
            query.setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByBreweryName(String breweryName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer " +
                    "where brewery.name LIKE :breweryName and status = :status", Beer.class);
            query.setParameter("breweryName", "%" + breweryName + "%");
            query.setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByOriginCountry(String originCountry) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer " +
                    "where originCountry.name = :originCountry and status = :status", Beer.class);
            query.setParameter("originCountry", originCountry);
            query.setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByCountryId(int countryId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer " +
                    "where originCountry.id = :countryId and status = :status", Beer.class);
            query.setParameter("countryId", countryId);
            query.setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public List<Beer> getBeersByBreweryId(int breweryId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer " +
                    "where brewery.id = :breweryId and status = :status", Beer.class);
            query.setParameter("breweryId", breweryId);
            query.setParameter("status",ENABLED);
            return query.list();
        }
    }

    @Override
    public Beer createBeer(Beer newBeer) {
//        if (checkBeerExists(newBeer.getName())) {
//            throw new DuplicateEntityException(BEER_NAME_EXISTS, newBeer.getName());
//        }
        try (Session session = sessionFactory.openSession()) {
            session.save(newBeer);

        }
        return newBeer;
    }

    @Override
    public void deleteBeer(int id) {
        if (!checkBeerExists(getById(id).getName())) {
            throw new EntityNotFoundException(BEER_ID_NOT_FOUND, id);
        }
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("update Beer " +
                    "set status = :status where id = :id ")
                    .setParameter("id", id)
                    .setParameter("status", DISABLE)
                    .executeUpdate();
            getWishListDeleteBeerQuery(id, session);
            getDrankListDeleteBeerQuery(id, session);
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

    @Override
    public void updateAvgRating(int beerId, double avgRating) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Beer beerToBeUpdated = session.get(Beer.class, beerId);
            beerToBeUpdated.setAvgRating(avgRating);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Tag> getBeerTags(int beerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tag> query = session.createNativeQuery("select * from tag" +
                    " join beertag on beertag.tag_tag_id = tag.tag_id" +
                    " where beertag.beer_beer_id = :beerId " +
                    "and tag.status =:tagStatus " +
                    "and beertag.status = :beerStatus", Tag.class);
            query.setParameter("beerId", beerId);
            query.setParameter("tagStatus", ENABLED);
            query.setParameter("beerStatus", ENABLED);
            return query.list();
        }
    }

    private void getWishListDeleteBeerQuery(int beerId, Session session) {
        session.createQuery("update WishList " +
                "set status = " + DISABLE + " " +
                "where beerId = :beerId ")
                .setParameter("beerId", beerId)
                .executeUpdate();
    }

    private void getDrankListDeleteBeerQuery(int beerId, Session session) {
        session.createQuery("update DrankList " +
                "set status = " + DISABLE + " " +
                "where beerId = :beerId ")
                .setParameter("beerId", beerId)
                .executeUpdate();
    }

}
