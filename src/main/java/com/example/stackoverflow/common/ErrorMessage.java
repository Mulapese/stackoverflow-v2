package com.example.stackoverflow.common;

public final class ErrorMessage {

    public static String notExist(String s) {
        return s + " is not exist.";
    }

    public static String duplicate(String s) {
        return s + " is duplicate.";
    }

    public static String invalid(String s) {
        return s + " is invalid.";
    }

    public static String notMatch(String s) {
        return s + " you entered does not match the url.";
    }

    public static String notNull(String s) {
        return s + " cannot be null";
    }
}
