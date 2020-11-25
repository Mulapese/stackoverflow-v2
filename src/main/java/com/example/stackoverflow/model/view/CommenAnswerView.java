package com.example.stackoverflow.model.view;

import com.example.stackoverflow.model.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class CommenAnswerView {
    @JsonIgnore
    private Comment comment;

    private int commentId;
    private String email;
    private int answerId;
    private String text;
    private Integer viewCount;
    private Integer voteCount;
    private Integer flagCount;

    public CommenAnswerView create() {
        commentId = comment.getCommentId();
        email = comment.getAccountByAccountId().getEmail();
        answerId = comment.getAnswerByAnswerId().getAnswerId();
        text = comment.getText();
        viewCount = comment.getViewCount();
        voteCount = comment.getVoteCount();
        flagCount = comment.getFlagCount();
        return this;
    }
}
