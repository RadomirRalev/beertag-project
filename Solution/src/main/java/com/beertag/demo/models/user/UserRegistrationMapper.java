package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import org.springframework.stereotype.Component;

import static com.beertag.demo.exceptions.Constants.*;
import static com.beertag.demo.helpers.UserRegistrationHelper.*;

@Component
public class UserRegistrationMapper {

    public User validationData(UserRegistration userRegistration) {
        if (isUserAdult(userRegistration.getDay(), userRegistration.getMonth(), userRegistration.getBirthYear())) {

            setOptionalFields(userRegistration);

            return new User(userRegistration.getFirstName(), userRegistration.getLastName(),
                    userRegistration.getUserName(), userRegistration.getEmail());
        }

        throw new InvalidAgeException(NOT_ADULT);
    }
}
