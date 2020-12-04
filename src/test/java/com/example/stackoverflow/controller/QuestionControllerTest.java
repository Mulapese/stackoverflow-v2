package com.example.stackoverflow.controller;

import com.example.stackoverflow.common.Constant;
import com.example.stackoverflow.interceptor.AuthorizeInterceptor;
import com.example.stackoverflow.model.entity.*;
import com.example.stackoverflow.model.form.CommentForm;
import com.example.stackoverflow.service.implement.AccountServiceImpl;
import com.example.stackoverflow.service.implement.QuestionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
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
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionServiceImpl questionService;

    @MockBean
    private AccountServiceImpl accountService;

    @Autowired
    ObjectMapper objectMapper;

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

        given(questionService.searchQuestionByTitleAndDescription(anyString())).willReturn(questions);

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

        given(questionService.searchQuestionByTitleAndDescription(anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getQuestions_ContentValidAndHasZeroQuestion_OkAndSizeZero() throws Exception {
        String content = "This is valid content";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getQuestions_ContentEmptyAndHasZeroQuestion_BadRequest() throws Exception {
        String content = "";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Content cannot be empty.")));
    }

    @Test
    public void getQuestions_ContentSpaceAndHasZeroQuestion_BadRequest() throws Exception {
        String content = " ";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Content cannot be empty.")));
    }

    @Test
    public void getQuestions_ContentSpacesAndHasZeroQuestion_BadRequest() throws Exception {
        String content = " ";
        List<Question> questions = new ArrayList<>();

        given(questionService.searchQuestionByTitleAndDescription(anyString())).willReturn(questions);

        mvc.perform(get("/api/questions?content=" + content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Content cannot be empty.")));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNotNullAndTextNotNull_Ok() throws Exception {
        // Config data for RequestBody
        String textRequest = "text";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // Config data for Comment, which convert to CommentQuestionView
        // Config account to bypass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        int commentId = 1;
        String text = "text";
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        Question question = Question.builder().questionId(questionId).build();
        Comment comment = Comment.builder().commentId(commentId).account(account).question(question).text(text).build();


        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        given(questionService.findById(anyString())).willReturn(ofNullable(question));
        given(questionService.insertCommentToQuestion(any(), any(), any())).willReturn(comment);

        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(email)));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNotNullTextWithLeadingSpaces_Ok() throws Exception {
        // Config data for RequestBody
        String textRequest = "  text";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // Config data for Comment, which convert to CommentQuestionView
        // Config account to bypass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        int commentId = 1;
        String text = "text";
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        Question question = Question.builder().questionId(questionId).build();
        Comment comment = Comment.builder().commentId(commentId).account(account).question(question).text(text).build();


        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        given(questionService.findById(anyString())).willReturn(ofNullable(question));
        given(questionService.insertCommentToQuestion(any(), any(), any())).willReturn(comment);

        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(email)));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNotNullTextWithTrailingSpaces_Ok() throws Exception {
        // Config data for RequestBody
        String textRequest = "text  ";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // Config data for Comment, which convert to CommentQuestionView
        // Config account to bypass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        int commentId = 1;
        String text = "text";
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        Question question = Question.builder().questionId(questionId).build();
        Comment comment = Comment.builder().commentId(commentId).account(account).question(question).text(text).build();


        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        given(questionService.findById(anyString())).willReturn(ofNullable(question));
        given(questionService.insertCommentToQuestion(any(), any(), any())).willReturn(comment);

        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(email)));
    }

    @Test
    public void addCommentToQuestion_AccountNullAndQuestionNotNull_BadRequest() throws Exception {
        // Config data for RequestBody
        String textRequest = "text";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // Config data for Comment, which convert to CommentQuestionView
        // Config account to bypass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        int commentId = 1;
        String text = "text";
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        Question question = Question.builder().questionId(questionId).build();
        Comment comment = Comment.builder().commentId(commentId).account(account).question(question).text(text).build();


        given(accountService.getAccountFromToken(anyString())).willReturn(null);
        given(questionService.findById(anyString())).willReturn(ofNullable(question));
        given(questionService.insertCommentToQuestion(any(), any(), any())).willReturn(comment);

        // Because accountService.getAccountFromToken is exist in Controller and Interceptor, so the exception will be throw in Interceptor and has status 400
        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Please authenticate again or contact to admin.")));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNull_NotFound() throws Exception {
        // Config data for RequestBody
        String textRequest = "text";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // Config data for Comment, which convert to CommentQuestionView
        // Config account to bypass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        int commentId = 1;
        String text = "text";
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        Question question = Question.builder().questionId(questionId).build();
        Comment comment = Comment.builder().commentId(commentId).account(account).question(question).text(text).build();


        given(accountService.getAccountFromToken(anyString())).willReturn(account);
        given(questionService.findById(anyString())).willReturn(ofNullable(null));
        given(questionService.insertCommentToQuestion(any(), any(), any())).willReturn(comment);

        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message[0]", is("The questionId " + questionId + " is not exist.")));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNotNullTextNull_BadRequest() throws Exception {
        // Config data for RequestBody
        CommentForm commentForm = CommentForm.builder().build();

        // By pass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        given(accountService.getAccountFromToken(anyString())).willReturn(account);


        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Text cannot be blank.")));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNotNullTextEmpty_BadRequest() throws Exception {
        // Config data for RequestBody
        String textRequest = "";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // By pass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        given(accountService.getAccountFromToken(anyString())).willReturn(account);


        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Text cannot be blank.")));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNotNullTextSpace_BadRequest() throws Exception {
        // Config data for RequestBody
        String textRequest = " ";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // By pass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        given(accountService.getAccountFromToken(anyString())).willReturn(account);


        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Text cannot be blank.")));
    }

    @Test
    public void addCommentToQuestion_AccountNotNullAndQuestionNotNullTextSpaces_BadRequest() throws Exception {
        // Config data for RequestBody
        String textRequest = "  ";
        CommentForm commentForm = CommentForm.builder().text(textRequest).build();

        // By pass authorization
        String email = "test@gmail.com";
        int questionId = 1;
        Role role = Role.builder().roleId(Constant.ROLE_MEMBER).build();
        Account account = Account.builder().email(email).role(role).build();
        given(accountService.getAccountFromToken(anyString())).willReturn(account);


        mvc.perform(post("/api/questions/" + questionId + "/comments").header("Authorization","SampleValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentForm)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[0]", is("Text cannot be blank.")));
    }
}