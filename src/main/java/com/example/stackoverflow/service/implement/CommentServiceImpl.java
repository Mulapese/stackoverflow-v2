package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Comment;
import com.example.stackoverflow.model.entity.Vote;
import com.example.stackoverflow.repository.CommentRepository;
import com.example.stackoverflow.repository.VoteRepository;
import com.example.stackoverflow.service.serviceInterface.CommentService;
import com.example.stackoverflow.service.serviceInterface.common.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CRUDService<Comment, Comment>, CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public int insert(String token, Comment comment) {
        commentRepository.save(comment);
        return 1;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(String commentId) {
        int commentIdInt = Utils.convertStringToInteger(commentId, "questionId");
        Optional<Comment> commentOptional = commentRepository.findById(commentIdInt);
        return commentOptional;
    }

    @Override
    public int update(String id, Comment comment) {
        return 1;
    }

    @Override
    public Optional<Comment> delete(String id) {
        return Optional.empty();
    }

    @Override
    public int setVoteOfComment(String token, String score, String commentId) {
        int scoreInt = Utils.convertStringToInteger(score, "score", -1, 1);
        int commentIdInt = Utils.convertStringToInteger(commentId, "commentId");
        Account account = accountService.getAccountFromToken(token);
        Comment comment = validateComment(commentId);
        Vote currentVote = voteRepository.findByAccount_AccountIdAndComment_CommentId(account.getAccountId(), commentIdInt);
        int scoreChange = scoreInt;

        Vote newVote = Vote.builder().account(account)
                .comment(comment)
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
        comment.setVoteCount(comment.getVoteCount() + scoreChange);
        commentRepository.save(comment);
        // Save to vote table
        voteRepository.save(newVote);

        return Constant.SUCCESS;
    }

    public Comment validateComment(String commentId) {
        Optional<Comment> comment = findById(commentId);
        if (!comment.isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("The commentId " + commentId));
        }
        return comment.get();
    }
}
