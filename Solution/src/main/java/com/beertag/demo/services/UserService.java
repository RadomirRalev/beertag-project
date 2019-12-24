package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistration;

import java.util.List;

public interface UserService {

    List<User> showUsers();

    List<Beer> getWishList();

    List<Beer> getDrankList();

    User createUser(UserRegistration userRegistration);

    List<User> getByNickname(String name);

    User getById (int id);

    void deleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);

}
