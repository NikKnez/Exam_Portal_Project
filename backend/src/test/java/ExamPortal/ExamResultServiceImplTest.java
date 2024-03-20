package ExamPortal;

import ExamPortal.entities.Exam;
import ExamPortal.entities.ExamResult;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import ExamPortal.repositories.ExamResultRepository;
import ExamPortal.services.impl.ExamResultServiceImpl;
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

class ExamResultServiceImplTest {

    @Mock
    private ExamResultRepository examResultRepository;

    @InjectMocks
    private ExamResultServiceImpl examResultService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddResult() {
        // Arrange
        ExamResult expectedResult = new ExamResult();
        when(examResultRepository.save(any(ExamResult.class))).thenReturn(expectedResult);

        // Act
        ExamResult addedResult = examResultService.addResult(new ExamResult());

        // Assert
        assertNotNull(addedResult);
        assertEquals(expectedResult, addedResult);
        verify(examResultRepository, times(1)).save(any(ExamResult.class));
    }

    @Test
    void testGetResultsByStudent() {
        // Arrange
        User student = new User();
        List<ExamResult> expectedResults = new ArrayList<>();
        when(examResultRepository.findByStudent(student)).thenReturn(expectedResults);

        // Act
        List<ExamResult> retrievedResults = examResultService.getResultsByStudent(student);

        // Assert
        assertNotNull(retrievedResults);
        assertEquals(expectedResults, retrievedResults);
        verify(examResultRepository, times(1)).findByStudent(student);
    }

    @Test
    void testGetResultsByStudentAndExam() {
        // Arrange
        User student = new User();
        Exam exam = new Exam();
        List<ExamResult> expectedResults = new ArrayList<>();
        when(examResultRepository.findByStudentAndExam(student, exam)).thenReturn(expectedResults);

        // Act
        List<ExamResult> retrievedResults = examResultService.getResultsByStudentAndExam(student, exam);

        // Assert
        assertNotNull(retrievedResults);
        assertEquals(expectedResults, retrievedResults);
        verify(examResultRepository, times(1)).findByStudentAndExam(student, exam);
    }

    @Test
    void testGetResultsByExam() {
        // Arrange
        Exam exam = new Exam();
        List<ExamResult> expectedResults = new ArrayList<>();
        when(examResultRepository.findByExam(exam)).thenReturn(expectedResults);

        // Act
        List<ExamResult> retrievedResults = examResultService.getResultsByExam(exam);

        // Assert
        assertNotNull(retrievedResults);
        assertEquals(expectedResults, retrievedResults);
        verify(examResultRepository, times(1)).findByExam(exam);
    }

    @Test
    void testGetExamResultById() {
        // Arrange
        int resultId = 1;
        ExamResult expectedResult = new ExamResult();
        when(examResultRepository.findById(resultId)).thenReturn(Optional.of(expectedResult));

        // Act
        ExamResult retrievedResult = examResultService.getExamResultById(resultId);

        // Assert
        assertNotNull(retrievedResult);
        assertEquals(expectedResult, retrievedResult);
        verify(examResultRepository, times(1)).findById(resultId);
    }

    @Test
    void testGetResultsByGrade() {
        // Arrange
        Grade grade = new Grade();
        List<ExamResult> expectedResults = new ArrayList<>();
        when(examResultRepository.findByExam_Grade(grade)).thenReturn(expectedResults);

        // Act
        List<ExamResult> retrievedResults = examResultService.getResultsByGrade(grade);

        // Assert
        assertNotNull(retrievedResults);
        assertEquals(expectedResults, retrievedResults);
        verify(examResultRepository, times(1)).findByExam_Grade(grade);
    }

    @Test
    void testGetAllResults() {
        // Arrange
        List<ExamResult> expectedResults = new ArrayList<>();
        when(examResultRepository.findAll()).thenReturn(expectedResults);

        // Act
        List<ExamResult> retrievedResults = examResultService.getAllResults();

        // Assert
        assertNotNull(retrievedResults);
        assertEquals(expectedResults, retrievedResults);
        verify(examResultRepository, times(1)).findAll();
    }

}

