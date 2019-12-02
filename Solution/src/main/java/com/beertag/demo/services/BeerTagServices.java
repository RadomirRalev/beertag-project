package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;

import java.util.List;

public interface BeerTagServices {

    List<Beers> getBeersList();

    List<User> getUsers();

    List<Beers> getWishList();

    List<Beers> getDrankList();

    void createUser(User user);
}
