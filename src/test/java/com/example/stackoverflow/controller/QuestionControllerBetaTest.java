package com.example.stackoverflow.controller;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.interceptor.AuthorizeInterceptor;
import com.example.stackoverflow.model.entity.*;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import com.example.stackoverflow.service.implement.QuestionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
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

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class QuestionControllerBetaTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionServiceImpl questionService;

    @MockBean
    private AccountServiceImpl accountService;

//    // If don't have this => Token null => Role Guest => validateRequest return DONT_HAVE_PERMISSION (500)
//    @Spy
//    private AuthorizeInterceptor authorizeInterceptor;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addCommentToQuestion_HasAccountAndHasQuestion() throws Exception {
//        String questionId = "1";
//        String email = "test@gmail.com";
//        Account account = Account.builder().email(email).build();
//        Question question = Question.builder().build();
//        Comment comment = Comment.builder().account(account).build();
//        CommentForm commentForm = new CommentForm();
//
//        given(accountService.getAccountFromToken(anyString())).willReturn(account);
//        given(questionService.findById(anyString())).willReturn(ofNullable(question));
//        given(questionService.insertCommentToQuestion(account, question, commentForm)).willReturn(comment);

//        mvc.perform(post("/api/questions/1/comments1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                //.andExpect(jsonPath("$.email", is(email)));

        mvc.perform(get("/api1/questions?content=" + "12")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addCommentToQuestion_AccountNullAndHasQuestion() throws Exception {
        // Config data for Comment, which convert to CommentQuestionView
        String email = "test@gmail.com";
        int questionId = 1;
        int commentId = 1;
        String text = "text";
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        Question question = Question.builder().questionId(questionId).build();
        Comment comment = Comment.builder().commentId(commentId).account(account).question(question).text(text).build();

        // Config data for RequestBody
        CommentForm commentForm = CommentForm.builder().text(text).build();

        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        given(questionService.findById(anyString())).willReturn(ofNullable(question));
        given(questionService.insertCommentToQuestion(any(), any(), any())).willReturn(comment);

        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(email)));
    }

    //        String method = "GET";
//        String uri = "/api/questions";
//        MockHttpServletRequest request = new MockHttpServletRequest(method,uri);
//        given(authorizeInterceptor.validateRequest(request)).willReturn(AuthorizeInterceptor.ValidateRequestStatus.SUCCESS);
//        Mockito.doReturn(AuthorizeInterceptor.ValidateRequestStatus.DONT_HAVE_PERMISSION).when(authorizeInterceptor).validateRequest(request);
//        Mockito.when(authorizeInterceptor.validateRequest(request)).thenReturn(AuthorizeInterceptor.ValidateRequestStatus.SUCCESS);


}