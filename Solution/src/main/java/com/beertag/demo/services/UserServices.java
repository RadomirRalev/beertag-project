package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;

import java.util.Collection;

public interface UserServices {

    Collection<User> showUsers();

    Collection<Beers> getWishList();

    Collection<Beers> getDrankList();

    void createUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    boolean userExist(String name);

    boolean emailExist(String email);

    boolean isUserAdult(int day, int month, int year);

    boolean isNull(Object object);

}
