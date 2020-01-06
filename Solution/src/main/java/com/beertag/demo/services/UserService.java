package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.UserDetail;
import com.beertag.demo.models.user.UserRegistration;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<UserDetail> getUsers();

    Set<Beer> getWishList(int userId);

    void addBeerToWishList (int userId, int beerId);

    void softDeleteBeerToWishList (int userId, int beerId);

    void addBeerToDrankList (int userId, int beerId, int rating);

    Set<Beer> getDrankList(int userId);

    UserDetail createUser(UserRegistration userRegistration);

    UserDetail getByUsername(String name);

    UserDetail getById(int id);

    void softDeleteUser(UserDetail userDetail);

    UserDetail updateUser(UserDetail userDetail);

    boolean usernameExist(String name);

    boolean emailExist(String email);

}
