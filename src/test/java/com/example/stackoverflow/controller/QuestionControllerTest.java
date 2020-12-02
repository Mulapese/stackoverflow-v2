package com.example.stackoverflow.controller;

import com.example.stackoverflow.model.entity.Question;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void getQuestionsWhenContentNull() throws Exception {
        Question question = Question.builder().questionId(1).title("Title").description("Description")
                .build();
        List<Question> questions = Arrays.asList(question);

        given(questionService.findAll()).willReturn(questions);

        mvc.perform(get("/api/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // TE-01
    @Test
    public void addQuestion() throws Exception {
        given(questionService.insert(Mockito.anyString(), Mockito.any())).willReturn(1);

        mvc.perform(post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}