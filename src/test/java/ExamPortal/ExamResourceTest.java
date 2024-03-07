package ExamPortal;

import ExamPortal.dto.ExamResponseDto;
import ExamPortal.entities.*;
import ExamPortal.resource.ExamResource;
import ExamPortal.services.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamResourceTest {

    @Mock
    private CourseService courseService;

    @Mock
    private UserService userService;

    @Mock
    private GradeService gradeService;

    @Mock
    private ExamService examService;

    @Mock
    private ExamResultService examResultService;

    @InjectMocks
    private ExamResource examResource;

    @Test
    void addExam_success() {
        // Arrange
        AddExamRequest request = new AddExamRequest();
        request.setTeacherId(1);
        request.setGradeId(1);
        request.setCourseId(1);
        request.setEndTime(String.valueOf(LocalDateTime.now().plusHours(1)));
        request.setName("Test Exam");
        request.setStartTime(String.valueOf(LocalDateTime.now()));

        when(userService.getUserById(1)).thenReturn(new User());
        when(gradeService.getGradeById(1)).thenReturn(new Grade());
        when(courseService.getCourseById(1)).thenReturn(new Course());
        when(examService.addExam(any(Exam.class))).thenReturn(new Exam());

        // Act
        ResponseEntity<ExamResponseDto> responseEntity = examResource.addExam(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Exam Added Successful", responseEntity.getBody().getResponseMessage());
        assertFalse(CollectionUtils.isEmpty(responseEntity.getBody().getExams()));
    }

    @Test
    void addExam_missingInput() {
        // Arrange
        AddExamRequest request = null;

        // Act
        ResponseEntity<ExamResponseDto> responseEntity = examResource.addExam(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("missing input or bad request", responseEntity.getBody().getResponseMessage());
        assertTrue(responseEntity.getBody().getExams().isEmpty());
    }


}

