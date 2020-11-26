package com.example.stackoverflow.common;

import com.example.stackoverflow.exception.exceptionType.CommonException;

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
            throw new CommonException(ErrorMessage.mustBeInteger(message));
        }
    }

    public static String convertTimestampToString(Timestamp timestamp) {
        Date date = new Date();
        date.setTime(timestamp.getTime());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return formattedDate;
    }
}
