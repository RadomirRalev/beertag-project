package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.DrankList;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.WishList;


import java.util.List;
import java.util.Set;

public interface UserRepository {

    List<User> getUsers();

    Set<Beer> getWishList(int userId);

    void addBeerToWishList (WishList wishList);

    void softDeleteBeerToWishList (WishList wishListToDelete);

    void addBeerToDrankList (DrankList drankList);

    Set<Beer> getDrankList(int userId);

    User createUser(User user);

    User getById(int id);

    User getByUsername(String name);

    void softDeleteUser(User user);

    User updateUser(User user);

    boolean usernameExist(String name);

    boolean emailExist(String email);


}
