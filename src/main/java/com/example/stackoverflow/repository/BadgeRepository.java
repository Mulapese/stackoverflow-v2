package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BadgeRepository extends JpaRepository<Badge, Integer>, JpaSpecificationExecutor<Badge> {

}