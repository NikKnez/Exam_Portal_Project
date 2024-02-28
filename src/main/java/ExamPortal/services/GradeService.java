package ExamPortal.services;


import ExamPortal.entities.Grade;

import java.util.List;

public interface GradeService {

	Grade addGrade(Grade grade);

	Grade updateGrade(Grade grade);

	Grade getGradeById(int gradeId);

	List<Grade> getAllGradesByStatus(String status);

}
