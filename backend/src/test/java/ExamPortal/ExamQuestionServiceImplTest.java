package ExamPortal;

import ExamPortal.entities.Question;
import ExamPortal.repositories.QuestionRepository;
import ExamPortal.services.impl.ExamQuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ExamQuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private ExamQuestionServiceImpl examQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddExamQuestion() {
        // Arrange
        Question expectedQuestion = new Question();
        when(questionRepository.save(any(Question.class))).thenReturn(expectedQuestion);

        // Act
        Question addedQuestion = examQuestionService.addExamQuestion(new Question());

        // Assert
        assertNotNull(addedQuestion);
        assertEquals(expectedQuestion, addedQuestion);
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void testGetExamQuestionById() {
        // Arrange
        int questionId = 1;
        Question expectedQuestion = new Question();
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(expectedQuestion));

        // Act
        Question retrievedQuestion = examQuestionService.getExamQuestionById(questionId);

        // Assert
        assertNotNull(retrievedQuestion);
        assertEquals(expectedQuestion, retrievedQuestion);
        verify(questionRepository, times(1)).findById(questionId);
    }

    @Test
    void testDeleteQuestion() {
        // Arrange
        Question question = new Question();

        // Act
        examQuestionService.deleteQuestion(question);

        // Assert
        verify(questionRepository, times(1)).delete(question);
    }

}

