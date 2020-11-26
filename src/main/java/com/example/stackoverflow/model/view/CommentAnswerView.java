package com.example.stackoverflow.model.view;

import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.entity.Comment;
import lombok.Getter;

@Getter
public class CommentAnswerView {
    private final int commentId;
    private final String email;
    private final int answerId;
    private final String text;
    private final Integer viewCount;
    private final Integer voteCount;
    private final Integer flagCount;
    private final String createdTime;

    public CommentAnswerView(Comment comment) {
        commentId = comment.getCommentId();
        email = comment.getAccount().getEmail();
        answerId = comment.getAnswer().getAnswerId();
        text = comment.getText();
        viewCount = comment.getViewCount();
        voteCount = comment.getVoteCount();
        flagCount = comment.getFlagCount();
        createdTime = Utils.convertTimestampToString(comment.getCreatedTime());

    }
}
