package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.beer.Beer;
import com.beertag.demo.models.user.*;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.beertag.demo.constants.ExceptionConstants.*;
import static com.beertag.demo.constants.SQLQueryConstants.*;

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
    public User createUser(UserRegistrationDTO userRegistrationDTO) throws IOException {
        User user = userMapper.validationData(userRegistrationDTO);

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
    public void setStatusUser(String username,int status) {
        userRepository.setStatusUser(username,status);
    }

    @Override
    public User updateUser(User userToUpdate, UserUpdateDTO userUpdateDTO) throws IOException {
        userMapper.validationData(userUpdateDTO, userToUpdate);
        return userRepository.updateUser(userToUpdate);
    }

    @Override
    public boolean usernameExist(String name) {
        return userRepository.usernameExist(name);
    }

    @Override
    public boolean emailExist(String name) {
        return userRepository.emailExist(name);
    }

    @Override
    public Set<Beer> getWishList(String username) {
        return userRepository.getWishList(username);
    }

    @Override
    public void addBeerToWishList(String username, int beerId) {
        Beer beer = beerService.getById(beerId);
        if (isUserHaveCurrentBeerOnWishList(username, beerId)) {
            throw new DuplicateEntityException(String.format(USER_ALREADY_HAVE_BEER_WISH_LIST, username, beer.getName()));
        }
        User user = getByUsername(username);
        WishList wishList = new WishList();
        wishList.setStatus(ENABLE);
        wishList.setUsername(user.getUsername());
        wishList.setBeerId(beer.getId());

        userRepository.addBeerFromWishList(wishList);
    }


    @Override
    public void setStatusWishList(String username, int beerId, int status) {
        userRepository.setStatusWishList(username, beerId, status);
    }

    @Override
    public boolean isUserHaveCurrentBeerOnWishList(String username, int beerId) {
        return userRepository.isUserHaveCurrentBeerOnWishList(username, beerId);
    }

    @Override
    public boolean isBeerEnabletOnWishList(String username, int beerId) {
        return userRepository.isBeerEnabletOnWishList(username, beerId);
    }

    @Override
    public Set<Beer> getDrankList(String username) {
        return userRepository.getDrankList(username);
    }

    @Override
    public void addBeerToDrankList(String username, int beerId) {

        Beer beer = beerService.getById(beerId);
        if (isUserHaveCurrentBeerOnDrankList(username, beerId)) {
            throw new DuplicateEntityException(
                    String.format(USER_ALREADY_HAVE_BEER_DRANK_LIST, username, beer.getName()));
        }
        User user = getByUsername(username);

        DrankList drankList = new DrankList();
        drankList.setStatus(ENABLE);
        drankList.setUsername(user.getUsername());
        drankList.setBeerId(beer.getId());
        userRepository.addBeerToDrankList(drankList);
    }

    @Override
    public void setStatusDrankList(String username, int beerId, int status) {
        userRepository.setStatusDrankList(username, beerId, status);
    }

    @Override
    public boolean isUserHaveCurrentBeerOnDrankList(String username, int beerId) {
        return userRepository.isUserHaveCurrentBeerOnDrankList(username, beerId);
    }

    @Override
    public boolean isBeerEnabletOnDrankList(String username, int beerId) {
        return userRepository.isBeerEnabletOnDrankList(username,beerId);
    }


    @Override
    public void rateBeer(String username, int beerId, int rating) {
        userRepository.rateBeer(username, beerId, rating);
    }

}
