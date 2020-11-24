package com.example.stackoverflow.controller;

import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.Answer;
import com.example.stackoverflow.model.Comment;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.service.serviceImp.AccountServiceImpl;
import com.example.stackoverflow.service.serviceImp.AnswerServiceImpl;
import com.example.stackoverflow.service.serviceImp.CommentServiceImpl;
import com.example.stackoverflow.service.serviceImp.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.example.stackoverflow.common.ErrorMessage.notExist;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl service;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private AnswerServiceImpl answerServiceImpl;

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> entities = service.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> getCommentById(@PathVariable("id") String id) {
        Optional<Comment> entity = service.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/answerId/{answerId}/accountId/{accountId}")
    public ResponseEntity<Comment> addCommentToAnswer(@PathVariable("answerId") String answerId, @PathVariable("accountId") String accountId, @RequestBody Comment comment) {
        // Check account existed
        Optional<Account> account = accountService.findById(accountId);
        if (!account.isPresent()) {
            throw new RecordNotFoundException(notExist("accountId"));
        }

        // Check question existed
        Optional<Answer> answer = answerServiceImpl.findById(answerId);

        if (!answer.isPresent()) {
            throw new RecordNotFoundException(notExist("answerId"));
        }

        // Set account and answer to comment
        comment.setAccountByAccountId(account.get());
        comment.setAnswerByAnswerId(answer.get());

        Comment entity = service.insert(comment);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PostMapping("/questionId/{questionId}/accountId/{accountId}")
    public ResponseEntity<Comment> addCommentToQuestion(@PathVariable("questionId") String questionId, @PathVariable("accountId") String accountId, @RequestBody Comment comment) {
        // Check account existed
        Optional<Account> account = accountService.findById(accountId);
        if (!account.isPresent()) {
            throw new RecordNotFoundException(notExist("accountId"));
        }

        // Check question existed
        Optional<Question> question = questionServiceImpl.findById(questionId);

        if (!question.isPresent()) {
            throw new RecordNotFoundException(notExist("questionId"));
        }

        // Set account and question to comment
        comment.setAccountByAccountId(account.get());
        comment.setQuestionByQuestionId(question.get());

        Comment entity = service.insert(comment);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") String id, @Valid @RequestBody Comment accountEntity) {
        Comment entity = service.update(id, accountEntity);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Comment>> deleteComment(@PathVariable("id") String id) {
        Optional<Comment> entity = service.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
