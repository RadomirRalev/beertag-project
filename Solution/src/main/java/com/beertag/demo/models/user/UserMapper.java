package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.beertag.demo.exceptions.Constants.*;
import static com.beertag.demo.helpers.UserRegistrationHelper.*;

@Component
public class UserMapper {

    private static final int ENABLED = 1;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User validationData(UserRegistration userRegistration) {
        if (isUserAdult
                (userRegistration.getBirthDay(), userRegistration.getBirthMonth(), userRegistration.getBirthYear()))
        {
            User user = new User(userRegistration.getFirstName(),
                    userRegistration.getLastName(),
                    userRegistration.getUsername(),
                    userRegistration.getEmail(),
                    passwordEncoder.encode(userRegistration.getPassword()), ENABLED);
            setOptionalFields(user);
            return user;
        }
        throw new InvalidAgeException(NOT_ADULT);
    }

    public void validationData(UserUpdateDTO userUpdateDTO, User user) {
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        setOptionalFields(user);
    }
}
