package com.example.stackoverflow.service.serviceInterface.common;

import com.example.stackoverflow.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface CRUDService<Model, Form> {
    Model insert(Account account, Form t);

    List<Model> findAll();

    // SO-07
    Optional<Model> findById(String id);

    int update(String id, Model t);

    Optional<Model> delete(String id);
}
