package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Rating;
import com.beertag.demo.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.beertag.demo.constants.ExceptionConstants.*;

@Service
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void createRating(Rating newRating) {
        try {
            ratingRepository.createRating(newRating);
        } catch (Exception e) {
            throw new DuplicateEntityException(RATING_EXISTS);
        }
    }

    @Override
    public void updateRating(int ratingId, Rating ratingToUpdate) {
        try {
            ratingRepository.updateRating(ratingId, ratingToUpdate);
        } catch (Exception e) {
            throw new EntityNotFoundException(RATING_ID_NOT_FOUND, ratingId);
        }
    }

    @Override
    public Rating getRating(int ratingId) {
        try {
            return ratingRepository.getRating(ratingId);
        } catch (Exception e) {
            throw new EntityNotFoundException (RATING_ID_NOT_FOUND, ratingId);
        }
    }

    @Override
    public boolean isRated(String username, int beerId) {
        return ratingRepository.isRated(username,beerId);
    }
}
