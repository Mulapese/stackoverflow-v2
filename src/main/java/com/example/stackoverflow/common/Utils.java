package com.example.stackoverflow.common;

import com.example.stackoverflow.exception.exceptionType.CommonException;

import java.sql.Timestamp;

public class Utils {
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Integer stringToInteger(String text, String message) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new CommonException(message);
        }
    }
}
