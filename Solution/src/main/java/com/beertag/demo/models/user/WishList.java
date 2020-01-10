package com.beertag.demo.models.user;

import javax.persistence.*;

@Entity
@Table(name = "wish_beer")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    int wishId;
    @Column(name = "username")
    String username;
    @Column(name = "beer_id")
    int beerId;
    @Column(name = "status")
    int status;

    public WishList() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
