package ExamPortal;

import ExamPortal.controllers.ExamQuestionController;
import ExamPortal.dto.AddQuestionDto;
import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.resource.ExamQuestionResource;
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
class ExamQuestionControllerTest {

    @Mock
    private ExamQuestionResource examQuestionResource;

    @InjectMocks
    private ExamQuestionController examQuestionController;

    @Test
    void addExamQuestion_Success() {
        // Arrange
        AddQuestionDto request = new AddQuestionDto();

        ResponseEntity<QuestionsResponseDto> mockResponseEntity = new ResponseEntity<>(new QuestionsResponseDto(), HttpStatus.OK);
        when(examQuestionResource.addExamQuestion(request)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<QuestionsResponseDto> responseEntity = examQuestionController.addExamQuestion(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void deleteQuestion_Success() {
        // Arrange
        int questionId = 789;  // Set up the question ID for testing

        ResponseEntity<QuestionsResponseDto> mockResponseEntity = new ResponseEntity<>(new QuestionsResponseDto(), HttpStatus.OK);
        when(examQuestionResource.deleteExamQuestion(questionId)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<QuestionsResponseDto> responseEntity = examQuestionController.deleteQuestion(questionId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}

