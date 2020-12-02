package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.common.ErrorMessage;
import com.example.stackoverflow.common.Utils;
import com.example.stackoverflow.exception.exceptionType.RecordNotFoundException;
import com.example.stackoverflow.model.entity.*;
import com.example.stackoverflow.model.form.AnswerForm;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.model.form.QuestionForm;
import com.example.stackoverflow.repository.*;
import com.example.stackoverflow.service.serviceInterface.QuestionService;
import com.example.stackoverflow.service.serviceInterface.common.CRUDService;
import com.example.stackoverflow.service.serviceInterface.common.FlagCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements CRUDService<Question, QuestionForm>, FlagCountService, QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private StatusOfQuestionRepository statusOfQuestionRepository;

    @Autowired
    private VoteRepository voteRepository;

    // SO-08
    @Autowired
    private AccountServiceImpl accountService;

    // SO-09
    @Override
    public int insert(String token, QuestionForm questionForm) {
        Account account = accountService.getAccountFromToken(token);
        // SO-03
        Optional<StatusOfQuestion> statusOpen = statusOfQuestionRepository.findById(Constant.QUESTION_STATUS_OPEN);

        Question question = Question.builder().title(questionForm.getTitle())
                .description(questionForm.getDescription())
                .account(account)
                .statusOfQuestion(statusOpen.get())
                .build();
        questionRepository.save(question);
        return Constant.SUCCESS;
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findByOrderByQuestionIdDesc();
    }

    @Override
    public Optional<Question> findById(String questionId) {
        int questionIdInt = Utils.convertStringToInteger(questionId, "questionId");
        Optional<Question> questionOptional = questionRepository.findById(questionIdInt);

//        // Update view count
//        if (questionOptional.isPresent()) {
//            Question question = questionOptional.get();
//            if (question.getViewCount() == null) {
//                question.setViewCount(0);
//            }
//            question.setViewCount(question.getViewCount() + 1);
//            questionRepository.save(question);
//        }

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
        Optional<Question> questionOptional = questionRepository.findById(Integer.parseInt(id));
        setUpdatedTime(questionOptional.get());

        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setFlagCount(question.getFlagCount() + 1);
            return Constant.SUCCESS;
        }

        return Constant.FAIL;
    }

    public void setUpdatedTime(Question question) {
        question.setUpdatedTime(Utils.getCurrentTimeStamp());
    }

    @Override
    public List<Answer> getAnswersOfQuestion(String questionId) {
        validateQuestion(questionId);
        int questionIdInt = Utils.convertStringToInteger(questionId, "questionId");
        return answerRepository.findByQuestion_QuestionIdOrderByAnswerIdDesc(questionIdInt);
    }

    @Override
    public Answer getAnswerByIdOfQuestion(String questionId, String answerId) {
        validateQuestion(questionId);
        int questionIdInt = Utils.convertStringToInteger(questionId, "questionId");
        int answerIdInt = Utils.convertStringToInteger(answerId, "answerId");
        Answer answer = answerRepository.findByQuestion_QuestionIdAndAnswerId(questionIdInt, answerIdInt);

        if (answer == null) {
            throw new RecordNotFoundException("The question with questionId = " + questionId +
                    " is not contains the answer with answerId = " + answerId);
        }
        return answerRepository.findByQuestion_QuestionIdAndAnswerId(questionIdInt, answerIdInt);
    }

    @Override
    public List<Comment> getCommentsOfQuestion(String questionId) {
        validateQuestion(questionId);
        int questionIdInt = Utils.convertStringToInteger(questionId, "questionId");
        return commentRepository.findByQuestion_QuestionIdOrderByCommentIdDesc(questionIdInt);
    }

    @Override
    public Comment getCommentByIdOfQuestion(String questionId, String commentId) {
        validateQuestion(questionId);
        int questionIdInt = Utils.convertStringToInteger(questionId, "questionId");
        int answerIdInt = Utils.convertStringToInteger(commentId, "commentId");
        Comment comment = commentRepository.findByQuestion_QuestionIdAndCommentId(questionIdInt, answerIdInt);

        if (comment == null) {
            throw new RecordNotFoundException("The question with questionId = " + questionId +
                    " is not contains the comment with commentId = " + commentId);
        }
        return commentRepository.findByQuestion_QuestionIdAndCommentId(questionIdInt, answerIdInt);
    }

    @Override
    public int insertCommentToQuestion(String token, String questionId, CommentForm commentForm) {
        Account account = accountService.getAccountFromToken(token);
        Question question = validateQuestion(questionId);

        Comment comment = Comment.builder().account(account)
                .question(question).text(commentForm.getText()).build();
        commentRepository.save(comment);
        return Constant.SUCCESS;
    }

    @Override
    public int insertAnswerToQuestion(String token, String questionId, AnswerForm answerForm) {
        Account account = accountService.getAccountFromToken(token);
        Question question = validateQuestion(questionId);

        Answer answer = Answer.builder().account(account)
                .question(question).text(answerForm.getText()).build();
        answerRepository.save(answer);
        return Constant.SUCCESS;
    }

    @Override
    public List<Question> searchQuestionByTitleAndDescription(String content) {
        content = content.trim();
        // SO-10
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(content, content);
        return questions;
    }

    @Override
    public StatusOfQuestion setStatusOfQuestion(String token, String statusId, String questionId) {
        int statusIdInt = Utils.convertStringToInteger(statusId, "statusId");

        // Need validate, only the current user can edit
        // SO-11
        Account account = accountService.getAccountFromToken(token);
        StatusOfQuestion statusOfQuestion = statusOfQuestionRepository.getOne(statusIdInt);
        Question question = validateQuestion(questionId);

        question.setStatusOfQuestion(statusOfQuestion);
        questionRepository.save(question);
        return statusOfQuestion;
    }

    @Override
    @Transactional
    public int setVoteOfQuestion(String token, String score, String questionId) {
        int scoreInt = Utils.convertStringToInteger(score, "score", -1, 1);
        int questionIdInt = Utils.convertStringToInteger(questionId, "question");
        Account account = accountService.getAccountFromToken(token);
        Question question = validateQuestion(questionId);
        Vote currentVote = voteRepository.findByAccount_AccountIdAndQuestion_QuestionId(account.getAccountId(), questionIdInt);
        int scoreChange = scoreInt;

        Vote newVote = Vote.builder().account(account)
                .question(question)
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
        question.setVoteCount(question.getVoteCount() + scoreChange);
        questionRepository.save(question);
        // Save to vote table
        voteRepository.save(newVote);

        return Constant.SUCCESS;
    }

    // If question not exist then throw exception
    // Else return Question
    public Question validateQuestion(String questionId) {
        Optional<Question> question = findById(questionId);
        if (!question.isPresent()) {
            throw new RecordNotFoundException(ErrorMessage.notExist("The questionId " + questionId));
        }
        return question.get();
    }
}
