package com.example.stackoverflow.service.serviceImp;

import com.example.stackoverflow.model.Comment;
import com.example.stackoverflow.repository.CommentRepository;
import com.example.stackoverflow.service.serviceInterface.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CRUDService<Comment> {

    @Autowired
    private CommentRepository repository;

    @Override
    public Comment insert(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Comment> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Comment update(String id, Comment comment) {
        return null;
    }

    @Override
    public Optional<Comment> delete(String id) {
        return Optional.empty();
    }
}
