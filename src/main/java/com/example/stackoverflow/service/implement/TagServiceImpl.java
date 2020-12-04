package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.model.Tag;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.repository.TagRepository;
import com.example.stackoverflow.service.serviceInterface.common.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements CRUDService<Tag, Tag> {

    @Autowired
    private TagRepository repository;

    @Override
    public Tag insert(Account account, Tag tag) {
        return repository.save(tag);
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Tag> findById(String id) {
        return Optional.empty();
    }

    @Override
    public int update(String id, Tag tag) {
        return 1;
    }

    @Override
    public Optional<Tag> delete(String id) {
        return Optional.empty();
    }
}
