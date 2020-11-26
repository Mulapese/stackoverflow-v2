package com.example.stackoverflow.service.serviceInterface;

import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.form.CommentForm;

import java.util.List;

public interface AnswerService {
    List<Comment> getCommentsOfAnswer(String answerId);

    Comment getCommentByIdOfAnswer(String answerId, String commentId);

    int insertCommentToAnswer(String token, String answerId, CommentForm commentForm);

    int setVoteOfAnswer(String token, String score, String answerId);
}
