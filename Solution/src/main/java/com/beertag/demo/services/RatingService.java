package com.beertag.demo.services;

import com.beertag.demo.models.beer.Rating;

public interface RatingService {

    void createRating(Rating newRating);

    void updateRating(int rating, Rating ratingToUpdate);

    Rating getRating(int ratingId);
}
