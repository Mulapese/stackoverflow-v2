package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UrlRepository extends JpaRepository<Url, Integer>, JpaSpecificationExecutor<Url> {

}