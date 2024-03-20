package ExamPortal;

import ExamPortal.entities.Course;
import ExamPortal.entities.Exam;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import ExamPortal.repositories.ExamRepository;
import ExamPortal.services.impl.ExamServiceImpl;
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

class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private ExamServiceImpl examService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddExam() {
        // Arrange
        Exam examToSave = new Exam();
        when(examRepository.save(examToSave)).thenReturn(examToSave);

        // Act
        Exam savedExam = examService.addExam(examToSave);

        // Assert
        assertNotNull(savedExam);
        assertEquals(examToSave, savedExam);
        verify(examRepository, times(1)).save(examToSave);
    }

    @Test
    void testUpdateExam() {
        // Arrange
        Exam examToUpdate = new Exam();
        when(examRepository.save(examToUpdate)).thenReturn(examToUpdate);

        // Act
        Exam updatedExam = examService.updateExam(examToUpdate);

        // Assert
        assertNotNull(updatedExam);
        assertEquals(examToUpdate, updatedExam);
        verify(examRepository, times(1)).save(examToUpdate);
    }

    @Test
    void testGetExamById() {
        // Arrange
        int examId = 1;
        Exam expectedExam = new Exam();
        when(examRepository.findById(examId)).thenReturn(Optional.of(expectedExam));

        // Act
        Exam retrievedExam = examService.getExamById(examId);

        // Assert
        assertNotNull(retrievedExam);
        assertEquals(expectedExam, retrievedExam);
        verify(examRepository, times(1)).findById(examId);
    }

    @Test
    void testGetAllExamsByStatus() {
        // Arrange
        String status = "Active";
        List<Exam> expectedExams = new ArrayList<>();
        when(examRepository.findByStatus(status)).thenReturn(expectedExams);

        // Act
        List<Exam> retrievedExams = examService.getAllExamsByStatus(status);

        // Assert
        assertNotNull(retrievedExams);
        assertEquals(expectedExams, retrievedExams);
        verify(examRepository, times(1)).findByStatus(status);
    }

    @Test
    void testGetAllExamsByGradeAndStatus() {
        // Arrange
        Grade grade = new Grade();
        String status = "Active";
        List<Exam> expectedExams = new ArrayList<>();
        when(examRepository.findByGradeAndStatus(grade, status)).thenReturn(expectedExams);

        // Act
        List<Exam> retrievedExams = examService.getAllExamsByGradeAndStatus(grade, status);

        // Assert
        assertNotNull(retrievedExams);
        assertEquals(expectedExams, retrievedExams);
        verify(examRepository, times(1)).findByGradeAndStatus(grade, status);
    }

    @Test
    void testGetAllExamsByCourseAndStatus() {
        // Arrange
        Course course = new Course();
        String status = "Active";
        List<Exam> expectedExams = new ArrayList<>();
        when(examRepository.findByCourseAndStatus(course, status)).thenReturn(expectedExams);

        // Act
        List<Exam> retrievedExams = examService.getAllExamsByCourseAndStatus(course, status);

        // Assert
        assertNotNull(retrievedExams);
        assertEquals(expectedExams, retrievedExams);
        verify(examRepository, times(1)).findByCourseAndStatus(course, status);
    }

    @Test
    void testGetAllExamsByTeacherAndStatus() {
        // Arrange
        User teacher = new User();
        String status = "Active";
        List<Exam> expectedExams = new ArrayList<>();
        when(examRepository.findByTeacherAndStatus(teacher, status)).thenReturn(expectedExams);

        // Act
        List<Exam> retrievedExams = examService.getAllExamsByTeacherAndStatus(teacher, status);

        // Assert
        assertNotNull(retrievedExams);
        assertEquals(expectedExams, retrievedExams);
        verify(examRepository, times(1)).findByTeacherAndStatus(teacher, status);
    }

    @Test
    void testGetExamsByGradeAndStartTimeGreaterThanAndStatus() {
        // Arrange
        Grade grade = new Grade();
        String startTime = "2024-02-20";
        String status = "Active";
        List<Exam> expectedExams = new ArrayList<>();
        when(examRepository.findByGradeAndStartTimeGreaterThanAndStatus(grade, startTime, status))
                .thenReturn(expectedExams);

        // Act
        List<Exam> retrievedExams = examService.getExamsByGradeAndStartTimeGreaterThanAndStatus(grade, startTime, status);

        // Assert
        assertNotNull(retrievedExams);
        assertEquals(expectedExams, retrievedExams);
        verify(examRepository, times(1)).findByGradeAndStartTimeGreaterThanAndStatus(grade, startTime, status);
    }

    @Test
    void testGetExamsByGradeAndStartTimeLessThanAndStatus() {
        // Arrange
        Grade grade = new Grade();
        String startTime = "2024-02-20";
        String status = "Active";
        List<Exam> expectedExams = new ArrayList<>();
        when(examRepository.findByGradeAndStartTimeLessThanAndStatus(grade, startTime, status)).thenReturn(expectedExams);

        // Act
        List<Exam> retrievedExams = examService.getExamsByGradeAndStartTimeLessThanAndStatus(grade, startTime, status);

        // Assert
        assertNotNull(retrievedExams);
        assertEquals(expectedExams, retrievedExams);
        verify(examRepository, times(1)).findByGradeAndStartTimeLessThanAndStatus(grade, startTime, status);
    }

    @Test
    void testGetExamsByGradeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus() {
        // Arrange
        Grade grade = new Grade();
        String currentTime = "2024-02-20";
        String currenTime = "2024-02-21";
        String status = "Active";
        List<Exam> expectedExams = new ArrayList<>();
        when(examRepository.findByGradeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(grade, currentTime,
                currenTime, status)).thenReturn(expectedExams);

        // Act
        List<Exam> retrievedExams = examService.getExamsByGradeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(
                grade, currentTime, currenTime, status);

        // Assert
        assertNotNull(retrievedExams);
        assertEquals(expectedExams, retrievedExams);
        verify(examRepository, times(1)).findByGradeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(grade,
                currentTime, currenTime, status);
    }
}

