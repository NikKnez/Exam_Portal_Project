package ExamPortal;

import ExamPortal.controllers.GradeController;
import ExamPortal.dto.GradeResponseDto;
import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.Grade;
import ExamPortal.resource.GradeResource;
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
class GradeControllerTest {

    @Mock
    private GradeResource gradeResource;

    @InjectMocks
    private GradeController gradeController;

    @Test
    void addGrade_Success() {
        // Arrange
        Grade grade = new Grade();

        ResponseEntity<CommonApiResponse> mockResponseEntity = new ResponseEntity<>(new CommonApiResponse(), HttpStatus.OK);
        when(gradeResource.addGrade(grade)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeController.addGrade(grade);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void fetchAllGrade_Success() {
        // Arrange
        ResponseEntity<GradeResponseDto> mockResponseEntity = new ResponseEntity<>(new GradeResponseDto(), HttpStatus.OK);
        when(gradeResource.fetchAllGrade()).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<GradeResponseDto> responseEntity = gradeController.fetchAllGrade();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}

