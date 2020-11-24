package com.example.stackoverflow.exception.exceptionType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CommonException extends RuntimeException {
    public CommonException(String message) {
        super(message);
    }
}
