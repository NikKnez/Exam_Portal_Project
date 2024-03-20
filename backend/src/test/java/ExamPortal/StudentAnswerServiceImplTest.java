package ExamPortal;

import ExamPortal.entities.Exam;
import ExamPortal.entities.Question;
import ExamPortal.entities.StudentAnswer;
import ExamPortal.entities.User;
import ExamPortal.repositories.StudentAnswerRepository;
import ExamPortal.services.impl.StudentAnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StudentAnswerServiceImplTest {

    @Mock
    private StudentAnswerRepository studentAnswerRepository;

    @InjectMocks
    private StudentAnswerServiceImpl studentAnswerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAnswer() {
        // Arrange
        StudentAnswer answerToSave = new StudentAnswer();
        when(studentAnswerRepository.save(answerToSave)).thenReturn(answerToSave);

        // Act
        StudentAnswer savedAnswer = studentAnswerService.addAnswer(answerToSave);

        // Assert
        assertNotNull(savedAnswer);
        assertEquals(answerToSave, savedAnswer);
        verify(studentAnswerRepository, times(1)).save(answerToSave);
    }

    @Test
    void testGetAnswer() {
        // Arrange
        int answerId = 1;
        StudentAnswer expectedAnswer = new StudentAnswer();
        when(studentAnswerRepository.findById(answerId)).thenReturn(Optional.of(expectedAnswer));

        // Act
        StudentAnswer retrievedAnswer = studentAnswerService.getAnswer(answerId);

        // Assert
        assertNotNull(retrievedAnswer);
        assertEquals(expectedAnswer, retrievedAnswer);
        verify(studentAnswerRepository, times(1)).findById(answerId);
    }

    @Test
    void testGetAnswerByQuestion() {
        // Arrange
        Question question = new Question();
        StudentAnswer expectedAnswer = new StudentAnswer();
        when(studentAnswerRepository.findByQuestion(question)).thenReturn(expectedAnswer);

        // Act
        StudentAnswer retrievedAnswer = studentAnswerService.getAnswerByQuestion(question);

        // Assert
        assertNotNull(retrievedAnswer);
        assertEquals(expectedAnswer, retrievedAnswer);
        verify(studentAnswerRepository, times(1)).findByQuestion(question);
    }

    @Test
    void testGetAllAnswerByExam() {
        // Arrange
        Exam exam = new Exam();
        List<StudentAnswer> expectedAnswers = new ArrayList<>();
        when(studentAnswerRepository.findByExam(exam)).thenReturn(expectedAnswers);

        // Act
        List<StudentAnswer> retrievedAnswers = studentAnswerService.getAllAnswerByExam(exam);

        // Assert
        assertNotNull(retrievedAnswers);
        assertEquals(expectedAnswers, retrievedAnswers);
        verify(studentAnswerRepository, times(1)).findByExam(exam);
    }

    @Test
    void testAddAnswers() {
        // Arrange
        List<StudentAnswer> answersToSave = new ArrayList<>();
        when(studentAnswerRepository.saveAll(answersToSave)).thenReturn(answersToSave);

        // Act
        List<StudentAnswer> savedAnswers = studentAnswerService.addAnswers(answersToSave);

        // Assert
        assertNotNull(savedAnswers);
        assertEquals(answersToSave, savedAnswers);
        verify(studentAnswerRepository, times(1)).saveAll(answersToSave);
    }

    @Test
    void testGetByExamAndStudent() {
        // Arrange
        Exam exam = new Exam();
        User student = new User();
        List<StudentAnswer> expectedAnswers = new ArrayList<>();
        when(studentAnswerRepository.findByExamAndStudent(exam, student)).thenReturn(expectedAnswers);

        // Act
        List<StudentAnswer> retrievedAnswers = studentAnswerService.getByExamAndStudent(exam, student);

        // Assert
        assertNotNull(retrievedAnswers);
        assertEquals(expectedAnswers, retrievedAnswers);
        verify(studentAnswerRepository, times(1)).findByExamAndStudent(exam, student);
    }
}
