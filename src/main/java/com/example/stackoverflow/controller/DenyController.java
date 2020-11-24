package com.example.stackoverflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deny")
public class DenyController {
    @GetMapping
    public ResponseEntity<String> deny() {
        return new ResponseEntity<>("Sorry, you do not have permission to access this api.", HttpStatus.NO_CONTENT);
    }
}
