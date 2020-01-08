package com.beertag.demo.models.user;

import javax.persistence.*;

@Entity
@Table(name = "drank_beer")
public class DrankList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drank_beer_id")
    int drankId;
    @Column(name = "username")
    String username;
    @Column(name = "beer_id")
    int beerId;


    public DrankList() {
    }

    public int getDrankId() {
        return drankId;
    }

    public void setDrankId(int drankId) {
        this.drankId = drankId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

}
