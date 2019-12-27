package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;


import java.util.List;
import java.util.Set;

public interface UserRepository {

    List<User> getUsers();

    Set<Beer> getWishList(int userId);

    Set<Beer> getDrankList(int userId);

    User createUser(User user);

    User getById(int id);

    User getByUsername(String name);

    void softDeleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);


}
