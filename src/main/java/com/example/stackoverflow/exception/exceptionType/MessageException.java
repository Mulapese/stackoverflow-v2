package com.example.stackoverflow.exception.exceptionType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MessageException extends RuntimeException {
    public MessageException(String message) {
        super(message);
    }
}
