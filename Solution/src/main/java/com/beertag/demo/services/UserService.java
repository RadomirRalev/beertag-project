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

    void addBeerToWishList (String username, int beerId);

    void softDeleteBeerFromWishList(String username, int beerId);

    void addBeerToDrankList (String username, int beerId);

    void softDeleteBeerFromDrankList (String username, int beerId);

    Set<Beer> getDrankList(String username);

    User createUser(UserRegistrationDTO userRegistrationDTO) throws IOException;

    User getByUsername(String name);

    User getById(int id);

    void softDeleteUser(User user);

    User updateUser(User userToUpdate, UserUpdateDTO userUpdateDTO) throws IOException;

    boolean usernameExist(String name);

    boolean emailExist(String email);

    boolean isUserHaveCurrentBeerOnWishList(String username, int beerId );

    boolean isUserHaveCurrentBeerOnDrankList(String username, int beerId );

    void rateBeer (String username, int beerId, int rating);

}
