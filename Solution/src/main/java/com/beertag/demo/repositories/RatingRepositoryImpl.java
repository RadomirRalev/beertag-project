package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Rating;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.beertag.demo.exceptions.Constants.*;

@Repository
public class RatingRepositoryImpl implements RatingRepository {
    private static final String IS_RATING_EXITS_SQL = "select * " +
            "from rating " +
            "where drank_id = (select (drank_beer_id)" +
            "    from drank_beer " +
            "    where username = :username and beer_id = :beerId and status = :status);" ;

    private static final String GET_RATING_FROM_BEER = "select * from rating" +
            " join drank_beer on rating.drank_id = drank_beer.drank_beer_id" +
            " where beer_id = :beerId " +
            "and drank_beer.status = :beerStatus";

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
            Query<Rating> query = session.createNativeQuery(GET_RATING_FROM_BEER, Rating.class);
            query.setParameter("beerId", beerId);
            query.setParameter("beerStatus", ENABLED);
            return query.list();
        }
    }

    @Override
    public boolean isRated(String username, int beerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Rating> query = session.createNativeQuery(IS_RATING_EXITS_SQL, Rating.class);
            query.setParameter("username", username);
            query.setParameter("beerId", beerId);
            query.setParameter("status", ENABLED);
            return !query.list().isEmpty();
        }
    }
}