package ExamPortal;

import ExamPortal.entities.Grade;
import ExamPortal.repositories.GradeRepository;
import ExamPortal.services.impl.GradeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GradeServiceImplTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeServiceImpl gradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddGrade() {
        // Arrange
        Grade gradeToSave = new Grade();
        when(gradeRepository.save(gradeToSave)).thenReturn(gradeToSave);

        // Act
        Grade savedGrade = gradeService.addGrade(gradeToSave);

        // Assert
        assertNotNull(savedGrade);
        assertEquals(gradeToSave, savedGrade);
        verify(gradeRepository, times(1)).save(gradeToSave);
    }

    @Test
    void testUpdateGrade() {
        // Arrange
        Grade gradeToUpdate = new Grade();
        when(gradeRepository.save(gradeToUpdate)).thenReturn(gradeToUpdate);

        // Act
        Grade updatedGrade = gradeService.updateGrade(gradeToUpdate);

        // Assert
        assertNotNull(updatedGrade);
        assertEquals(gradeToUpdate, updatedGrade);
        verify(gradeRepository, times(1)).save(gradeToUpdate);
    }

    @Test
    void testGetGradeById() {
        // Arrange
        int gradeId = 1;
        Grade expectedGrade = new Grade();
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(expectedGrade));

        // Act
        Grade retrievedGrade = gradeService.getGradeById(gradeId);

        // Assert
        assertNotNull(retrievedGrade);
        assertEquals(expectedGrade, retrievedGrade);
        verify(gradeRepository, times(1)).findById(gradeId);
    }

    @Test
    void testGetAllGradesByStatus() {
        // Arrange
        String status = "Active";
        List<Grade> expectedGrades = new ArrayList<>();
        when(gradeRepository.findByStatus(status)).thenReturn(expectedGrades);

        // Act
        List<Grade> retrievedGrades = gradeService.getAllGradesByStatus(status);

        // Assert
        assertNotNull(retrievedGrades);
        assertEquals(expectedGrades, retrievedGrades);
        verify(gradeRepository, times(1)).findByStatus(status);
    }
}

