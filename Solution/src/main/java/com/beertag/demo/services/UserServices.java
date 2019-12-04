package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;

import java.util.Collection;

public interface UserServices {

    Collection<User> showUsers();

    Collection<Beers> getWishList();

    Collection<Beers> getDrankList();

    void createUser(User user);

    User findUser(String name);

    void deleteUser(User user);

    void updateUser(User user);

    boolean userExist(String name);

}
