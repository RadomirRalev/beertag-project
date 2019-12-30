package com.beertag.demo.models.user;

import javax.persistence.*;

@Entity
@Table(name = "wish_beer")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_user_id")
    int wish_id;
    @Column(name = "user_user_id")
    int user_id;
    @Column(name = "beer_beer_id")
    int beer_id;
    @Column(name = "deleted")
    boolean deleted;

    public WishList() {
    }

    public int getWish_id() {
        return wish_id;
    }

    public void setWish_id(int wish_id) {
        this.wish_id = wish_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBeer_id() {
        return beer_id;
    }

    public void setBeer_id(int beer_id) {
        this.beer_id = beer_id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
