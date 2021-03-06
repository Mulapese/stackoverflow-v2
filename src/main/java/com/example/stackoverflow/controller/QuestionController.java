package com.example.stackoverflow.controller;

import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.exception.exceptionType.AccountNotFoundException;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.entity.*;
import com.example.stackoverflow.model.form.AnswerForm;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.model.form.QuestionForm;
import com.example.stackoverflow.model.view.AnswerView;
import com.example.stackoverflow.model.view.CommentQuestionView;
import com.example.stackoverflow.model.view.QuestionView;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import com.example.stackoverflow.service.implement.QuestionServiceImpl;
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
    private QuestionServiceImpl questionService;

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public ResponseEntity<List<QuestionView>> getQuestions(@RequestParam(required = false) String content) {
        List<Question> questions;
        if (content != null) {
            content = content.trim();
            if (content.equals("")) {
                throw new IllegalArgumentException(ErrorMessage.notEmpty("Content"));
            }
            questions = questionService.searchQuestionByTitleAndDescription(content);
        } else { // content == null when url don't have RequestParam <content>
            questions = questionService.findAll();
        }

        if (questions.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        // Throw exception if questions == null
        // Get question view list from question list
        List<QuestionView> questionViews = questions.stream()
                .map(question -> new QuestionView(question))
                .collect(Collectors.toList());

        return new ResponseEntity<>(questionViews, HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionView> getQuestionById(@PathVariable("questionId") String questionId) {
        Optional<Question> entity = questionService.findById(questionId);

        if (entity.isPresent()) {
            QuestionView questionView = new QuestionView(entity.get());
            return new ResponseEntity<>(questionView, HttpStatus.OK);
        }

        throw new RecordNotFoundException(ErrorMessage.notExist("Question with id = " + questionId));
    }

    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<AnswerView>> getAnswersOfQuestion(@PathVariable("questionId") String questionId) {
        List<Answer> answers = questionService.findAnswersOfQuestion(questionId);
        List<AnswerView> answerViews = answers.stream()
                .map(answer -> new AnswerView(answer))
                .collect(Collectors.toList());

        return new ResponseEntity<>(answerViews, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<AnswerView> getAnswerByIdOfQuestion(@PathVariable("questionId") String questionId,
                                                              @PathVariable("answerId") String answerId) {
        Answer answer = questionService.findAnswerByIdOfQuestion(questionId, answerId);

        AnswerView answerView = new AnswerView(answer);
        return new ResponseEntity<>(answerView, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/comments")
    public ResponseEntity<List<CommentQuestionView>> getCommentsOfQuestion(@PathVariable("questionId") String questionId) {
        List<Comment> comments = questionService.findCommentsOfQuestion(questionId);
        List<CommentQuestionView> commentQuestionViews = comments.stream()
                .map(comment -> new CommentQuestionView(comment))
                .collect(Collectors.toList());

        return new ResponseEntity<>(commentQuestionViews, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/comments/{commentId}")
    public ResponseEntity<CommentQuestionView> getCommentByIdOfQuestion(@PathVariable("questionId") String questionId,
                                                                        @PathVariable("commentId") String commentId) {
        Comment comment = questionService.findCommentByIdOfQuestion(questionId, commentId);

        CommentQuestionView commentQuestionView = new CommentQuestionView(comment);
        return new ResponseEntity<>(commentQuestionView, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/comments")
    public ResponseEntity<CommentQuestionView> addCommentToQuestion(@PathVariable("questionId") String questionId,
                                                            @Valid @RequestBody CommentForm commentForm,
                                                            @RequestHeader(name = "Authorization") String token) {
        Account account = accountService.getAccountFromToken(token);
        if (account == null) {
            throw new AccountNotFoundException("The email or password is wrong. Please authenticate again.");
        }

        Optional<Question> question = questionService.findById(questionId);
        if (!question.isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("The questionId " + questionId));
        }

        Comment comment = questionService.insertCommentToQuestion(account, question.get(), commentForm);
        CommentQuestionView commentQuestionView = new CommentQuestionView(comment);
        return new ResponseEntity<>(commentQuestionView, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity<AnswerView> addAnswerToQuestion(@PathVariable("questionId") String questionId,
                                                          @Valid @RequestBody AnswerForm answerForm,
                                                          @RequestHeader(name = "Authorization") String token) {
        // Get account
        Account account = accountService.getAccountFromToken(token);
        if (account == null) {
            throw new AccountNotFoundException("The email or password is wrong. Please authenticate again.");
        }

        // Get question
        Optional<Question> question = questionService.findById(questionId);
        if (!question.isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("The questionId " + questionId));
        }

        Answer answer = questionService.insertAnswerToQuestion(account, question.get(), answerForm);
        AnswerView answerView = new AnswerView(answer);
        return new ResponseEntity<>(answerView, HttpStatus.OK);
    }

    @PatchMapping("/{questionId}/status/{statusId}")
    public ResponseEntity<String> updateStatus(@RequestHeader(name = "Authorization") String token,
                                               @PathVariable("statusId") String statusId,
                                               @PathVariable("questionId") String questionId) {
        StatusOfQuestion statusOfQuestion = questionService.setStatusOfQuestion(token, statusId, questionId);
        String message = "You has changed status of question with id " + questionId + " to \"" +
                statusOfQuestion.getDescription() + "\"";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QuestionView> addQuestion(@Valid @RequestBody QuestionForm questionForm,
                                                    @RequestHeader(name = "Authorization") String token) {
        Account account = accountService.getAccountFromToken(token);
        if (account == null) {
            throw new AccountNotFoundException("The email or password is wrong. Please authenticate again.");
        }
        Question question = questionService.insert(account, questionForm);

        //SO-02
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        QuestionView questionView = new QuestionView(question);

        return new ResponseEntity<>(questionView, HttpStatus.CREATED);
    }

    @PatchMapping("{questionId}/vote/{score}")
    public ResponseEntity<String> setVoteOfQuestion(@RequestHeader(name = "Authorization") String token,
                                                    @PathVariable("questionId") String questionId,
                                                    @PathVariable("score") String score) {
        questionService.setVoteOfQuestion(token, score, questionId);

        return new ResponseEntity<>("You has vote " + score + " for the question with id = " + questionId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") String id, @Valid @RequestBody Question question) {
        int result = questionService.update(id, question);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Question>> deleteQuestion(@PathVariable("id") String id) {
        Optional<Question> entity = questionService.delete(id);

        if (entity.isPresent()) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("flag/{id}")
    public ResponseEntity<Optional<Question>> flagQuestion(@PathVariable("id") String id) {
        int result = questionService.flag(id);

        if (result == 1) {
            return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
