package ExamPortal;

import ExamPortal.entities.Course;
import ExamPortal.entities.Grade;
import ExamPortal.repositories.CourseRepository;
import ExamPortal.services.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCourse() {
        // Arrange
        Course expectedCourse = new Course();
        when(courseRepository.save(any(Course.class))).thenReturn(expectedCourse);

        // Act
        Course addedCourse = courseService.addCourse(new Course());

        // Assert
        assertNotNull(addedCourse);
        assertEquals(expectedCourse, addedCourse);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testUpdateCourse() {
        // Arrange
        Course course = new Course();
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        Course updatedCourse = courseService.updateCourse(new Course());

        // Assert
        assertNotNull(updatedCourse);
        assertEquals(course, updatedCourse);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testGetCourseById() {
        // Arrange
        int courseId = 1;
        Course expectedCourse = new Course();
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(expectedCourse));

        // Act
        Course retrievedCourse = courseService.getCourseById(courseId);

        // Assert
        assertNotNull(retrievedCourse);
        assertEquals(expectedCourse, retrievedCourse);
        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void testGetAllCoursesByStatus() {
        // Arrange
        String status = "ACTIVE";
        List<Course> expectedCourses = List.of(new Course(), new Course());
        when(courseRepository.findByStatus(status)).thenReturn(expectedCourses);

        // Act
        List<Course> retrievedCourses = courseService.getAllCoursesByStatus(status);

        // Assert
        assertNotNull(retrievedCourses);
        assertEquals(expectedCourses, retrievedCourses);
        verify(courseRepository, times(1)).findByStatus(status);
    }

    @Test
    void testGetAllCoursesByGradeAndStatus() {
        // Arrange
        Grade grade = new Grade();
        String status = "ACTIVE";
        List<Course> expectedCourses = List.of(new Course(), new Course());
        when(courseRepository.findByGradeAndStatus(grade, status)).thenReturn(expectedCourses);

        // Act
        List<Course> retrievedCourses = courseService.getAllCoursesByGradeAndStatus(grade, status);

        // Assert
        assertNotNull(retrievedCourses);
        assertEquals(expectedCourses, retrievedCourses);
        verify(courseRepository, times(1)).findByGradeAndStatus(grade, status);
    }

}

