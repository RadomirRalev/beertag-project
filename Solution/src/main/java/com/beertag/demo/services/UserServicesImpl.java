package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.models.Beers;
import com.beertag.demo.models.user.User;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Service
public class UserServicesImpl implements UserServices {

    private static final String THIS_USER_ALREADY_EXIST = "User already exist";
    private static final String EMAIL_ALREADY_EXIST = "Email already exist";
    private static final int ADULT_YEAR = 18;

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

        if (isNull(user.getFirstName())) {
            user.setFirstName("");
        }
        if (isNull(user.getLastName())) {
            user.setLastName("");
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

    public boolean isUserAdult(int birthDay, int birthMonth, int birthYear) {
        Date date = new Date();
        SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatterDay = new SimpleDateFormat("dd");
        int currentYear = Integer.parseInt(formatterYear.format(date));
        int currentMonth = Integer.parseInt(formatterMonth.format(date));
        int currentDay = Integer.parseInt(formatterDay.format(date));

        if (birthDay > currentDay) {
            currentMonth--;
        }
        if (birthMonth > currentMonth) {
            currentYear--;
        }
        return currentYear - birthYear >= ADULT_YEAR;
    }

    public boolean isNull(Object object) {
        return object == null;
    }
}
