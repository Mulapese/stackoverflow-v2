package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
    List<Comment> findByQuestion_QuestionIdOrderByCommentIdDesc(int questionId);

    Comment findByQuestion_QuestionIdAndCommentId(int questionId, int commentId);

    List<Comment> findByAnswer_AnswerIdOrderByCommentIdDesc(int answerId);

    Comment findByAnswer_AnswerIdAndCommentId(int answerId, int commentId);
}