package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VoteRepository extends JpaRepository<Vote, Integer>, JpaSpecificationExecutor<Vote> {
    // SO-12
    Vote findByAccount_AccountIdAndQuestion_QuestionId(int accountId, int questionId);
    Vote findByAccount_AccountIdAndAnswer_AnswerId(int accountId, int answerId);
    Vote findByAccount_AccountIdAndComment_CommentId(int accountId, int commentId);
}