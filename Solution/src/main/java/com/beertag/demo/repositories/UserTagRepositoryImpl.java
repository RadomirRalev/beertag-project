package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserTagRepositoryImpl implements UserTagRepository {

    private static final String USER_NOT_FOUND = "User with name %s not found";

    private Map<String,User> usersList;
    private Map<User,Beers> wishList;
    private Map<User,Beers> drankList;

    public UserTagRepositoryImpl() {
        usersList = new HashMap<>();
        wishList = new HashMap<>();
        drankList = new HashMap<>();
    }


    @Override
    public Collection<User> showUsers() {
        return usersList.values();
    }

    @Override
    public Collection<Beers> getWishList() {
        return wishList.values();
    }

    @Override
    public Collection<Beers> getDrankList() {
        return drankList.values();
    }

    @Override
    public void createUser(User user) {
        usersList.put(user.getUserName(), user);
    }

    @Override
    public User findUser(String name) {
         if (userExist(name)){
            return usersList.get(name);
        }
         throw new EntityNotFoundException(String.format(USER_NOT_FOUND, name));
    }

    @Override
    public void deleteUser(User user) {
         findUser(user.getUserName());
        usersList.remove(user.getUserName());

    }

    @Override
    public void updateUser(User user) {
        User userToUpdate = findUser(user.getUserName());
        userToUpdate.setEmail(user.getEmail());
    }

    @Override
    public boolean userExist(String name) {
      return usersList.containsKey(name);
    }
}
