package com.example.stackoverflow.controller;

import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.service.serviceImp.AccountServiceImpl;
import com.example.stackoverflow.service.serviceImp.AnswerServiceImpl;
import com.example.stackoverflow.service.serviceImp.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerServiceImpl service;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers() {
        List<Answer> entities = service.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Answer>> getAnswerById(@PathVariable("id") String id) {
        Optional<Answer> entity = service.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/questionId/{questionId}")
    public ResponseEntity<Answer> addAnswer(@PathVariable("questionId") String questionId, @RequestBody Answer answer,
                                            @RequestHeader(name = "Authorization") String token) {
        Account account = accountService.getAccountFromToken(token);

        // Check question exist
        Optional<Question> question = questionServiceImpl.findById(questionId);
        if (!question.isPresent()) {
            throw new RecordNotFoundException("The questionId is not existed.");
        }

        answer.setAccountByAccountId(account);
        answer.setQuestionByQuestionId(question.get());

        int result = service.insert(token, answer);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable("id") String id, @Valid @RequestBody Answer answer) {
        int result = service.update(id, answer);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Answer>> deleteAnswer(@PathVariable("id") String id) {
        Optional<Answer> entity = service.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
