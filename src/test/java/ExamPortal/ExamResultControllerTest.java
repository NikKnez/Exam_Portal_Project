package ExamPortal;

import ExamPortal.controllers.ExamResultController;
import ExamPortal.entities.ExamResultResponse;
import ExamPortal.resource.ExamResultResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamResultControllerTest {

    @Mock
    private ExamResultResource examResultResource;

    @InjectMocks
    private ExamResultController examResultController;

    @Test
    void fetchStudentExamResult_Success() {
        // Arrange
        int studentId = 123;  // Set up the student ID for testing

        ResponseEntity<ExamResultResponse> mockResponseEntity = new ResponseEntity<>(new ExamResultResponse(), HttpStatus.OK);
        when(examResultResource.fetchStudentExamResult(studentId)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<ExamResultResponse> responseEntity = examResultController.fetchStudentExamResult(studentId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void fetchStudentExamResultGradeWise_Success() {
        // Arrange
        int gradeId = 456;

        ResponseEntity<ExamResultResponse> mockResponseEntity = new ResponseEntity<>(new ExamResultResponse(), HttpStatus.OK);
        when(examResultResource.fetchStudentExamResultGradeWise(gradeId)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<ExamResultResponse> responseEntity = examResultController.fetchStudentExamResultGradeWise(gradeId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void fetchAllStudentResults_Success() {
        // Arrange
        ResponseEntity<ExamResultResponse> mockResponseEntity = new ResponseEntity<>(new ExamResultResponse(), HttpStatus.OK);
        when(examResultResource.fetchAllStudentResults()).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<ExamResultResponse> responseEntity = examResultController.fetchAllStudentResults();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}

