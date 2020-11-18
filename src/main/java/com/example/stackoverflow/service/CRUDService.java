package com.example.stackoverflow.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {
    T insert(T t);
    List<T> findAll();
    Optional<T> findById(String id);
    T update(String id, T t);
    Optional<T> delete(String id);
}
