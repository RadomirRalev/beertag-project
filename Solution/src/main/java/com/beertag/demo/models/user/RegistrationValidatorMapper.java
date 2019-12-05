package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationValidatorMapper {

    private static final String NOT_ADULT = "User is under 18 years";

    private UserServices userServices;

    @Autowired
    public RegistrationValidatorMapper(UserServices userServices) {
        this.userServices = userServices;
    }

    public User fromDto(UserRegistrationValidation validation) {
        if (userServices.isUserAdult(validation.getDay(), validation.getMonth(), validation.getBirthYear())) {

            return new User(validation.getFirstName(), validation.getLastName(),
                    validation.getUserName(), validation.getEmail());
        }
        throw new InvalidAgeException(NOT_ADULT);
    }
}
