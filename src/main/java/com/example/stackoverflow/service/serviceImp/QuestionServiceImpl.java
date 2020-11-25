package com.example.stackoverflow.service.serviceImp;

import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.StatusOfQuestion;
import com.example.stackoverflow.model.builder.QuestionBuilder;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.model.form.QuestionForm;
import com.example.stackoverflow.repository.QuestionRepository;
import com.example.stackoverflow.repository.StatusOfQuestionRepository;
import com.example.stackoverflow.service.serviceInterface.CRUDService;
import com.example.stackoverflow.service.serviceInterface.FlagCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements CRUDService<Question, QuestionForm>, FlagCountService {

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private StatusOfQuestionRepository statusOfQuestionRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @Override
    public int insert(String token, QuestionForm questionForm) {
        Account account = accountService.getAccountFromToken(token);

        // Default status: 1 (Open)
        // SO-03
        Optional<StatusOfQuestion> statusOpen = statusOfQuestionRepository.findById(1);
        Question questionEdited = new QuestionBuilder().setTitle(questionForm.getTitle())
                .setDescription(questionForm.getDescription())
                .setCreatedTime(Utils.getCurrentTimeStamp())
                .setUpdatedTime(Utils.getCurrentTimeStamp())
                .setAccountByAccountId(account)
                .setStatusOfQuestionByStatusOfQuestionId(statusOpen.get())
                .createQuestion();
        repository.save(questionEdited);
        return 1;
    }

    @Override
    public List<Question> findAll() {
        return repository.findByOrderByQuestionIdDesc();
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
    public int update(String id, Question question) {
        return 1;
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
