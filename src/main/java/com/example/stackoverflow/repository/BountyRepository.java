package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.Bounty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BountyRepository extends JpaRepository<Bounty, Integer>, JpaSpecificationExecutor<Bounty> {

}