package com.example.stackoverflow.repository;

import com.example.stackoverflow.common.UtilsTest;
import com.example.stackoverflow.model.entity.Question;
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
//@DataJpaTest
@SpringBootTest
@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@DisplayNameGeneration(UtilsTest.ReplaceCamelCase.class)
public class QuestionRepositoryTest {

    public static final int numberOfQuestion = 20;


    @Autowired
    QuestionRepository questionRepository;

    @Before
    public void setUp() throws Exception {
//        questionRepository.save(new Question());
    }

    @Test
    public void findByOrderByQuestionIdDesc() {
        List<Question> questions = questionRepository.findByOrderByQuestionIdDesc();
        int size = questions.size();
        assert (size == numberOfQuestion);
        // Test sort descending by questionId
        for (int i = 0; i < size; i++) {
            assert (questions.get(i).getQuestionId() == size - i);
        }
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentFirstWord() {
        String content1 = "AXQ124";
        String content2 = "BKI345";
        String content3 = "H2KL2";
        String content = content1 + " " + content2 + " " + content3;
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);

        String searchContent = content1;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentMiddleWord() {
        String content1 = "AXQ124";
        String content2 = "BKI345";
        String content3 = "H2KL2";
        String content = content1 + " " + content2 + " " + content3;
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);

        String searchContent = content2;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentLastWord() {
        String content1 = "AXQ124";
        String content2 = "BKI345";
        String content3 = "H2KL2";
        String content = content1 + " " + content2 + " " + content3;
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);

        String searchContent = content3;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentHalfFirstWord() {
        String content1 = "AXQ124";
        String content2 = "BKI345";
        String content3 = "H2KL2";
        String content = content1 + " " + content2 + " " + content3;
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);

        String searchContent = content3.substring(0, 3);
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentHalfMiddleWord() {
        String content1 = "AXQ124";
        String content2 = "BKI345";
        String content3 = "H2KL2";
        String content = content1 + " " + content2 + " " + content3;
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);

        String searchContent = content2.substring(0, 3);
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentFirstAndMiddleWord() {
        String content1 = "AXQ124";
        String content2 = "BKI345";
        String content3 = "H2KL2";
        String content = content1 + " " + content2 + " " + content3;
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);

        String searchContent = content1 + " " + content2;
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

    @Test
    public void findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase_ContentHalfFirstAndHalfMiddleWord() {
        String content1 = "AXQ124";
        String content2 = "BKI345";
        String content3 = "H2KL2";
        String content = content1 + " " + content2 + " " + content3;
        Question question = Question.builder().title(content).build();
        questionRepository.save(question);

        String searchContent = content1.substring(3) + " " + content2.substring(0, 3);
        List<Question> questions = questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchContent, searchContent);

        assert (questions.size() == 1);
        assert (questions.get(0).getTitle().equals(content));
    }

}