package com.example.stackoverflow.model.view;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.entity.Comment;
import lombok.Getter;

@Getter
public class CommentQuestionView {
    private final int commentId;
    private final String email;
    private final int questionId;
    private final String text;
    private final Integer viewCount;
    private final Integer voteCount;
    private final Integer flagCount;
    private final String createdTime;
    private final String updatedTime;

    public CommentQuestionView(Comment comment) {
        commentId = comment.getCommentId();
        email = comment.getAccount().getEmail();
        questionId = comment.getQuestion().getQuestionId();
        text = comment.getText();
        viewCount = comment.getViewCount();
        voteCount = comment.getVoteCount();
        flagCount = comment.getFlagCount();
        createdTime = Utils.convertTimestampToString(comment.getCreatedTime());
        updatedTime = Utils.convertTimestampToString(comment.getUpdatedTime());
    }
}
