package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistration;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getUsers();

    Set<Beer> getWishList(String username);

    void addBeerToWishList (String username, int beerId);

    void softDeleteBeerToWishList (String username, int beerId);

    void addBeerToDrankList (String username, int beerId, int rating);

    Set<Beer> getDrankList(String username);

    User createUser(UserRegistration userRegistration);

    User getByUsername(String name);

    User getById(int id);

    void softDeleteUser(User user);

    User updateUser(User user);

    boolean usernameExist(String name);

    boolean emailExist(String email);

    boolean isUserHaveCurrentBeerInWishList(String username, int beerId );

    boolean isUserHaveCurrentBeerInDrankList(String username, int beerId );

}
