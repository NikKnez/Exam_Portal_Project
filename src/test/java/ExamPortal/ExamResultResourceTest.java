package ExamPortal;

import ExamPortal.entities.ExamResultResponse;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import ExamPortal.resource.ExamResultResource;
import ExamPortal.services.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamResultResourceTest {

    @Mock
    private UserService userService;

    @Mock
    private GradeService gradeService;

    @Mock
    private ExamService examService;

    @Mock
    private ExamResultService examResultService;

    @Mock
    private StudentAnswerService studentAnswerService;

    @InjectMocks
    private ExamResultResource examResultResource;

    @Test
    void fetchStudentExamResult_noResultsFound() {
        // Arrange
        int studentId = 1;
        when(userService.getUserById(studentId)).thenReturn(new User());
        when(examResultService.getResultsByStudent(any(User.class))).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<ExamResultResponse> responseEntity = examResultResource.fetchStudentExamResult(studentId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("No Exam Results found!!!", responseEntity.getBody().getResponseMessage());
        assertTrue(CollectionUtils.isEmpty(responseEntity.getBody().getResults()));
    }

    @Test
    void fetchStudentExamResultGradeWise_noResultsFound() {
        // Arrange
        int gradeId = 1;
        when(gradeService.getGradeById(gradeId)).thenReturn(new Grade());
        when(examResultService.getResultsByGrade(any(Grade.class))).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<ExamResultResponse> responseEntity = examResultResource.fetchStudentExamResultGradeWise(gradeId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("No Exam Results found!!!", responseEntity.getBody().getResponseMessage());
        assertTrue(CollectionUtils.isEmpty(responseEntity.getBody().getResults()));
    }

    @Test
    void fetchAllStudentResults_noResultsFound() {
        // Arrange
        when(examResultService.getAllResults()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<ExamResultResponse> responseEntity = examResultResource.fetchAllStudentResults();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("No Exam Results found!!!", responseEntity.getBody().getResponseMessage());
        assertTrue(CollectionUtils.isEmpty(responseEntity.getBody().getResults()));
    }


}

