package com.example.stackoverflow.exception.exceptionType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongHeaderInfoException extends RuntimeException {
    public WrongHeaderInfoException(String message) {
        super(message);
    }
}