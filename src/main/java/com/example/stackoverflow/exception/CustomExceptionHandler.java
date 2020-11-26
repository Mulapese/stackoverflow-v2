package com.example.stackoverflow.exception;

import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.exception.exceptionType.WrongHeaderInfoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> allMatches = new ArrayList<>();

        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError error : objectErrors) {
            System.out.println(error.getDefaultMessage());
            allMatches.add(error.getDefaultMessage());
        }

        ErrorResponse errorDetails = new ErrorResponse(
                new Date(), allMatches, request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(
                new Date(),
                Arrays.asList(ex.getCause().getCause().getMessage()),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleCommonException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(new Date(), Arrays.asList(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    // Handle Json body request wrong format
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse error = new ErrorResponse(new Date(), Arrays.asList(ex.getCause().getLocalizedMessage()), request.getDescription(false));
        return new ResponseEntity<>(error, status);
    }
}