package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.StatusOfQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatusOfQuestionRepository extends JpaRepository<StatusOfQuestion, Integer>, JpaSpecificationExecutor<StatusOfQuestion> {

}