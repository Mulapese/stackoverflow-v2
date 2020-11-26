package com.example.stackoverflow.model.builder;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Question;

import java.sql.Timestamp;

public class CommentBuilder {
    private String text;
    private Integer viewCount = 0;
    private Integer voteCount = 0;
    private Integer flagCount = 0;
    private Timestamp createdTime = Utils.getCurrentTimeStamp();
    private Timestamp updatedTime = Utils.getCurrentTimeStamp();
    private Question question;
    private Answer answer;
    private Account account;

    public CommentBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public CommentBuilder setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public CommentBuilder setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public CommentBuilder setFlagCount(Integer flagCount) {
        this.flagCount = flagCount;
        return this;
    }

    public CommentBuilder setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public CommentBuilder setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public CommentBuilder setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public CommentBuilder setAnswer(Answer answer) {
        this.answer = answer;
        return this;
    }

    public CommentBuilder setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Comment createComment() {
        return new Comment(text, viewCount, voteCount, flagCount, createdTime, updatedTime, question, answer, account);
    }
}