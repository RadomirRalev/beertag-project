package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import org.springframework.stereotype.Component;

import static com.beertag.demo.exceptions.Constants.*;
import static com.beertag.demo.helpers.UserRegistrationHelper.*;

@Component
public class UserMapper {

    public User validationData(UserRegistration userRegistration) {
        if (isUserAdult(userRegistration.getDay(), userRegistration.getMonth(), userRegistration.getBirthYear())) {

            setOptionalFields(userRegistration);

            return new User(userRegistration.getFirstName(), userRegistration.getLastName(),
                    userRegistration.getUsername(), userRegistration.getEmail(), userRegistration.getPassword());
        }

        throw new InvalidAgeException(NOT_ADULT);
    }

    public void validationData(UserUpdateDTO userUpdateDTO, User user) {

        setOptionalFields(userUpdateDTO);
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
    }
}
