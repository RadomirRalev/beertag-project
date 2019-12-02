package com.beertag.demo.services;

import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;
import com.beertag.demo.repositories.BeerTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerTagServicesImpl implements BeerTagServices {

    private BeerTagRepository beerTagRepository;

    @Autowired
    public BeerTagServicesImpl(BeerTagRepository beerTagRepository) {
        this.beerTagRepository = beerTagRepository;
    }


    @Override
    public List<Beers> getBeersList() {
        return beerTagRepository.getBeersList();
    }

    @Override
    public List<User> getUsers() {
        return beerTagRepository.getUsers();
    }

    @Override
    public List<Beers> getWishList() {
        return beerTagRepository.getWishList();
    }

    @Override
    public List<Beers> getDrankList() {
        return beerTagRepository.getDrankList();
    }

    @Override
    public void createUser(User user) {
         beerTagRepository.createUser(user);
         //тука трябва да се вкара логиката, която ще ограничава User да са уникални.
        //Но първо да видим как ще го играем с имената ...
    }
}
