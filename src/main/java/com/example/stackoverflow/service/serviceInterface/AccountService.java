package com.example.stackoverflow.service.serviceInterface;

import com.example.stackoverflow.model.entity.Account;

public interface AccountService {
    Account findByEmail(String email);

    Account getAccountFromToken(String token);
}
