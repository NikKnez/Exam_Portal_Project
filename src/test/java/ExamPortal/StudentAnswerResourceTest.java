package ExamPortal;

import ExamPortal.dto.QuestionsResponseDto;
import ExamPortal.entities.StudentAnswerRequest;
import ExamPortal.resource.StudentAnswerResource;
import ExamPortal.services.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentAnswerResourceTest {

    @Mock
    private StudentAnswerService studentAnswerService;

    @Mock
    private ExamService examService;

    @Mock
    private UserService userService;

    @Mock
    private ExamQuestionService examQuestionService;

    @Mock
    private ExamResultService examResultService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private StudentAnswerResource studentAnswerResource;

    @Test
    void addStudentAnswers_InvalidRequest_BadRequest() {
        // Arrange
        StudentAnswerRequest request = createInvalidRequest();

        // Act
        ResponseEntity<QuestionsResponseDto> response = studentAnswerResource.addStudentAnswers(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }



    private StudentAnswerRequest createInvalidRequest() {
        StudentAnswerRequest request = new StudentAnswerRequest();
        return request;
    }


}

