package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.Beer;
import com.beertag.demo.models.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String USER_NOT_FOUND = "User with name %s not found";

    private Map<String, User> usersList;
    private Map<User, Beer> wishList;
    private Map<User, Beer> drankList;

    public UserRepositoryImpl() {
        usersList = new HashMap<>();
        wishList = new HashMap<>();
        drankList = new HashMap<>();
    }


    @Override
    public Collection<User> showUsers() {
        return usersList.values();
    }

    @Override
    public Collection<Beer> getWishList() {
        return wishList.values();
    }

    @Override
    public Collection<Beer> getDrankList() {
        return drankList.values();
    }

    @Override
    public User createUser(User user) {
         usersList.put(user.getUserName(), user);
         return user;
    }

    @Override
    public User findUser(String name) {
        if (userExist(name)) {
            return usersList.get(name);
        }
        throw new EntityNotFoundException(String.format(USER_NOT_FOUND, name));
    }

    @Override
    public User deleteUser(User user) {
        findUser(user.getUserName());
       return usersList.remove(user.getUserName());
    }

    @Override
    public User updateUser(User user) {
        User userToUpdate = findUser(user.getUserName());
        userToUpdate.setEmail(user.getEmail());
        return userToUpdate;
    }

    @Override
    public boolean userExist(String name) {
        return usersList.containsKey(name);
    }

    @Override
    public boolean emailExist(String email) {
        return usersList.values().stream()
                .map(User::getEmail)
                .anyMatch(e -> e.equals(email));
    }
}
