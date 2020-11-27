package com.example.stackoverflow.controller;

import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.StatusOfQuestion;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.model.form.AnswerForm;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.model.form.QuestionForm;
import com.example.stackoverflow.model.view.AnswerView;
import com.example.stackoverflow.model.view.CommentQuestionView;
import com.example.stackoverflow.model.view.QuestionView;
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
    private QuestionServiceImpl service;

    @GetMapping
    public ResponseEntity<List<QuestionView>> getQuestions(@RequestParam(required = false) String content) {
        List<Question> questions;
        if (content != null) {
            questions = service.searchQuestionByTitleAndDescription(content);
        } else {
            questions = service.findAll();
        }

        if (questions.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        // Get question view list from question list
        List<QuestionView> questionViews = questions.stream()
                .map(question -> new QuestionView(question))
                .collect(Collectors.toList());

        return new ResponseEntity<>(questionViews, HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionView> getQuestionById(@PathVariable("questionId") String questionId) {
        Optional<Question> entity = service.findById(questionId);

        if (entity.isPresent()) {
            QuestionView questionView = new QuestionView(entity.get());
            return new ResponseEntity<>(questionView, HttpStatus.OK);
        }

        throw new RecordNotFoundException(ErrorMessage.notExist("Question with id = " + questionId));
    }

    @GetMapping("/{questionId}/answers")
    public ResponseEntity<List<AnswerView>> getAnswersOfQuestion(@PathVariable("questionId") String questionId) {
        List<Answer> answers = service.getAnswersOfQuestion(questionId);
        List<AnswerView> answerViews = answers.stream()
                .map(answer -> new AnswerView(answer))
                .collect(Collectors.toList());

        return new ResponseEntity<>(answerViews, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<AnswerView> getAnswerByIdOfQuestion(@PathVariable("questionId") String questionId,
                                                              @PathVariable("answerId") String answerId) {
        Answer answer = service.getAnswerByIdOfQuestion(questionId, answerId);

        AnswerView answerView = new AnswerView(answer);
        return new ResponseEntity<>(answerView, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/comments")
    public ResponseEntity<List<CommentQuestionView>> getCommentsOfQuestion(@PathVariable("questionId") String questionId) {
        List<Comment> comments = service.getCommentsOfQuestion(questionId);
        List<CommentQuestionView> commentQuestionViews = comments.stream()
                .map(comment -> new CommentQuestionView(comment))
                .collect(Collectors.toList());

        return new ResponseEntity<>(commentQuestionViews, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/comments/{commentId}")
    public ResponseEntity<CommentQuestionView> getCommentByIdOfQuestion(@PathVariable("questionId") String questionId,
                                                                        @PathVariable("commentId") String commentId) {
        Comment comment = service.getCommentByIdOfQuestion(questionId, commentId);

        CommentQuestionView commentQuestionView = new CommentQuestionView(comment);
        return new ResponseEntity<>(commentQuestionView, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/comments")
    public ResponseEntity<CommentForm> addCommentToQuestion(@PathVariable("questionId") String questionId,
                                                            @Valid @RequestBody CommentForm commentForm,
                                                            @RequestHeader(name = "Authorization") String token) {
        service.insertCommentToQuestion(token, questionId, commentForm);
        return new ResponseEntity<>(commentForm, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/answers")
    public ResponseEntity<AnswerForm> addAnswerToQuestion(@PathVariable("questionId") String questionId,
                                                          @Valid @RequestBody AnswerForm answerForm,
                                                          @RequestHeader(name = "Authorization") String token) {
        service.insertAnswerToQuestion(token, questionId, answerForm);
        return new ResponseEntity<>(answerForm, HttpStatus.OK);
    }

    @PatchMapping("/{questionId}/status/{statusId}")
    public ResponseEntity<String> updateStatus(@RequestHeader(name = "Authorization") String token,
                                               @PathVariable("statusId") String statusId,
                                               @PathVariable("questionId") String questionId) {
        StatusOfQuestion statusOfQuestion = service.setStatusOfQuestion(token, statusId, questionId);
        String message = "You has changed status of question with id " + questionId + " to \"" +
                statusOfQuestion.getDescription() + "\"";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QuestionForm> addQuestion(@Valid @RequestBody QuestionForm questionForm,
                                                    @RequestHeader(name = "Authorization") String token) {
        int result = service.insert(token, questionForm);

        //SO-02
        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(questionForm, HttpStatus.CREATED);
    }

    @PatchMapping("{questionId}/vote/{score}")
    public ResponseEntity<String> setVoteOfQuestion(@RequestHeader(name = "Authorization") String token,
                                                    @PathVariable("questionId") String questionId,
                                                    @PathVariable("score") String score) {
        service.setVoteOfQuestion(token, score, questionId);

        return new ResponseEntity<>("You has vote " + score + " for the question with id = " + questionId, HttpStatus.OK);
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
