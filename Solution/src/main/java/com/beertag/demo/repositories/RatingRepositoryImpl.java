package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.beer.Country;
import com.beertag.demo.models.beer.Rating;
import com.beertag.demo.models.user.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import static com.beertag.demo.exceptions.Constants.RATING_ID_NOT_FOUND;

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
}
