package com.beertag.demo.models.beer;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int ratingId;
    @Column(name = "drank_id")
    private int drankId;
    @Column(name = "rating")
    private int rating;

    public Rating() {

    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getDrankId() {
        return drankId;
    }

    public void setDrankId(int drankId) {
        this.drankId = drankId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
