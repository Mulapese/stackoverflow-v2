package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.StatusOfAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StatusOfAccountRepository extends JpaRepository<StatusOfAccount, Integer>, JpaSpecificationExecutor<StatusOfAccount> {

}