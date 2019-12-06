package com.beertag.demo.repositories;

import com.beertag.demo.models.Beer;
import com.beertag.demo.models.user.User;

import java.util.Collection;

public interface UserRepository {

    Collection<User> showUsers();

    Collection<Beer> getWishList();

    Collection<Beer> getDrankList();

    User createUser(User user);

    User findUser(String name);

    User deleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);


}
