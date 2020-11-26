package com.example.stackoverflow.model.builder;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Question;

import java.sql.Timestamp;
import java.util.Collection;

public class AnswerBuilder {
    private String text;
    private Integer viewCount = 0;
    private Integer voteCount = 0;
    private Integer flagCount = 0;
    private Boolean isAccepted = false;
    private Timestamp createdTime = Utils.getCurrentTimeStamp();
    private Timestamp updatedTime = Utils.getCurrentTimeStamp();
    private Question question;
    private Account account;
    private Collection<Comment> comments;

    public AnswerBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public AnswerBuilder setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public AnswerBuilder setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public AnswerBuilder setFlagCount(Integer flagCount) {
        this.flagCount = flagCount;
        return this;
    }

    public AnswerBuilder setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
        return this;
    }

    public AnswerBuilder setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public AnswerBuilder setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public AnswerBuilder setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public AnswerBuilder setAccount(Account account) {
        this.account = account;
        return this;
    }

    public AnswerBuilder setComments(Collection<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Answer createAnswer() {
        return new Answer(text, viewCount, voteCount, flagCount, isAccepted, createdTime, updatedTime, question, account, comments);
    }
}