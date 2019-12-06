package com.beertag.demo.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserRegistrationHelper {
    private static final int ADULT_YEAR = 18;

    public static boolean isUserAdult(int birthDay, int birthMonth, int birthYear) {
        Date date = new Date();
        SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatterDay = new SimpleDateFormat("dd");
        int currentYear = Integer.parseInt(formatterYear.format(date));
        int currentMonth = Integer.parseInt(formatterMonth.format(date));
        int currentDay = Integer.parseInt(formatterDay.format(date));

        if (birthDay > currentDay) {
            currentMonth--;
        }
        if (birthMonth > currentMonth) {
            currentYear--;
        }
        return currentYear - birthYear >= ADULT_YEAR;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }


}
