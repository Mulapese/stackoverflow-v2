package com.example.stackoverflow.model.builder;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.Bounty;
import com.example.stackoverflow.model.StatusOfQuestion;
import com.example.stackoverflow.model.entity.Question;

import java.sql.Timestamp;

// SO-01: Nên merge với entity
public class QuestionBuilder {
    private String title;
    private String description;
    private Integer viewCount = 0;
    private Integer voteCloseCount = 0;
    private Integer voteDeleteCount = 0;
    private Integer flagCount = 0;
    private Timestamp createdTime = Utils.getCurrentTimeStamp();
    private Timestamp updatedTime = Utils.getCurrentTimeStamp();
    private Bounty bountyByBountyId;
    private Account accountByAccountId;
    private StatusOfQuestion statusOfQuestionByStatusOfQuestionId;

    public QuestionBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public QuestionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public QuestionBuilder setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public QuestionBuilder setVoteCloseCount(Integer voteCloseCount) {
        this.voteCloseCount = voteCloseCount;
        return this;
    }

    public QuestionBuilder setVoteDeleteCount(Integer voteDeleteCount) {
        this.voteDeleteCount = voteDeleteCount;
        return this;
    }

    public QuestionBuilder setFlagCount(Integer flagCount) {
        this.flagCount = flagCount;
        return this;
    }

    public QuestionBuilder setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public QuestionBuilder setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public QuestionBuilder setBountyByBountyId(Bounty bountyByBountyId) {
        this.bountyByBountyId = bountyByBountyId;
        return this;
    }

    public QuestionBuilder setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
        return this;
    }

    public QuestionBuilder setStatusOfQuestionByStatusOfQuestionId(StatusOfQuestion statusOfQuestionByStatusOfQuestionId) {
        this.statusOfQuestionByStatusOfQuestionId = statusOfQuestionByStatusOfQuestionId;
        return this;
    }

    public Question createQuestion() {
        return new Question(title, description, viewCount, voteCloseCount, voteDeleteCount, flagCount, createdTime, updatedTime, bountyByBountyId, accountByAccountId, statusOfQuestionByStatusOfQuestionId);
    }
}