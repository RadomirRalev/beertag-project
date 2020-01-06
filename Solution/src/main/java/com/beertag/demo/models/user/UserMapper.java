package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import org.springframework.stereotype.Component;

import static com.beertag.demo.exceptions.Constants.*;
import static com.beertag.demo.helpers.UserRegistrationHelper.*;

@Component
public class UserMapper {

    public UserDetail validationData(UserRegistration userRegistration) {
        if (isUserAdult(userRegistration.getDay(), userRegistration.getMonth(), userRegistration.getBirthYear())) {

            UserDetail userDetail = new UserDetail(userRegistration.getFirstName(), userRegistration.getLastName(),
                    userRegistration.getUsername(), userRegistration.getEmail());

            setOptionalFields(userDetail);
            return userDetail;
        }

        throw new InvalidAgeException(NOT_ADULT);
    }

    public void validationData(UserUpdateDTO userUpdateDTO, UserDetail userDetail) {

        userDetail.setFirstName(userUpdateDTO.getFirstName());
        userDetail.setLastName(userUpdateDTO.getLastName());
        setOptionalFields(userDetail);
    }
}
