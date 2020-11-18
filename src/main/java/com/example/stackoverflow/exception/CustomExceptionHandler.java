package com.example.stackoverflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(new Date(), Arrays.asList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongHeaderInfoException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(WrongHeaderInfoException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(new Date(), Arrays.asList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidFieldExceptions(Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), Arrays.asList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("Message='(.*?)'")
                .matcher(message);
        while (m.find()) {
            allMatches.add(m.group(1));
        }

        ErrorResponse errorDetails = new ErrorResponse(new Date(), allMatches, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}