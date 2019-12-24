package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {

    List<User> showUsers();

    List<Beer> getWishList();

    List<Beer> getDrankList();

    User createUser(User user);

    User findUser(String name);

    User getById (int id);

    List<User> getByNickname (String name);

    void deleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);


}
