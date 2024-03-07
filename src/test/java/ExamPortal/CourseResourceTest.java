package ExamPortal;

import ExamPortal.dto.CourseResponseDto;
import ExamPortal.entities.AddCourseRequest;
import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.Course;
import ExamPortal.entities.Grade;
import ExamPortal.resource.CourseResource;
import ExamPortal.services.CourseService;
import ExamPortal.services.GradeService;
import ExamPortal.utility.Constants.ActiveStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseResourceTest {

    @Mock
    private CourseService courseService;

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private CourseResource courseResource;

    @Test
    void addCourse_Success() {
        // Arrange
        AddCourseRequest request = new AddCourseRequest();
        request.setName("Software Engineering");
        request.setDescription("Advanced Software Engineering");
        request.setGradeId(1);

        when(gradeService.getGradeById(1)).thenReturn(new Grade());
        when(courseService.addCourse(any())).thenReturn(new Course());

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = courseResource.addCourse(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Course Added Successful", responseEntity.getBody().getResponseMessage());
    }

    @Test
    void addCourse_GradeNotFound() {
        // Arrange
        AddCourseRequest request = new AddCourseRequest();
        request.setName("Software Engineering");
        request.setDescription("Advanced Software Engineering");
        request.setGradeId(1);

        when(gradeService.getGradeById(1)).thenReturn(null);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = courseResource.addCourse(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("grade not found", responseEntity.getBody().getResponseMessage());
    }

    @Test
    void fetchAllCourse_Success() {
        // Arrange
        when(courseService.getAllCoursesByStatus(ActiveStatus.ACTIVE.value())).thenReturn(Arrays.asList(new Course()));

        // Act
        ResponseEntity<CourseResponseDto> responseEntity = courseResource.fetchAllCourse();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Courses fetched successful", responseEntity.getBody().getResponseMessage());
        assertFalse(CollectionUtils.isEmpty(responseEntity.getBody().getCourses()));
    }

}

