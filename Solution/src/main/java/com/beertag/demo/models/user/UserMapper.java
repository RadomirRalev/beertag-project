package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import org.springframework.stereotype.Component;

import static com.beertag.demo.exceptions.Constants.*;
import static com.beertag.demo.helpers.UserRegistrationHelper.*;

@Component
public class UserMapper {

    public User validationData(UserRegistration userRegistration) {
        if (isUserAdult(userRegistration.getDay(), userRegistration.getMonth(), userRegistration.getBirthYear())) {

            User user = new User(userRegistration.getFirstName(), userRegistration.getLastName(),
                    userRegistration.getUsername(), userRegistration.getEmail(), userRegistration.getPassword());

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
