package com.beertag.demo.helpers;

import com.beertag.demo.exceptions.InvalidOptionalFieldParameter;
import com.beertag.demo.models.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.beertag.demo.exceptions.Constants.*;
import static java.lang.Integer.parseInt;

public class UserRegistrationHelper {
    private static final int ADULT_YEAR = 18;
    private static final int MIN_LENGTH = 3;
    private static final String ONLY_LETTERS = "^[a-zA-Z]+$";
    private static final String EMPTY_STRING = "";


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

    public static void setOptionalFields(User user) {
        if (!user.getFirstName().equals(EMPTY_STRING)) {
            if (user.getFirstName().length() < MIN_LENGTH) {
                throw new InvalidOptionalFieldParameter(FIRST_NAME_LENGTH_EXCEPTION);
            }
            if (!user.getFirstName().matches(ONLY_LETTERS)) {
                throw new InvalidOptionalFieldParameter(FIRST_NAME_REGEX_EXCEPTION);
            }
        }

        if (!user.getLastName().equals(EMPTY_STRING)) {
            if (user.getLastName().length() < MIN_LENGTH) {
                throw new InvalidOptionalFieldParameter(LAST_NAME_LENGTH_EXCEPTION);
            }
            if (!user.getLastName().matches(ONLY_LETTERS)) {
                throw new InvalidOptionalFieldParameter(LAST_NAME_REGEX_EXCEPTION);
            }
        }
    }
}