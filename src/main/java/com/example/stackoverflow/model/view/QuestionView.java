package com.example.stackoverflow.model.view;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.entity.Question;
import lombok.Getter;

@Getter
public class QuestionView {
    private final int questionId;
    private final String email;
    private final String title;
    private final String description;
    private final Integer viewCount;
    private final Integer voteCount;
    private final Integer voteDeleteCount;
    private final Integer flagCount;
    private final String status;
    private final int numberOfComment;
    private final int numberOfAnswer;
    private final String createdTime;
    private final String updatedTime;

    public QuestionView(Question question) {
        email = question.getAccount().getEmail();
        questionId = question.getQuestionId();
        title = question.getTitle();
        description = question.getDescription();
        viewCount = question.getViewCount();
        voteCount = question.getVoteCount();
        voteDeleteCount = question.getVoteDeleteCount();
        flagCount = question.getFlagCount();
        status = question.getStatusOfQuestion().getDescription();
        if (question.getComments() != null) {
            numberOfComment = question.getComments().size();
        } else {
            numberOfComment = 0;
        }
        if (question.getComments() != null){
            numberOfAnswer = question.getAnswers().size();
        } else {
            numberOfAnswer = 0;
        }
        createdTime = Utils.convertTimestampToString(question.getCreatedTime());
        updatedTime = Utils.convertTimestampToString(question.getUpdatedTime());
    }
}
