package com.example.stackoverflow.controller;

import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.jwt.JwtTokenUtil;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.model.form.QuestionForm;
import com.example.stackoverflow.model.view.QuestionView;
import com.example.stackoverflow.service.serviceImp.AccountServiceImpl;
import com.example.stackoverflow.service.serviceImp.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<QuestionView>> getQuestions() {
        List<Question> questions = service.findAll();

        if (questions.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        // Get question view list from question list
        List<QuestionView> questionViews = questions.stream()
                .map(question -> new QuestionView(question).create())
                .collect(Collectors.toList());

        return new ResponseEntity<>(questionViews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionView> getQuestionById(@PathVariable("id") String id) {
        Optional<Question> entity = service.findById(id);

        if (entity.isPresent()) {
            QuestionView questionView = new QuestionView(entity.get()).create();
            return new ResponseEntity<>(questionView, HttpStatus.OK);
        }
        // 404
        throw new RecordNotFoundException(ErrorMessage.notExist("Question with id = " + id));
    }

    @PostMapping
    public ResponseEntity<QuestionForm> addQuestion(@Valid @RequestBody QuestionForm questionForm,
                                                    @RequestHeader(name = "Authorization") String token) {
//        Account account = accountService.getAccountFromToken(token);
//
//        question.setAccountByAccountId(account);

        int result = service.insert(token, questionForm);

        //SO-02
        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(questionForm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") String id, @Valid @RequestBody Question question) {
        int result = service.update(id, question);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(question, HttpStatus.OK);
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
