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

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BeerService beerService) {
        this.userRepository = userRepository;
        this.beerService = beerService;
    }

    @Override
    public List<UserDetail> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Set<Beer> getWishList(String username) {
        return userRepository.getWishList(username);
    }

    @Override
    public void addBeerToWishList(String username, int beerId) {
        UserDetail userDetail = getByUsername(username);
        Beer beer = beerService.getById(beerId);

        WishList wishList = new WishList();
        wishList.setUser_id(userDetail.getId());
        wishList.setBeer_id(beer.getId());

        userRepository.addBeerToWishList(wishList);

    }

    //TODO
    @Override
    public void softDeleteBeerToWishList(String username, int beerId) {


    }

    @Override
    public Set<Beer> getDrankList(String username) {
        return userRepository.getDrankList(username);
    }

    @Override
    public void addBeerToDrankList(String username, int beerId, int rating) {
        UserDetail userDetail = getByUsername(username);
        Beer beer = beerService.getById(beerId);
        DrankList drankList = new DrankList();
        drankList.setUserId(userDetail.getId());
        drankList.setBeerId(beer.getId());
        userRepository.addBeerToDrankList(drankList);
    }

    @Override
    public UserDetail createUser(UserRegistration userRegistration) {
        UserDetail userDetail = UserMapper.validationData(userRegistration);

        if (usernameExist(userDetail.getUsername())) {
            throw new DuplicateEntityException(USER_USERNAME_EXISTS, userDetail.getUsername());
        }

        if (emailExist(userDetail.getEmail())) {
            throw new DuplicateEntityException(USER_EMAIL_EXISTS, userDetail.getEmail());
        }

        return userRepository.createUser(userDetail);
    }

    @Override
    public UserDetail getByUsername(String name) {
        return userRepository.getByUsername(name);
    }

    @Override
    public UserDetail getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void softDeleteUser(UserDetail userDetail) {
        userRepository.softDeleteUser(userDetail);
    }

    @Override
    public UserDetail updateUser(UserDetail userDetail) {
        return userRepository.updateUser(userDetail);
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
