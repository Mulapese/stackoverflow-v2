package com.example.stackoverflow.model.view;

import com.example.stackoverflow.model.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class CommentQuestionView {
    @JsonIgnore
    private Comment comment;

    private int commentId;
    private String email;
    private int questionId;
    private String text;
    private Integer viewCount;
    private Integer voteCount;
    private Integer flagCount;

    public CommentQuestionView create() {
        commentId = comment.getCommentId();
        email = comment.getAccountByAccountId().getEmail();
        questionId = comment.getQuestionByQuestionId().getQuestionId();
        text = comment.getText();
        viewCount = comment.getViewCount();
        voteCount = comment.getVoteCount();
        flagCount = comment.getFlagCount();
        return this;
    }
}
