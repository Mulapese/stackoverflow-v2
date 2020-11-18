package com.example.stackoverflow.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private Date timestamp;
    private List<String> message;
    private String details;
}