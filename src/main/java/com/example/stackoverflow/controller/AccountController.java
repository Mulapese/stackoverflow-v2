package com.example.stackoverflow.controller;

//import com.example.stackoverflow.exception.RecordNotFoundException;
import com.example.stackoverflow.model.AccountEntity;
import com.example.stackoverflow.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountEntity>> getAccounts(){
        List<AccountEntity> entities = service.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AccountEntity>> getAccountById(@PathVariable("id") String id){
        Optional<AccountEntity> entity = service.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<AccountEntity> addAccount(@RequestBody AccountEntity accountEntity){
        AccountEntity entity = service.insert(accountEntity);

        if(entity == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable("id") String id, @RequestBody AccountEntity accountEntity){
        AccountEntity entity = service.update(id, accountEntity);

        if(entity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<AccountEntity>> deleteAccount(@PathVariable("id") String id){
        Optional<AccountEntity> entity = service.delete(id);

        if(entity.isPresent()){
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
