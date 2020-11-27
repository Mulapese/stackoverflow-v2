package com.example.stackoverflow.config;

import lombok.Getter;
import lombok.Setter;

// SO-05
@Getter
@Setter
public class JwtRequest {
    private String username;
    private String password;
}