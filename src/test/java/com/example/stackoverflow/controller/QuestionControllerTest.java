package com.example.stackoverflow.controller;

import com.example.stackoverflow.model.builder.QuestionBuilder;
import com.example.stackoverflow.model.entity.Question;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import com.example.stackoverflow.service.implement.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
@AutoConfigureMockMvc(addFilters = false)
//@ContextConfiguration(classes={QuestionController.class,)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionServiceImpl questionService;

    @Autowired
    private AccountServiceImpl accountService;

    @Test
    void getQuestionsWhenContentNull() throws Exception {
        Question question = new QuestionBuilder().setTitle("Title").createQuestion();
        List<Question> questions = Arrays.asList(question);

        BDDMockito.given(questionService.findAll()).willReturn(questions);

        mvc.perform(get("/api/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getQuestionById() {
    }

    @Test
    void getAnswersOfQuestion() {
    }

    @Test
    void getAnswerByIdOfQuestion() {
    }

    @Test
    void getCommentsOfQuestion() {
    }

    @Test
    void getCommentByIdOfQuestion() {
    }

    @Test
    void addCommentToQuestion() {
    }

    @Test
    void addAnswerToQuestion() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void addQuestion() {
    }

    @Test
    void setVoteOfQuestion() {
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void flagQuestion() {
    }
}