package com.beertag.demo.models.user;

import org.springframework.stereotype.Component;

import static com.beertag.demo.helpers.UserRegistrationHelper.*;

@Component
public class UserUpdateMapper {


    public void validationData(UserUpdateDTO userUpdateDTO, User user) {

        setOptionalFields(userUpdateDTO);
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());

    }
}
