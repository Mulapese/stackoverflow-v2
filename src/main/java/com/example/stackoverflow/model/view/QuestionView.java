package com.example.stackoverflow.model.view;

import com.example.stackoverflow.model.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionView {
    @JsonIgnore
    private final Question question;

    private int questionId;
    private String email;
    private String title;
    private String description;
    private Integer viewCount;
    private Integer voteCloseCount;
    private Integer voteDeleteCount;
    private Integer flagCount;
    private String status;
    private int numberOfComment;
    private int numberOfAnswer;
    private String createdTime;

    public QuestionView(Question question) {
        this.question = question;
    }

    public QuestionView create() {
        email = question.getAccountByAccountId().getEmail();
        questionId = question.getQuestionId();
        title = question.getTitle();
        description = question.getDescription();
        viewCount = question.getViewCount();
        voteCloseCount = question.getVoteCloseCount();
        voteDeleteCount = question.getVoteDeleteCount();
        flagCount = question.getFlagCount();
        status = question.getStatusOfQuestionByStatusOfQuestionId().getDescription();
        numberOfComment = question.getCommentsByQuestionId().size();
        numberOfAnswer = question.getAnswersByQuestionId().size();
        Date date = new Date();
        date.setTime(question.getCreatedTime().getTime());
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        createdTime = formattedDate;
        return this;
    }

    public List<QuestionView> getQuestionViewListFromQuestionList(List<Question> questions) {
        return questions.stream().map(question -> new QuestionView(question).create()).collect(Collectors.toList());
    }
}
