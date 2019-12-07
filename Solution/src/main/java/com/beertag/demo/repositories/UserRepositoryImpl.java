package com.beertag.demo.repositories;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.beertag.demo.models.Constants.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

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
        try {
            usersList.put(user.getUserName(), user);
        } catch (Exception e) {
            throw new DuplicateEntityException(USER_NAME_EXISTS, user.getUserName());
        }
        return user;
    }

    @Override
    public User findUser(String name) {
        try {
            return usersList.get(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format(USER_NAME_NOT_FOUND, name));
        }
    }

    @Override
    public User deleteUser(User user) {
        try {
            findUser(user.getUserName());
            return usersList.remove(user.getUserName());
        } catch (Exception e) {
            throw new EntityNotFoundException(USER_NAME_NOT_FOUND, user.getUserName());
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            User userToUpdate = findUser(user.getUserName());
            userToUpdate.setEmail(user.getEmail());
            return userToUpdate;
        } catch (Exception e) {
            throw new EntityNotFoundException(USER_NAME_NOT_FOUND, user.getUserName());
        }
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
