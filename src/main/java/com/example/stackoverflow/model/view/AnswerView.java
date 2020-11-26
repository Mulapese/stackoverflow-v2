package com.example.stackoverflow.model.view;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.entity.Answer;
import lombok.Getter;

@Getter
public class AnswerView {
    private final int answerId;
    private final String email;
    private final String text;
    private final Integer viewCount;
    private final Integer voteCount;
    private final Integer flagCount;
    private final Boolean isAccepted;
    private final int questionId;
    private final String createdTime;

    public AnswerView(Answer answer) {
        answerId = answer.getAnswerId();
        email = answer.getAccount().getEmail();
        text = answer.getText();
        viewCount = answer.getViewCount();
        voteCount = answer.getVoteCount();
        flagCount = answer.getFlagCount();
        isAccepted = answer.getIsAccepted();
        questionId = answer.getQuestion().getQuestionId();
        createdTime = Utils.convertTimestampToString(answer.getCreatedTime());
    }
}
