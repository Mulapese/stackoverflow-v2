package com.example.stackoverflow.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Integer convertStringToInteger(String numberString, String attribute) {
        try {
            return Integer.parseInt(numberString.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.mustBeInteger(attribute));
        } catch (NullPointerException e) {
            throw new NullPointerException(ErrorMessage.notNull(attribute));
        }
    }

    public static Integer convertStringToInteger(String numberString, String attribute, int min, int max) {
        try {
            int number = Integer.parseInt(numberString.trim());
            if (number < min || number > max) {
                throw new IllegalArgumentException(attribute + " must be in range: " + min + " => " + max);
            }
            return number;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.mustBeInteger(attribute));
        }
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        Date date = new Date();
        date.setTime(timestamp.getTime());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return formattedDate;
    }
}
