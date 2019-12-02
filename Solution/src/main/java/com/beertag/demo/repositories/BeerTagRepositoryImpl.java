package com.beertag.demo.repositories;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeerTagRepositoryImpl implements BeerTagRepository {

    private List<Beers> beersList;
    private List<User> usersList;
    private List<Beers> wishList;
    private List<Beers> drankList;

    public BeerTagRepositoryImpl() {
        beersList = new ArrayList<>();
        usersList = new ArrayList<>();
        wishList = new ArrayList<>();
        drankList = new ArrayList<>();
        //не съм вкарвал логика в колекциите, най-вероятно после всички ще станат HashMap ...
    }

    @Override
    public List<Beers> getBeersList() {
        return beersList;
    }

    @Override
    public List<User> getUsers() {
        return usersList;
    }

    @Override
    public List<Beers> getWishList() {
        return wishList;
    }

    @Override
    public List<Beers> getDrankList() {
        return drankList;
    }

    @Override
    public void createUser(User user) {
        usersList.add(user);
    }

    @Override
    public User findUser(String name) {
      return   usersList.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new RuntimeException(String.format("User with name %s not found", name)));

    }

    @Override
    public void deleteUser(String name) {

    }

    @Override
    public void updateUser(String name) {

    }

    @Override
    public boolean isUserExist(String name) {
      return   usersList.stream()
                .anyMatch(user -> user.getName().equals(name));
    }


}
