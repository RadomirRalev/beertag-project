package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Rating;

import java.util.List;

public interface RatingRepository {

    void createRating(Rating newRating);

    void updateRating(int rating, Rating ratingToUpdate);

    Rating getRating(int ratingId);

    List<Rating> getRatingsOfSpecificBeer(int beerId);

     boolean isRated (String username, int beerId);

}
