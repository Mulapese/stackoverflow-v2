package com.example.stackoverflow.controller;

import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import com.example.stackoverflow.service.implement.AnswerServiceImpl;
import com.example.stackoverflow.service.implement.CommentServiceImpl;
import com.example.stackoverflow.service.implement.QuestionServiceImpl;
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
    private CommentServiceImpl commentService;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private AnswerServiceImpl answerServiceImpl;

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> entities = commentService.findAll();

        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> getCommentById(@PathVariable("id") String id) {
        Optional<Comment> entity = commentService.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping("/answerId/{answerId}/accountId/{accountId}")
//    public ResponseEntity<Comment> addCommentToAnswer(@PathVariable("answerId") String answerId,
//                                                      @PathVariable("accountId") String accountId,
//                                                      @RequestBody Comment comment,
//                                                      @RequestHeader(name = "Authorization") String token) {
//        // Check account existed
//        Optional<Account> account = accountService.findById(accountId);
//        if (!account.isPresent()) {
//            throw new RecordNotFoundException(notExist("accountId"));
//        }
//
//        // Check question existed
//        Optional<Answer> answer = answerServiceImpl.findById(answerId);
//
//        if (!answer.isPresent()) {
//            throw new RecordNotFoundException(notExist("answerId"));
//        }
//
//        // Set account and answer to comment
//        comment.setAccount(account.get());
//        comment.setAnswer(answer.get());
//
//        int result = commentService.insert(token, comment);
//
//        if (result == 0) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        return new ResponseEntity<>(comment, HttpStatus.CREATED);
//    }

//    @PostMapping("/questionId/{questionId}/accountId/{accountId}")
//    public ResponseEntity<Comment> addCommentToQuestion(@PathVariable("questionId") String questionId,
//                                                        @PathVariable("accountId") String accountId,
//                                                        @RequestBody Comment comment,
//                                                        @RequestHeader(name = "Authorization") String token) {
//        // Check account existed
//        Optional<Account> account = accountService.findById(accountId);
//        if (!account.isPresent()) {
//            throw new RecordNotFoundException(notExist("accountId"));
//        }
//
//        // Check question existed
//        Optional<Question> question = questionServiceImpl.findById(questionId);
//
//        if (!question.isPresent()) {
//            throw new RecordNotFoundException(notExist("questionId"));
//        }
//
//        // Set account and question to comment
//        comment.setAccount(account.get());
//        comment.setQuestion(question.get());
//
//        int result = commentService.insert(token, comment);
//
//        if (result == 0) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        return new ResponseEntity<>(comment, HttpStatus.CREATED);
//    }

    @PatchMapping("{commentId}/vote/{score}")
    public ResponseEntity<String> setVoteOfQuestion(@RequestHeader(name = "Authorization") String token,
                                                    @PathVariable("commentId") String commentId,
                                                    @PathVariable("score") String score) {
        int result = commentService.setVoteOfComment(token, score, commentId);

        return new ResponseEntity<>("You has vote " + score + " for the comment with id = " + commentId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") String id, @Valid @RequestBody Comment comment) {
        int result = commentService.update(id, comment);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Comment>> deleteComment(@PathVariable("id") String id) {
        Optional<Comment> entity = commentService.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
