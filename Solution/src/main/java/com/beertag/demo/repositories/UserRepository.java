package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.DrankList;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.WishList;


import java.util.List;
import java.util.Set;

public interface UserRepository {

    List<User> getUsers();

    Set<Beer> getWishList(String username);

    void addBeerFromWishList(WishList wishList);

    void setStatusWishList(String username, int beerId, int status);

    void addBeerToDrankList(DrankList drankList);

    void setStatusDrankList(String username, int beerId, int status);

    Set<Beer> getDrankList(String username);

    List<Beer> getDrankTop(String username);

    User createUser(User user);

    User getById(int id);

    User getByUsername(String name);

    void setStatusUser(String username,int status);

    User updateUser(User user);

    boolean usernameExist(String name);

    boolean emailExist(String email);

    boolean isUserHaveCurrentBeerOnWishList(String username, int beerId);

   boolean isBeerEnabletOnWishList(String username, int beerId);

    boolean isUserHaveCurrentBeerOnDrankList(String username, int beerId);

    boolean isBeerEnabletOnDrankList(String username, int beerId);

    void rateBeer(String username, int beerId, int rating);

}
