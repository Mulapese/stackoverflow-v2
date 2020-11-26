package com.example.stackoverflow.service.serviceInterface;

public interface CommentService {
    int setVoteOfComment(String token, String score, String commentId);
}
