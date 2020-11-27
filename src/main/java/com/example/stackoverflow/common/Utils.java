package com.example.stackoverflow.common;

import com.example.stackoverflow.exception.exceptionType.MessageException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Integer convertStringToInteger(String text, String message) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new MessageException(ErrorMessage.mustBeInteger(message));
        }
    }

    public static Integer convertStringToInteger(String text, String message, int min, int max) {
        try {
            int number = Integer.parseInt(text);
            if (number < min || number > max) {
                throw new MessageException(message + " must be in range: " + min + " => " + max);
            }
            return number;
        } catch (NumberFormatException e) {
            throw new MessageException(ErrorMessage.mustBeInteger(message));
        }
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        Date date = new Date();
        date.setTime(timestamp.getTime());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return formattedDate;
    }
}
