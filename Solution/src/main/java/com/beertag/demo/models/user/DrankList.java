package com.beertag.demo.models.user;

import javax.persistence.*;

@Entity
@Table(name = "drank_beer")
public class DrankList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drank_beer_id")
    int drankId;
    @Column(name = "drank_beer_user_id")
    int userId;
    @Column(name = "drank_beer_beer_id")
    int beerId;
    @Column(name = "deleted")
    boolean deleted;

    public DrankList() {
    }

    public int getDrankId() {
        return drankId;
    }

    public void setDrankId(int drankId) {
        this.drankId = drankId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
