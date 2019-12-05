package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;

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

    boolean isUserAdult(int day, int month, int year);

    boolean isNull(Object object);

}
