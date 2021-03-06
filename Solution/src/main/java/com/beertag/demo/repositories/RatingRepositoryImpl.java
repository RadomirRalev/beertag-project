package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Rating;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beertag.demo.constants.ExceptionConstants.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public RatingRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createRating(Rating newRating) {
        try (Session session = sessionFactory.openSession()) {
            session.save(newRating);
        }
    }

    @Override
    public void updateRating(int ratingId, Rating ratingToUpdate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(ratingToUpdate);
            session.getTransaction().commit();
        }
    }

    @Override
    public Rating getRating(int ratingId) {
        try (Session session = sessionFactory.openSession()) {
            Rating rating = session.get(Rating.class, ratingId);
            if (rating == null) {
                throw new EntityNotFoundException(
                        String.format(RATING_ID_NOT_FOUND, ratingId));
            }
            return rating;
        }
    }

    @Override
    public List<Rating> getRatingsOfSpecificBeer(int beerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Rating> query = session.createNativeQuery(GET_RATING_FROM_BEER_SQL, Rating.class);
            query.setParameter("beerId", beerId);
            query.setParameter("beerStatus", ENABLE);
            return query.list();
        }
    }

    @Override
    public boolean isRated(String username, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Rating> query = session.createNativeQuery(IS_RATING_EXITS_SQL, Rating.class);
            query.setParameter("username", username);
            query.setParameter("beerId", beerId);
            query.setParameter("status", ENABLE);
            return !query.list().isEmpty();
        }
    }
}