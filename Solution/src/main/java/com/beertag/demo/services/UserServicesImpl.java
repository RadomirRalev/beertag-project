package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.Beers;
import com.beertag.demo.models.user.User;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServicesImpl implements UserServices {

    private static final String THIS_USER_ALREADY_EXIST = "User already exist";
    private static final String EMAIL_ALREADY_EXIST = "Email already exist";

    private UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository beerTagRepository) {
        this.userRepository = beerTagRepository;
    }

    @Override
    public Collection<User> showUsers() {
        return userRepository.showUsers();
    }

    @Override
    public Collection<Beers> getWishList() {
        return userRepository.getWishList();
    }

    @Override
    public Collection<Beers> getDrankList() {
        return userRepository.getDrankList();
    }

    @Override
    public User createUser(User user) {
        if (userExist(user.getUserName())) {
            throw new DuplicateEntityException(THIS_USER_ALREADY_EXIST);
        }

        if (emailExist(user.getEmail())) {
            throw new DuplicateEntityException(EMAIL_ALREADY_EXIST);
        }

        return userRepository.createUser(user);
    }

    @Override
    public User deleteUser(User user) {
        return userRepository.deleteUser(user);
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
    public boolean emailExist(String email) {
        return userRepository.emailExist(email);
    }

}
