package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.user.User;

import java.util.Collection;

public interface UserServices {

    Collection<User> showUsers();

    Collection<Beers> getWishList();

    Collection<Beers> getDrankList();

    User createUser(User user);

    User deleteUser(User user);

    User updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);

}
