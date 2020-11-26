package com.example.stackoverflow.service.serviceInterface;

import java.util.List;
import java.util.Optional;

public interface CRUDService<Model, Form> {
    int insert(String token, Form t);

    List<Model> findAll();

    // SO-07
    Optional<Model> findById(String id);

    int update(String id, Model t);

    Optional<Model> delete(String id);
}
