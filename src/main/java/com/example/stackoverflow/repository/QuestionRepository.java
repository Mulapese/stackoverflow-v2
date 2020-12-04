package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
    List<Question> findByOrderByQuestionIdDesc();

    @Query(value = "SELECT * FROM question WHERE title LIKE %?1% OR description LIKE  %?2%", nativeQuery = true)
    List<Question> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String content1, String content2);
}
