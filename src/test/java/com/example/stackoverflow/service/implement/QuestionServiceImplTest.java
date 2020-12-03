package com.example.stackoverflow.service.implement;

import com.example.stackoverflow.model.entity.Question;
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

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AccountServiceImpl accountService;

    @Mock
    private StatusOfQuestionRepository statusOfQuestionRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Test
    public void insert() {
//        Timestamp timestamp = new Timestamp(1);
//        Account account = Account.builder().name("Name").email("Email")
//                .password("Password").reputationPoint(1).createdTime(timestamp).updatedTime(timestamp).build();
//        Question question = Question.builder().build();
//        StatusOfQuestion statusOfQuestion = StatusOfQuestion.builder().statusOfQuestionId(1).description("open").build();
//        QuestionForm questionForm = QuestionForm.builder().title("Title").description("Description").build();
//
//        when(accountService.getAccountFromToken(Mockito.anyString())).thenReturn(account);
//        when(statusOfQuestionRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(statusOfQuestion));
//        when(questionRepository.save(Mockito.any())).thenReturn(question);
//
//        int actualResult = questionService.insert(Mockito.anyString(), Mockito.any());
//        assert (actualResult == 1);
    }

    // n/v : No value, only for make sure the function is tested
    @Test
    public void findAll_HasThreeQuestions_SizeThree() {
//        List<Question> list = new ArrayList<>();
//        Question question1 = Question.builder().build();
//        Question question2 = Question.builder().build();
//        Question question3 = Question.builder().build();
//
//        list.add(question1);
//        list.add(question2);
//        list.add(question3);
//
//        given(questionRepository.findByOrderByQuestionIdDesc()).willReturn(list);

        List<Question> questions = questionService.findAll();
        assert (questions.size() == 3);
//        verify(questionRepository, times(1)).findByOrderByQuestionIdDesc();
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
}