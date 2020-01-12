package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.beertag.demo.exceptions.Constants.*;
import static com.beertag.demo.helpers.UserHelper.*;

@Component
public class UserMapper {

    private static final int ENABLED = 1;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User validationData(UserRegistrationDTO userRegistrationDTO) throws IOException {
        if (isUserAdult
                (userRegistrationDTO.getBirthDay(), userRegistrationDTO.getBirthMonth(), userRegistrationDTO.getBirthYear()))
        {
            User user = new User(userRegistrationDTO.getFirstName(),
                    userRegistrationDTO.getLastName(),
                    userRegistrationDTO.getUsername(),
                    userRegistrationDTO.getEmail(),
                    passwordEncoder.encode(userRegistrationDTO.getPassword()), ENABLED, userRegistrationDTO.getFile().getBytes());
            setOptionalFields(user);
            return user;
        }
        throw new InvalidAgeException(NOT_ADULT);
    }

    public void validationData(UserUpdateDTO userUpdateDTO, User user) throws IOException {
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setPicture(userUpdateDTO.getFile().getBytes());
        setOptionalFields(user);
    }
}
