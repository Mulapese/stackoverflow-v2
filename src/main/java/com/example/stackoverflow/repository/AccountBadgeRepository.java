package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.AccountBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBadgeRepository extends JpaRepository<AccountBadge, Integer>, JpaSpecificationExecutor<AccountBadge> {

}