package com.example.stackoverflow.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

// SO-05
@Getter
@AllArgsConstructor
public class JwtResponse{
    private final String jwtToken;
}