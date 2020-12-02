package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.model.entity.Account;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.model.entity.StatusOfQuestion;
import com.example.stackoverflow.model.form.QuestionForm;
import com.example.stackoverflow.repository.QuestionRepository;
import com.example.stackoverflow.repository.StatusOfQuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AccountServiceImpl accountService;

    @Mock
    private StatusOfQuestionRepository statusOfQuestionRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        Question question = new QuestionBuilder().setTitle("Data for test")
//                .setDescription("Data for test").createQuestion();
//        questionRepository.save(question);
    }

    @Test
    public void insert() {
        Timestamp timestamp = new Timestamp(1);
        Account account = Account.builder().name("Name").email("Email")
                .password("Password").reputationPoint(1).createdTime(timestamp).updatedTime(timestamp).build();
        Question question = Question.builder().build();
        StatusOfQuestion statusOfQuestion = StatusOfQuestion.builder().statusOfQuestionId(1).description("open").build();
        QuestionForm questionForm = QuestionForm.builder().title("Title").description("Description").build();

        when(accountService.getAccountFromToken(Mockito.anyString())).thenReturn(account);
        when(statusOfQuestionRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(statusOfQuestion));
        when(questionRepository.save(Mockito.any())).thenReturn(question);

        int actualResult = questionService.insert(Mockito.anyString(), Mockito.any());
        assert (actualResult == 1);
    }

    @Test
    public void findAll() {
        List<Question> list = new ArrayList<>();
        Question question1 = Question.builder().build();
        Question question2 = Question.builder().build();
        Question question3 = Question.builder().build();

        list.add(question1);
        list.add(question2);
        list.add(question3);

        when(questionRepository.findByOrderByQuestionIdDesc()).thenReturn(list);

        List<Question> questions = questionService.findAll();
        assert (questions.size() == 3);
        verify(questionRepository, times(1)).findByOrderByQuestionIdDesc();
    }
}