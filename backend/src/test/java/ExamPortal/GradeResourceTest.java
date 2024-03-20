package ExamPortal;

import ExamPortal.dto.GradeResponseDto;
import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.Grade;
import ExamPortal.resource.GradeResource;
import ExamPortal.services.GradeService;
import ExamPortal.utility.Constants.ActiveStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeResourceTest {

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private GradeResource gradeResource;

    @Test
    void addGrade_successfulAddition() {
        // Arrange
        Grade grade = new Grade();
        grade.setName("A");
        grade.setStatus(ActiveStatus.ACTIVE.value());

        when(gradeService.addGrade(grade)).thenReturn(grade);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeResource.addGrade(grade);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Grade Added Successful", responseEntity.getBody().getResponseMessage());
    }

    @Test
    void addGrade_nullInput() {
        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeResource.addGrade(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("missing input", responseEntity.getBody().getResponseMessage());
    }


    @Test
    void updateGrade_successfulUpdate() {
        // Arrange
        Grade grade = new Grade();
        grade.setId(1);
        grade.setName("B");
        grade.setStatus(ActiveStatus.ACTIVE.value());

        when(gradeService.updateGrade(grade)).thenReturn(grade);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeResource.updateGrade(grade);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Grade Updated Successful", responseEntity.getBody().getResponseMessage());
    }

    @Test
    void updateGrade_nullInput() {
        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeResource.updateGrade(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("missing input", responseEntity.getBody().getResponseMessage());
    }


    @Test
    void fetchAllGrade_successfulFetch() {
        // Arrange
        List<Grade> grades = new ArrayList<>();
        Grade grade = new Grade();
        grade.setId(1);
        grade.setName("A");
        grade.setStatus(ActiveStatus.ACTIVE.value());
        grades.add(grade);

        when(gradeService.getAllGradesByStatus(ActiveStatus.ACTIVE.value())).thenReturn(grades);

        // Act
        ResponseEntity<GradeResponseDto> responseEntity = gradeResource.fetchAllGrade();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Grade fetched successful", responseEntity.getBody().getResponseMessage());
        assertFalse(responseEntity.getBody().getGrades().isEmpty());
    }

    @Test
    void fetchAllGrade_noGradesFound() {
        // Arrange
        when(gradeService.getAllGradesByStatus(ActiveStatus.ACTIVE.value())).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<GradeResponseDto> responseEntity = gradeResource.fetchAllGrade();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess()); // Change this line
        assertEquals("No Grades found", responseEntity.getBody().getResponseMessage());
        assertTrue(responseEntity.getBody().getGrades().isEmpty());
    }


    @Test
    void deleteGrade_successfulDeletion() {
        // Arrange
        int gradeId = 1;

        Grade grade = new Grade();
        grade.setId(gradeId);
        grade.setStatus(ActiveStatus.ACTIVE.value());

        when(gradeService.getGradeById(gradeId)).thenReturn(grade);
        when(gradeService.updateGrade(grade)).thenReturn(grade);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeResource.deleteGrade(gradeId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isSuccess());
        assertEquals("Grade Deleted Successful", responseEntity.getBody().getResponseMessage());
    }

    @Test
    void deleteGrade_missingGradeId() {
        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeResource.deleteGrade(0);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("missing grade Id", responseEntity.getBody().getResponseMessage());
    }

    @Test
    void deleteGrade_gradeNotFound() {
        // Arrange
        int gradeId = 1;

        when(gradeService.getGradeById(gradeId)).thenReturn(null);

        // Act
        ResponseEntity<CommonApiResponse> responseEntity = gradeResource.deleteGrade(gradeId);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody().isSuccess());
        assertEquals("grade not found", responseEntity.getBody().getResponseMessage());
    }
}

