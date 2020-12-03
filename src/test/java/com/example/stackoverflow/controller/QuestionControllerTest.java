package com.example.stackoverflow.controller;

import com.example.stackoverflow.model.entity.*;
import com.example.stackoverflow.service.implement.QuestionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(QuestionController.class)

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = QuestionController.class)
//@WebAppConfiguration

@RunWith(SpringRunner.class)
//@WebMvcTest(QuestionController.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionServiceImpl questionService;

    @Before
    public void setUp() throws Exception {
        System.out.println("test");
    }

    @Test
    public void getQuestions_HasOneQuestion_OkAndSizeOne() throws Exception {
        Account account = new Account();
        StatusOfQuestion statusOfQuestion = new StatusOfQuestion();
        List<Answer> answers = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        Question question1 = Question.builder()
                .questionId(1)
                .title("Title")
                .description("Description")
                .statusOfQuestion(statusOfQuestion)
                .account(account)
                .answers(answers)
                .comments(comments)
                .build();

        List<Question> questions = Arrays.asList(question1);

        given(questionService.findAll()).willReturn(questions);

        mvc.perform(get("/api/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getQuestions_HasTwoQuestions_OkAndSizeTwo() throws Exception {
        Account account = new Account();
        StatusOfQuestion statusOfQuestion = new StatusOfQuestion();
        List<Answer> answers = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        Question question1 = Question.builder()
                .questionId(1)
                .title("Title")
                .description("Description")
                .statusOfQuestion(statusOfQuestion)
                .account(account)
                .answers(answers)
                .comments(comments)
                .build();
        Question question2 = Question.builder()
                .questionId(2)
                .title("Title")
                .description("Description")
                .statusOfQuestion(statusOfQuestion)
                .account(account)
                .answers(answers)
                .comments(comments)
                .build();
        List<Question> questions = Arrays.asList(question1, question2);

        given(questionService.findAll()).willReturn(questions);

        mvc.perform(get("/api/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getQuestions_HasZeroQuestion_OkAndSizeZero() throws Exception {
        List<Question> questions = new ArrayList<>();

        given(questionService.findAll()).willReturn(questions);

        mvc.perform(get("/api/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getQuestions_ContentValidAndHasOneQuestion_OkAndSizeOne() throws Exception {
        String content = "This is valid content";
        Account account = new Account();
        StatusOfQuestion statusOfQuestion = new StatusOfQuestion();
        List<Answer> answers = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        Question question1 = Question.builder()
                .questionId(1)
                .title("Title")
                .description("Description")
                .statusOfQuestion(statusOfQuestion)
                .account(account)
                .answers(answers)
                .comments(comments)
                .build();

        List<Question> questions = Arrays.asList(question1);

        given(questionService.searchQuestionByTitleAndDescription(Mockito.anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getQuestions_ContentValidAndHasTwoQuestion_OkAndSizeTwo() throws Exception {
        String content = "This is valid content";
        Account account = new Account();
        StatusOfQuestion statusOfQuestion = new StatusOfQuestion();
        List<Answer> answers = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        Question question1 = Question.builder()
                .questionId(1)
                .title("Title")
                .description("Description")
                .statusOfQuestion(statusOfQuestion)
                .account(account)
                .answers(answers)
                .comments(comments)
                .build();
        Question question2 = Question.builder()
                .questionId(1)
                .title("Title")
                .description("Content")
                .statusOfQuestion(statusOfQuestion)
                .account(account)
                .answers(answers)
                .comments(comments)
                .build();
        List<Question> questions = Arrays.asList(question1, question2);

        given(questionService.searchQuestionByTitleAndDescription(Mockito.anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getQuestions_ContentValidAndHasZeroQuestion_OkAndSizeZero() throws Exception {
        String content = "This is valid content";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(Mockito.anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getQuestions_ContentEmptyAndHasZeroQuestion_BadRequestAndThrowMessage() throws Exception {
        String content = "";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(Mockito.anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Content cannot be empty.")));
    }

    @Test
    public void getQuestions_ContentSpaceAndHasZeroQuestion_BadRequestAndThrowMessage() throws Exception {
        String content = " ";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(Mockito.anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Content cannot be empty.")));
    }

    @Test
    public void getQuestions_ContentSpacesAndHasZeroQuestion_BadRequestAndThrowMessage() throws Exception {
        String content = " ";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(Mockito.anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Content cannot be empty.")));
    }


}