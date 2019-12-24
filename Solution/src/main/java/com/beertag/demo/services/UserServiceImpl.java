package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.EntityNotFoundException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.User;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.beertag.demo.models.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository beerTagRepository) {
        this.userRepository = beerTagRepository;
    }

    @Override
    public List<User> showUsers() {
        return userRepository.showUsers();
    }

    @Override
    public List<Beer> getWishList() {
        return userRepository.getWishList();
    }

    @Override
    public List<Beer> getDrankList() {
        return userRepository.getDrankList();
    }

    @Override
    public User createUser(User user) {
        if (userExist(user.getNickName())) {
            throw new DuplicateEntityException(USER_ALREADY_EXISTS);
        }

        if (emailExist(user.getEmail())) {
            throw new DuplicateEntityException(EMAIL_ALREADY_EXISTS);
        }

        return userRepository.createUser(user);
    }

    @Override
    public List<User> getByNickname(String name) {
        return userRepository.getByNickname(name);
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteUser(User user) {
        if (!userExist(user.getNickName())) {
            throw new EntityNotFoundException(USER_NAME_NOT_FOUND);
        }
        userRepository.deleteUser(user);
    }

    @Override
    public User updateUser(User user) {
        if (!userExist(user.getNickName())) {
            throw new EntityNotFoundException(USER_NAME_NOT_FOUND);
        }
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
