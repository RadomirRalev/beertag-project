package com.beertag.demo.services;

import com.beertag.demo.exceptions.DuplicateEntityException;
import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.models.Beers;
import com.beertag.demo.models.User;
import com.beertag.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Service
public class UserServicesImpl implements UserServices {

    private static final String THIS_USER_ALREADY_EXIST = "This user already exist";
    private static final String EMAIL_ALREADY_EXIST = "Email already exist";
    private static final String NOT_ADULT = "You haven`t 18 years.";
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
    public void createUser(User user) {
        if (userExist(user.getUserName())) {
            throw new DuplicateEntityException(THIS_USER_ALREADY_EXIST);
        }

        if (emailExist(user.getEmail())){
            throw new DuplicateEntityException(EMAIL_ALREADY_EXIST);
        }

        if (!isUserAdult(user.getDay(), user.getMonth(),user.getBirthYear())){
            throw new InvalidAgeException(NOT_ADULT);
        }
        if (isNull(user.getFirstName())){
            user.setFirstName("");
        }
        if (isNull(user.getLastName())){
            user.setLastName("");
        }
        userRepository.createUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public boolean userExist(String name) {
        return userRepository.userExist(name);
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.emailExist(email) ;
    }

    public boolean isUserAdult(int day, int month, int year) {
        Date date = new Date();
        SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatterDay = new SimpleDateFormat("dd");
        int currentYear = Integer.parseInt(formatterYear.format(date));
        int currentMonth = Integer.parseInt(formatterMonth.format(date));
        int currentDay = Integer.parseInt(formatterDay.format(date));

        if (day > currentDay) {
            currentMonth--;
        }
        if (month > currentMonth) {
            currentYear--;
        }
        return currentYear - year >= ADULT_YEAR;
    }

    public boolean isNull(Object object) {
        return object == null;
    }
}
