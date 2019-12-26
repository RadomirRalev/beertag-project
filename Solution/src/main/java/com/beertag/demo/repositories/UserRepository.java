package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    List<User> showUsers();

    List<Beer> getWishList();

    List<Beer> getDrankList(int userId);

    User createUser(User user);

    User getById(int id);

    User getByUsername(String name);

    void softDeleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);

}
