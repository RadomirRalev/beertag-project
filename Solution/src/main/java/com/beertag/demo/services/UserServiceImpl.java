package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.models.user.UserRegistration;
import com.beertag.demo.models.user.UserMapper;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.beertag.demo.exceptions.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Set<Beer> getWishList(int UserId) {
        return userRepository.getWishList(UserId);
    }

    @Override
    public Set<Beer> getDrankList(int UserId) {
        return userRepository.getDrankList(UserId);
    }

    @Override
    public User createUser(UserRegistration userRegistration) {
        User user = mapper.validationData(userRegistration);

        if (userExist(user.getUsername())) {
            throw new DuplicateEntityException(USER_USERNAME_EXISTS, user.getUsername());
        }

        if (emailExist(user.getEmail())) {
            throw new DuplicateEntityException(USER_EMAIL_EXISTS, user.getEmail());
        }

        return userRepository.createUser(user);
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.getByUsername(name);
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void softDeleteUser(User user) {
        userRepository.softDeleteUser(user);
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
