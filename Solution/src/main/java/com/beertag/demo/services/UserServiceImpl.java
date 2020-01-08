package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.*;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.beertag.demo.exceptions.Constants.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BeerService beerService;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BeerService beerService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.beerService = beerService;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Set<Beer> getWishList(String username) {
        return userRepository.getWishList(username);
    }

    @Override
    public void addBeerToWishList(String username, int beerId) {
        Beer beer = beerService.getById(beerId);
        if (isUserHaveCurrentBeerInWishList(username, beerId)) {
            throw new DuplicateEntityException(String.format(USER_ALREADY_HAVE_BEER_WISH_LIST, username, beer.getName()));
        }
        User user = getByUsername(username);
        WishList wishList = new WishList();
        wishList.setUsername(user.getUsername());
        wishList.setBeerId(beer.getId());

        userRepository.addBeerToWishList(wishList);
    }

    //TODO
    @Override
    public void softDeleteBeerToWishList(String username, int beerId) {


    }

    @Override
    public boolean isUserHaveCurrentBeerInWishList(String username, int beerId) {
        return userRepository.isUserHaveCurrentBeerInWishList(username, beerId);
    }

    @Override
    public Set<Beer> getDrankList(String username) {
        return userRepository.getDrankList(username);
    }

    @Override
    public void addBeerToDrankList(String username, int beerId, int rating) {

        Beer beer = beerService.getById(beerId);
        if (isUserHaveCurrentBeerInDrankList(username, beerId)) {
            throw new DuplicateEntityException(
                    String.format(USER_ALREADY_HAVE_BEER_DRANK_LIST, username, beer.getName()));
        }
        User user = getByUsername(username);
        DrankList drankList = new DrankList();
        drankList.setUsername(user.getUsername());
        drankList.setBeerId(beer.getId());
        userRepository.addBeerToDrankList(drankList);
    }

    @Override
    public boolean isUserHaveCurrentBeerInDrankList(String username, int beerId) {
        return userRepository.isUserHaveCurrentBeerInDrankList(username, beerId);
    }

    @Override
    public User createUser(UserRegistration userRegistration) {
        User user = userMapper.validationData(userRegistration);

        if (usernameExist(user.getUsername())) {
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
    public boolean usernameExist(String name) {
        return userRepository.usernameExist(name);
    }

    @Override
    public boolean emailExist(String name) {
        return userRepository.emailExist(name);
    }

}
