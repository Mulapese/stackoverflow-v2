package com.example.stackoverflow.controller;

import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.jwt.JwtTokenUtil;
import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.Question;
import com.example.stackoverflow.service.serviceImp.AccountServiceImpl;
import com.example.stackoverflow.service.serviceImp.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private QuestionServiceImpl service;
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> entities = service.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable("id") String id) {
        Optional<Question> entity = service.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        throw new RecordNotFoundException(ErrorMessage.notExist("Question with id = " + id));
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question question,
                                                @RequestHeader(name = "Authorization") String token) {
        Account account = accountService.getAccountFromToken(token);

        question.setAccountByAccountId(account);

        Question entity = service.insert(question);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") String id, @Valid @RequestBody Question accountEntity) {
        Question entity = service.update(id, accountEntity);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Question>> deleteQuestion(@PathVariable("id") String id) {
        Optional<Question> entity = service.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("flag/{id}")
    public ResponseEntity<Optional<Question>> flagQuestion(@PathVariable("id") String id) {
        int result = service.flag(id);

        if (result == 1) {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
