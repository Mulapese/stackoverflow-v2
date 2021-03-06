package com.example.stackoverflow.controller;

import com.example.stackoverflow.exception.exceptionType.AccountNotFoundException;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;


    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> entities = accountService.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable("id") String id) {
        Optional<Account> entity = accountService.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account account,
                                              @RequestHeader(name = "Authorization") String token) {
        Account result = accountService.insert(account, account);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") String id,
                                                 @Valid @RequestBody Account account,
                                                 @RequestHeader(name = "Authorization") String token) {
        int result = accountService.update(id, account);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Account>> deleteAccount(@PathVariable("id") String id) {
        Optional<Account> entity = accountService.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
