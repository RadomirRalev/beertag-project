package com.beertag.demo.repositories;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.DrankList;
import com.beertag.demo.models.user.UserDetail;
import com.beertag.demo.models.user.WishList;


import java.util.List;
import java.util.Set;

public interface UserRepository {

    List<UserDetail> getUsers();

    Set<Beer> getWishList(String username);

    void addBeerToWishList (WishList wishList);

    void softDeleteBeerToWishList (WishList wishListToDelete);

    void addBeerToDrankList (DrankList drankList);

    Set<Beer> getDrankList(String username);

    UserDetail createUser(UserDetail userDetail);

    UserDetail getById(int id);

    UserDetail getByUsername(String name);

    void softDeleteUser(UserDetail userDetail);

    UserDetail updateUser(UserDetail userDetail);

    boolean usernameExist(String name);

    boolean emailExist(String email);


}
