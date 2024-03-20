package ExamPortal;

import ExamPortal.dto.AddQuestionDto;
import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.entities.Question;
import ExamPortal.resource.ExamQuestionResource;
import ExamPortal.services.ExamQuestionService;
import ExamPortal.services.ExamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamQuestionResourceTest {

    @Mock
    private ExamService examService;

    @Mock
    private ExamQuestionService examQuestionService;

    @InjectMocks
    private ExamQuestionResource examQuestionResource;

    @Test
    void addExamQuestion_MissingInput() {
        // Arrange
        AddQuestionDto request = new AddQuestionDto();

        // Act
        ResponseEntity<QuestionsResponseDto> responseEntity = examQuestionResource.addExamQuestion(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("missing input or bad request", responseEntity.getBody().getResponseMessage());


    }

    @Test
    void deleteExamQuestion_Success() {
        // Arrange
        int questionId = 1;
        when(examQuestionService.getExamQuestionById(questionId)).thenReturn(new Question());

        // Act
        ResponseEntity<QuestionsResponseDto> responseEntity = examQuestionResource.deleteExamQuestion(questionId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Exam Questions Deleted Successful", responseEntity.getBody().getResponseMessage());


    }

    @Test
    void deleteExamQuestion_MissingInput() {
        // Arrange
        int questionId = 0;

        // Act
        ResponseEntity<QuestionsResponseDto> responseEntity = examQuestionResource.deleteExamQuestion(questionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("missing input or bad request", responseEntity.getBody().getResponseMessage());


    }
}

