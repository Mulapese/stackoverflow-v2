package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>, JpaSpecificationExecutor<Answer> {
    List<Answer> findByQuestion_QuestionIdOrderByAnswerIdDesc(int questionId);

    Answer findByQuestion_QuestionIdAndAnswerId(int questionId, int answerId);
}