package com.example.stackoverflow.controller;

import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.model.view.CommentAnswerView;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import com.example.stackoverflow.service.implement.AnswerServiceImpl;
import com.example.stackoverflow.service.implement.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/{answerId}")
    public ResponseEntity<Optional<Answer>> getAnswerById(@PathVariable("answerId") String id) {
        Optional<Answer> entity = service.findById(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{answerId}/comments")
    public ResponseEntity<List<CommentAnswerView>> getCommentsOfQuestion(@PathVariable("answerId") String answerId) {
        List<Comment> comments = service.getCommentsOfAnswer(answerId);
        List<CommentAnswerView> commentAnswerViews = comments.stream()
                .map(comment -> new CommentAnswerView(comment))
                .collect(Collectors.toList());

        return new ResponseEntity<>(commentAnswerViews, HttpStatus.OK);
    }

    @GetMapping("/{answerId}/comments/{commentId}")
    public ResponseEntity<CommentAnswerView> getCommentByIdOfQuestion(@PathVariable("answerId") String answerId,
                                                                      @PathVariable("commentId") String commentId) {
        Comment comment = service.getCommentByIdOfAnswer(answerId, commentId);

        CommentAnswerView commentAnswerView = new CommentAnswerView(comment);
        return new ResponseEntity<>(commentAnswerView, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/comments")
    public ResponseEntity<CommentForm> addCommentToQuestion(@PathVariable("answerId") String answerId,
                                                            @Valid @RequestBody CommentForm commentForm,
                                                            @RequestHeader(name = "Authorization") String token) {
        service.insertCommentToAnswer(token, answerId, commentForm);
        return new ResponseEntity<>(commentForm, HttpStatus.OK);
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

        answer.setAccount(account);
        answer.setQuestion(question.get());

        int result = service.insert(token, answer);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @PatchMapping("{answerId}/vote/{score}")
    public ResponseEntity<String> setVoteOfQuestion(@RequestHeader(name = "Authorization") String token,
                                                    @PathVariable("answerId") String answerId,
                                                    @PathVariable("score") String score){
        int result = service.setVoteOfAnswer(token, score, answerId);

        return new ResponseEntity<>("Done", HttpStatus.OK);
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
