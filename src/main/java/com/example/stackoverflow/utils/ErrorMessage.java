package com.example.stackoverflow.utils;

public final class ErrorMessage {

    public static String notExist(String s) {
        return "The " + s + " is not exist.";
    }

    public static String duplicate(String s){
        return "The " + s + " is duplicate.";
    }

    public static String invalid(String s){
        return "The " + s + " is invalid.";
    }

    public static String notMatch(String s) {
        return "The " + s + " you entered does not match the url.";
    }
}
