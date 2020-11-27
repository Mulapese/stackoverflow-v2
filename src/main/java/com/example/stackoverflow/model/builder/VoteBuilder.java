package com.example.stackoverflow.model.builder;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.entity.*;

import java.sql.Timestamp;

public class VoteBuilder {
    private int voteId;
    private Account account;
    private Question question;
    private Answer answer;
    private Comment comment;
    private int score;
    private Timestamp createdTime = Utils.getCurrentTimeStamp();
    private Timestamp updatedTime = Utils.getCurrentTimeStamp();

    public VoteBuilder setVoteId(int voteId) {
        this.voteId = voteId;
        return this;
    }

    public VoteBuilder setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public VoteBuilder setAnswer(Answer answer) {
        this.answer = answer;
        return this;
    }

    public VoteBuilder setComment(Comment comment) {
        this.comment = comment;
        return this;
    }

    public VoteBuilder setAccount(Account account) {
        this.account = account;
        return this;
    }

    public VoteBuilder setScore(int score) {
        this.score = score;
        return this;
    }

    public VoteBuilder setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public VoteBuilder setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public Vote createVote() {
        return new Vote(voteId, account, question, answer, comment, score, createdTime, updatedTime);
    }
}