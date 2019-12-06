package com.beertag.demo.models.user;

import com.beertag.demo.exceptions.InvalidAgeException;
import com.beertag.demo.helpers.UserRegistrationHelper;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    private static final String NOT_ADULT = "User is under 18 years";

    public User isDataCorrect(UserDto userDto) {
        if (UserRegistrationHelper.isUserAdult(userDto.getDay(), userDto.getMonth(), userDto.getBirthYear())) {
            checkOptionalForNull(userDto);
            return new User(userDto.getFirstName(), userDto.getLastName(),
                    userDto.getUserName(), userDto.getEmail());
        }
        throw new InvalidAgeException(NOT_ADULT);
    }

    private UserDto checkOptionalForNull (UserDto userDto) {
        if (UserRegistrationHelper.isNull(userDto.getFirstName())){
            userDto.setFirstName("");
        }
        if (UserRegistrationHelper.isNull(userDto.getLastName())){
            userDto.setLastName("");
        }
        return userDto;
    }
}
