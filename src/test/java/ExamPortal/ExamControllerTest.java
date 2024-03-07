package ExamPortal;

import ExamPortal.controllers.ExamController;
import ExamPortal.dto.ExamResponseDto;
import ExamPortal.entities.AddExamRequest;
import ExamPortal.resource.ExamResource;
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
class ExamControllerTest {

    @Mock
    private ExamResource examResource;

    @InjectMocks
    private ExamController examController;

    @Test
    void addExam_Success() {
        // Arrange
        AddExamRequest request = new AddExamRequest();

        ResponseEntity<ExamResponseDto> mockResponseEntity = new ResponseEntity<>(new ExamResponseDto(), HttpStatus.OK);
        when(examResource.addExam(request)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<ExamResponseDto> responseEntity = examController.addCourse(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void fetchAllExams_Success() {
        // Arrange
        ResponseEntity<ExamResponseDto> mockResponseEntity = new ResponseEntity<>(new ExamResponseDto(), HttpStatus.OK);
        when(examResource.fetchAllExams()).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<ExamResponseDto> responseEntity = examController.fetchAllExams();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}

