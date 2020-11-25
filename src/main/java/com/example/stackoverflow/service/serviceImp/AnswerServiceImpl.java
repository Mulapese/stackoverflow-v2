package com.example.stackoverflow.service.serviceImp;

import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.service.serviceInterface.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements CRUDService<Answer, Answer> {

    @Autowired
    private AnswerRepository repository;

    @Override
    public int insert(String token, Answer answer) {
//        Answer answerUpdated = new Answer();
        repository.save(answer);
        return 1;
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
    public int update(String id, Answer answer) {
        return 1;
    }

    @Override
    public Optional<Answer> delete(String id) {
        return Optional.empty();
    }
}
