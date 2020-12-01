package com.example.stackoverflow.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Integer convertStringToInteger(String numberString, String message) {
        try {
            return Integer.parseInt(numberString.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.mustBeInteger(message));
        } catch (NullPointerException e) {
            throw new NullPointerException(ErrorMessage.notNull(message));
        }
    }

    public static Integer convertStringToInteger(String numberString, String message, int min, int max) {
        try {
            int number = Integer.parseInt(numberString.trim());
            if (number < min || number > max) {
                throw new IllegalArgumentException(message + " must be in range: " + min + " => " + max);
            }
            return number;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ErrorMessage.mustBeInteger(message));
        }
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        Date date = new Date();
        date.setTime(timestamp.getTime());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return formattedDate;
    }
}
