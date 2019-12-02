package com.beertag.demo.repositories;


import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;

import java.util.List;

public interface BeerTagRepository {

    List<Beers> getBeersList();

    List<User> getUsers();

    List<Beers> getWishList();

    List<Beers> getDrankList();

    void createUser(User user);

     User findUser(String name);

    void deleteUser(String name);

    void updateUser(String name);

     boolean isUserExist(String name);

}
