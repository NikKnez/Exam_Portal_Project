package ExamPortal;

import ExamPortal.controllers.StudentAnswerController;
import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.entities.StudentAnswerRequest;
import ExamPortal.resource.StudentAnswerResource;
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
class StudentAnswerControllerTest {

    @Mock
    private StudentAnswerResource studentAnswerResource;

    @InjectMocks
    private StudentAnswerController studentAnswerController;

    @Test
    void addExamQuestion_Success() {
        // Arrange
        StudentAnswerRequest request = new StudentAnswerRequest();
        // Set up any required data in the request

        ResponseEntity<QuestionsResponseDto> mockResponseEntity = new ResponseEntity<>(new QuestionsResponseDto(), HttpStatus.OK);
        when(studentAnswerResource.addStudentAnswers(request)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<QuestionsResponseDto> responseEntity = studentAnswerController.addExamQuestion(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}

