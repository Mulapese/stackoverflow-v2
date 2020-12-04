package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.model.entity.StatusOfAccount;
import com.example.stackoverflow.model.entity.*;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.repository.CommentRepository;
import com.example.stackoverflow.repository.QuestionRepository;
import com.example.stackoverflow.repository.StatusOfQuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    // n/v : No value, only for make sure the function is tested
    @Test
    public void findAll_HasThreeQuestions_SizeThree() {
        List<Question> list = new ArrayList<>();
        Question question1 = Question.builder().build();
        Question question2 = Question.builder().build();
        Question question3 = Question.builder().build();

        list.add(question1);
        list.add(question2);
        list.add(question3);

        given(questionRepository.findByOrderByQuestionIdDesc()).willReturn(list);

        List<Question> questions = questionService.findAll();
        assert (questions.size() == 3);
    }

    // n/v
    @Test
    public void searchQuestionByTitleAndDescription_HasContentValid_SizeOne() {
        String content = "Content";
        List<Question> list = new ArrayList<>();
        Question question1 = Question.builder().build();
        list.add(question1);

        given(questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(content, content))
                .willReturn(list);

        List<Question> questions = questionService.searchQuestionByTitleAndDescription(content);
        assert (questions.size() == 1);
    }

    // n/v
    @Test
    public void insertCommentToQuestion() {
        // Setup account
        Role role = Role.builder().roleId(1).name("Admin").build();
        StatusOfAccount statusOfAccount = StatusOfAccount.builder().statusOfAccountId(1).description("open").build();
        String email = "test@gmail.com";
        Account account = Account.builder().accountId(1).role(role).statusOfAccount(statusOfAccount).email(email).build();

        // Setup question
        StatusOfQuestion statusOfQuestion = StatusOfQuestion.builder().statusOfQuestionId(1).build();
        Question question = Question.builder().questionId(1).statusOfQuestion(statusOfQuestion)
                .account(account).title("Title").description("Description").build();

        // Setup CommentForm
        CommentForm commentForm = CommentForm.builder().text("text").build();

        questionService.insertCommentToQuestion(account, question, commentForm);

        verify(commentRepository).save(any());
    }

}