package com.example.stackoverflow.repository;

import com.example.stackoverflow.common.UtilsTest;
import com.example.stackoverflow.exception.exceptionType.MessageException;
import com.example.stackoverflow.model.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class QuestionRepositoryTest {

    public static final int numberOfQuestion = 20;

    @Autowired
    QuestionRepository questionRepository;

    String content1 = "AXQ124";
    String content2 = "BKI345";
    String content3 = "H2KL2";
    String contentNotInclude = "HKL32";
    String content = content1 + " " + content2 + " " + content3;

    @Before
    public void setUp() throws Exception {
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);
    }

    @Test
    public void findByOrderByQuestionIdDesc() {
        List<Question> questions = questionRepository.findByOrderByQuestionIdDesc();
        int size = questions.size();

        assert (size == numberOfQuestion + 1);

        // Test sort descending by questionId
        for (int i = 0; i < size - 1; i++) {
            assert (questions.get(i).getQuestionId() > questions.get(i + 1).getQuestionId());
        }
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentFirstWord() {
        String searchContent = content1;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentMiddleWord() {
        String searchContent = content2;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentLastWord() {
        String searchContent = content3;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentHalfFirstWord() {
        String searchContent = content3.substring(0, 3);
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentHalfMiddleWord() {
        String searchContent = content2.substring(0, 3);
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentFirstAndMiddleWord() {
        String searchContent = content1 + " " + content2;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentHalfFirstAndHalfMiddleWord() {
        String searchContent = content1.substring(3) + " " + content2.substring(0, 3);
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentNotInclude() {
        String searchContent = contentNotInclude;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 0);
    }

    // n/v
    @Test
    public void save_NoQuestionId_OneRowInserted() {
        // Setup account
        Role role = Role.builder().roleId(1).name("Admin").build();
        StatusOfAccount statusOfAccount = StatusOfAccount.builder().statusOfAccountId(1).description("open").build();
        String email = "test@gmail.com";
        Account account = Account.builder().accountId(1).role(role).statusOfAccount(statusOfAccount).email(email).build();

        // Setup question
        StatusOfQuestion statusOfQuestion = StatusOfQuestion.builder().statusOfQuestionId(1).build();
        Question question = Question.builder().statusOfQuestion(statusOfQuestion)
                .account(account).title("Title").description("Description").build();

        questionRepository.save(question);
        List<Question> questions = questionRepository.findAll();
        assert (questions.size() == numberOfQuestion + 2);
    }
}