package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Answer;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Vote;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.repository.AnswerRepository;
import com.example.stackoverflow.repository.CommentRepository;
import com.example.stackoverflow.repository.VoteRepository;
import com.example.stackoverflow.service.serviceInterface.AnswerService;
import com.example.stackoverflow.service.serviceInterface.common.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements CRUDService<Answer, Answer>, AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Answer insert(Account account, Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> findById(String answerId) {
        int answerIdInt = Utils.convertStringToInteger(answerId, "answerId");
        Optional<Answer> answerOptional = answerRepository.findById(answerIdInt);
        return answerOptional;
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
    public Comment insertCommentToAnswer(Account account, String answerId, CommentForm commentForm) {
        Answer answer = validateAnswer(answerId);

        Comment comment = Comment.builder().account(account)
                .answer(answer).text(commentForm.getText()).build();

        return commentRepository.save(comment);
    }

    @Override
    public int setVoteOfAnswer(String token, String score, String answerId) {
        int scoreInt = Utils.convertStringToInteger(score, "score", -1, 1);
        int answerIdInt = Utils.convertStringToInteger(answerId, "question");
        Account account = accountService.getAccountFromToken(token);
        Answer answer = validateAnswer(answerId);
        Vote currentVote = voteRepository.findByAccount_AccountIdAndAnswer_AnswerId(account.getAccountId(), answerIdInt);
        int scoreChange = scoreInt;

        Vote newVote = Vote.builder().account(account)
                .answer(answer)
                .score(scoreInt)
                .build();
        if (currentVote == null) {
            // newVote don't have Id => Insert
            newVote.setCreatedTime(Utils.getCurrentTimeStamp());
        } else {
            // newVote have Id => Update
            newVote.setVoteId(currentVote.getVoteId());
            newVote.setUpdatedTime(Utils.getCurrentTimeStamp());

            scoreChange -= currentVote.getScore();
        }

        // Save to answer table
        answer.setVoteCount(answer.getVoteCount() + scoreChange);
        answerRepository.save(answer);
        // Save to vote table
        voteRepository.save(newVote);

        return 0;
    }

    public Answer validateAnswer(String answerId) {
        Optional<Answer> answer = findById(answerId);
        if (!answer.isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("The questionId " + answerId));
        }
        return answer.get();
    }
}
