package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTagRepository extends JpaRepository<QuestionTag, Integer>, JpaSpecificationExecutor<QuestionTag> {

}