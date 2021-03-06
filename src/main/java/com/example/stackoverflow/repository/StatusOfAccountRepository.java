package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.entity.StatusOfAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOfAccountRepository extends JpaRepository<StatusOfAccount, Integer>, JpaSpecificationExecutor<StatusOfAccount> {

}