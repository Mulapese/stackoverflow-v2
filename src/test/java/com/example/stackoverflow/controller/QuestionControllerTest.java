package com.example.stackoverflow.controller;

import com.example.stackoverflow.service.implement.QuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionServiceImpl questionService;

    @Test
    void getQuestionsWhenContentNull() throws Exception {
        mvc.perform(get("/api/question")
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