package com.example.stackoverflow.service.serviceImp;

import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.Question;
import com.example.stackoverflow.model.QuestionBuilder;
import com.example.stackoverflow.model.StatusOfQuestion;
import com.example.stackoverflow.repository.QuestionRepository;
import com.example.stackoverflow.repository.StatusOfQuestionRepository;
import com.example.stackoverflow.service.serviceInterface.CRUDService;
import com.example.stackoverflow.service.serviceInterface.FlagCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements CRUDService<Question>, FlagCountService {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private StatusOfQuestionRepository statusOfQuestionRepository;

    @Override
    public Question insert(Question question) {
        // Default status: 1 (Open)
        Optional<StatusOfQuestion> statusOpen = statusOfQuestionRepository.findById(1);
        Question questionEdited = new QuestionBuilder().setTitle(question.getTitle())
                .setDescription(question.getDescription())
                .setCreatedTime(Utils.getCurrentTimeStamp())
                .setUpdatedTime(Utils.getCurrentTimeStamp())
                .setAccountByAccountId(question.getAccountByAccountId())
                .setStatusOfQuestionByStatusOfQuestionId(statusOpen.get())
                .createQuestion();
        return repository.save(questionEdited);
    }

    @Override
    public List<Question> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Question> findById(String id) {
        Optional<Question> questionOptional = repository.findById(Utils.stringToInteger(id, ErrorMessage.invalid("QuestionId")));

        // Update view count
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            if (question.getViewCount() == null) {
                question.setViewCount(0);
            }
            question.setViewCount(question.getViewCount() + 1);
            repository.save(question);
        }

        return questionOptional;
    }

    @Override
    public Question update(String id, Question question) {
        return null;
    }

    @Override
    public Optional<Question> delete(String id) {
        return Optional.empty();
    }

    @Override
    public int flag(String id) {
        Optional<Question> questionOptional = repository.findById(Integer.parseInt(id));
        setUpdatedTime(questionOptional.get());

        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setFlagCount(question.getFlagCount() + 1);
            return 1;
        }

        return 0;
    }

    public void setUpdatedTime(Question question) {
        question.setUpdatedTime(Utils.getCurrentTimeStamp());
    }
}
