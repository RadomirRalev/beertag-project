package com.beertag.demo.helpers;

import com.beertag.demo.models.user.UserDetail;

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

    public static void setOptionalFields(UserDetail userDetail) {
        if (isNull(userDetail.getFirstName()) || userDetail.getFirstName().equals("empty")) {
            userDetail.setFirstName("");
        }
        if (isNull(userDetail.getLastName()) || userDetail.getLastName().equals("empty")) {
            userDetail.setLastName("");
        }
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

}
