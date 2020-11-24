package com.example.stackoverflow.service.serviceInterface;

import com.example.stackoverflow.model.Account;

public interface AccountService {
    Account findByEmail(String email);

    Account getAccountFromToken(String token);
}
