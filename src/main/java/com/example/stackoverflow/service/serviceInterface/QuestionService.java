package com.example.stackoverflow.service.serviceInterface;

import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.model.entity.StatusOfQuestion;
import com.example.stackoverflow.model.form.AnswerForm;
import com.example.stackoverflow.model.form.CommentForm;

import java.util.List;

public interface QuestionService {
    List<Answer> findAnswersOfQuestion(String questionId);

    Answer findAnswerByIdOfQuestion(String questionId, String answerId);

    List<Comment> findCommentsOfQuestion(String questionId);

    Comment findCommentByIdOfQuestion(String questionId, String commentId);

    int insertCommentToQuestion(String token, String questionId, CommentForm commentForm);

    int insertAnswerToQuestion(String token, String answerId, AnswerForm answerForm);

    List<Question> searchQuestionByTitleAndDescription(String content);

    StatusOfQuestion setStatusOfQuestion(String token, String statusId, String questionId);

    int setVoteOfQuestion(String token, String score, String questionId);
}
