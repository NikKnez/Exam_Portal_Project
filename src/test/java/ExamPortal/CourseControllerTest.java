package ExamPortal;

import ExamPortal.controllers.CourseController;
import ExamPortal.dto.CourseResponseDto;
import ExamPortal.entities.AddCourseRequest;
import ExamPortal.entities.CommonApiResponse;
import ExamPortal.resource.CourseResource;
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
class CourseControllerTest {

    @Mock
    private CourseResource courseResource;

    @InjectMocks
    private CourseController courseController;

    @Test
    void addCourse_Success() {
        // Arrange
        AddCourseRequest request = new AddCourseRequest();

        ResponseEntity<CommonApiResponse> mockResponseEntity = new ResponseEntity<>(new CommonApiResponse(), HttpStatus.OK);
        when(courseResource.addCourse(request)).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = courseController.addCourse(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void fetchAllCourse_Success() {
        // Arrange
        ResponseEntity<CourseResponseDto> mockResponseEntity = new ResponseEntity<>(new CourseResponseDto(), HttpStatus.OK);
        when(courseResource.fetchAllCourse()).thenReturn(mockResponseEntity);

        // Act
        ResponseEntity<CourseResponseDto> responseEntity = courseController.fetchAllCourse();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}

