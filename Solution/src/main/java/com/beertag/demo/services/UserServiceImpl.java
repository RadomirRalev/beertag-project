package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.beertag.demo.models.Constants.EMAIL_ALREADY_EXISTS;
import static com.beertag.demo.models.Constants.USER_ALREADY_EXISTS;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository beerTagRepository) {
        this.userRepository = beerTagRepository;
    }

    @Override
    public Collection<User> showUsers() {
        return userRepository.showUsers();
    }

    @Override
    public Collection<Beer> getWishList() {
        return userRepository.getWishList();
    }

    @Override
    public Collection<Beer> getDrankList() {
        return userRepository.getDrankList();
    }

    @Override
    public User createUser(User user) {
        if (userExist(user.getUserName())) {
            throw new DuplicateEntityException(USER_ALREADY_EXISTS);
        }

        if (emailExist(user.getEmail())) {
            throw new DuplicateEntityException(EMAIL_ALREADY_EXISTS);
        }

        return userRepository.createUser(user);
    }

    @Override
    public void deleteUser(User user) {
         userRepository.deleteUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean userExist(String name) {
        return userRepository.userExist(name);
    }

    @Override
    public boolean emailExist(String name) {
        return userRepository.emailExist(name);
    }

}
