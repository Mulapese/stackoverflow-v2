package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {

}