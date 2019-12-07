package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;

import java.util.Collection;

public interface UserService {

    Collection<User> showUsers();

    Collection<Beer> getWishList();

    Collection<Beer> getDrankList();

    User createUser(User user);

    User deleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);

}
