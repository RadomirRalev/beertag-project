package com.beertag.demo.helpers;

import com.beertag.demo.models.user.UserRegistration;
import com.beertag.demo.models.user.UserUpdateDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class UserRegistrationHelper {
    private static final int ADULT_YEAR = 18;

    public static boolean isUserAdult(int birthDay, int birthMonth, int birthYear) {
        Date date = new Date();
        SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatterDay = new SimpleDateFormat("dd");
        int currentYear = parseInt(formatterYear.format(date));
        int currentMonth = parseInt(formatterMonth.format(date));
        int currentDay = parseInt(formatterDay.format(date));

        if (birthDay > currentDay) {
            currentMonth--;
        }
        if (birthMonth > currentMonth) {
            currentYear--;
        }
        return currentYear - birthYear >= ADULT_YEAR;
    }

    public static void setOptionalFields(UserRegistration userRegistration) {
        if (isNull(userRegistration.getFirstName())) {
            userRegistration.setFirstName("empty");
        }
        if (isNull(userRegistration.getLastName())) {
            userRegistration.setLastName("empty");
        }
    }

    public static void setOptionalFields(UserUpdateDTO userUpdateDTO) {
        if (isNull(userUpdateDTO.getFirstName())) {
            userUpdateDTO.setFirstName("empty");
        }
        if (isNull(userUpdateDTO.getLastName())) {
            userUpdateDTO.setLastName("empty");
        }
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

}
