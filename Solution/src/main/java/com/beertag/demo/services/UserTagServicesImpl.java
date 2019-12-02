package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;
import com.beertag.demo.repositories.UserTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class UserTagServicesImpl implements UserTagServices {

    private static final String THIS_USER_ALREADY_EXIST = "This user already exist";

    private UserTagRepository beerTagRepository;

    @Autowired
    public UserTagServicesImpl(UserTagRepository beerTagRepository) {
        this.beerTagRepository = beerTagRepository;
    }


    @Override
    public Collection<User> showUsers() {
        return beerTagRepository.showUsers();
    }

    @Override
    public Collection<Beers> getWishList() {
        return beerTagRepository.getWishList();
    }

    @Override
    public Collection<Beers> getDrankList() {
        return beerTagRepository.getDrankList();
    }

    @Override
    public void createUser(User user) {
        if (userExist(user.getUserName())){
            throw new DuplicateEntityException(THIS_USER_ALREADY_EXIST);
        }
         beerTagRepository.createUser(user);
    }

    @Override
    public User findUser(String name) {
        return beerTagRepository.findUser(name);
    }

    @Override
    public void deleteUser(User user) {
        beerTagRepository.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        beerTagRepository.updateUser(user);
    }

    @Override
    public boolean userExist(String name) {
        return beerTagRepository.userExist(name);
    }
}
