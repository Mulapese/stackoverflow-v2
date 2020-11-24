package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>, JpaSpecificationExecutor<Tag> {

}