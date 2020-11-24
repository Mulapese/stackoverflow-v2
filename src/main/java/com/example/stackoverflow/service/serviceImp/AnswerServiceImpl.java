package com.example.stackoverflow.service.serviceImp;

import com.example.stackoverflow.model.Answer;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.service.serviceInterface.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements CRUDService<Answer> {

    @Autowired
    private AnswerRepository repository;

    @Override
    public Answer insert(Answer answer) {
//        Answer answerUpdated = new Answer();
        return repository.save(answer);
    }

    @Override
    public List<Answer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Answer> findById(String id) {
        return repository.findById(Integer.parseInt(id));
    }

    @Override
    public Answer update(String id, Answer answer) {
        return null;
    }

    @Override
    public Optional<Answer> delete(String id) {
        return Optional.empty();
    }
}
