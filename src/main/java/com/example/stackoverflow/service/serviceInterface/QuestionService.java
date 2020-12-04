package com.example.stackoverflow.service.serviceInterface;

import com.example.stackoverflow.model.entity.*;
import com.example.stackoverflow.model.form.AnswerForm;
import com.example.stackoverflow.model.form.CommentForm;

import java.util.List;

public interface QuestionService {
    List<Answer> findAnswersOfQuestion(String questionId);

    Answer findAnswerByIdOfQuestion(String questionId, String answerId);

    List<Comment> findCommentsOfQuestion(String questionId);

    Comment findCommentByIdOfQuestion(String questionId, String commentId);

    Comment insertCommentToQuestion(Account account, Question question, CommentForm commentForm);

    Answer insertAnswerToQuestion(Account account, Question question, AnswerForm answerForm);

    List<Question> searchQuestionByTitleAndDescription(String content);

    StatusOfQuestion setStatusOfQuestion(String token, String statusId, String questionId);

    int setVoteOfQuestion(String token, String score, String questionId);
}
