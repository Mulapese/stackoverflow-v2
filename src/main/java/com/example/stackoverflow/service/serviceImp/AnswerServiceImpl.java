package com.example.stackoverflow.service.serviceImp;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.Account;
import com.example.stackoverflow.model.builder.CommentBuilder;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.repository.CommentRepository;
import com.example.stackoverflow.service.serviceInterface.AnswerService;
import com.example.stackoverflow.service.serviceInterface.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements CRUDService<Answer, Answer>, AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccountServiceImpl accountService;

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

    @Override
    public List<Comment> getCommentsOfAnswer(String answerId) {
        validateAnswer(answerId);
        int answerIdInt = Utils.convertStringToInteger(answerId, "answerId");
        return commentRepository.findByAnswer_AnswerIdOrderByCommentIdDesc(answerIdInt);
    }

    @Override
    public Comment getCommentByIdOfAnswer(String answerId, String commentId) {
        validateAnswer(answerId);
        int answerIdInt = Utils.convertStringToInteger(answerId, "answerId");
        int commentIdInt = Utils.convertStringToInteger(commentId, "commentId");
        Comment comment = commentRepository.findByAnswer_AnswerIdAndCommentId(answerIdInt, commentIdInt);

        if (comment == null) {
            throw new RecordNotFoundException("The question with answerId = " + answerId +
                    " is not contains the comment with commentId = " + commentId);
        }
        return commentRepository.findByAnswer_AnswerIdAndCommentId(answerIdInt, commentIdInt);
    }

    @Override
    public int insertCommentToAnswer(String token, String answerId, CommentForm commentForm) {
        Account account = accountService.getAccountFromToken(token);
        Answer answer = validateAnswer(answerId);

        Comment comment = new CommentBuilder().setAccount(account)
                .setAnswer(answer).setText(commentForm.getText()).createComment();
        commentRepository.save(comment);
        return Constant.SUCCESS;
    }

    public Answer validateAnswer(String answerId) {
        Optional<Answer> answer = findById(answerId);
        if (!answer.isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("The questionId " + answerId));
        }
        return answer.get();
    }
}
