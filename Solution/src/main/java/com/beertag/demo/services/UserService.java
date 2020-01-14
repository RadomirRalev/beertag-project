package com.beertag.demo.services;

import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistrationDTO;
import com.beertag.demo.models.user.UserUpdateDTO;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getUsers();

    Set<Beer> getWishList(String username);

    void addBeerToWishList(String username, int beerId);

    void setStatusWishList(String username, int beerId, int status);

    void addBeerToDrankList(String username, int beerId);

    void setStatusDrankList(String username, int beerId, int status);

    Set<Beer> getDrankList(String username);

    List<Beer> getDrankTop(String username);

    User createUser(UserRegistrationDTO userRegistrationDTO) throws IOException;

    User getByUsername(String name);

    User getById(int id);

    void setStatusUser(String username,int status);

    User updateUser(User userToUpdate, UserUpdateDTO userUpdateDTO) throws IOException;

    boolean usernameExist(String name);

    boolean emailExist(String email);

    boolean isUserHaveCurrentBeerOnWishList(String username, int beerId);

    boolean isBeerEnabletOnWishList(String username, int beerId);

    boolean isUserHaveCurrentBeerOnDrankList(String username, int beerId);

    boolean isBeerEnabletOnDrankList(String username, int beerId);

    void rateBeer(String username, int beerId, int rating);

}
