package com.example.stackoverflow.model.view;

import com.example.stackoverflow.model.entity.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class AnswerView {
    @JsonIgnore
    private final Answer answer;

    private int answerId;
    private String text;
    private Integer viewCount;
    private Integer voteCount;
    private Integer flagCount;
    private Boolean isAccepted;
    private int questionId;
    private String email;

    public AnswerView(Answer answer) {
        this.answer = answer;
    }

    public AnswerView create() {
        answerId = answer.getAnswerId();
        text = answer.getText();
        viewCount = answer.getViewCount();
        voteCount = answer.getVoteCount();
        flagCount = answer.getFlagCount();
        isAccepted = answer.getIsAccepted();
        questionId = answer.getQuestionByQuestionId().getQuestionId();
        email = answer.getAccountByAccountId().getEmail();
        return this;
    }
}
