package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistration;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getUsers();

    Set<Beer> getWishList(int userId);

    Set<Beer> getDrankList(int userId);

    User createUser(UserRegistration userRegistration);

    User getByUsername(String name);

    User getById(int id);

    void softDeleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);

}
