package ExamPortal.services;

import ExamPortal.entities.Course;
import ExamPortal.entities.Exam;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;

import java.util.List;

public interface ExamService {
	
	Exam addExam(Exam exam);

	Exam updateExam(Exam exam);

	Exam getExamById(int examId);

	List<Exam> getAllExamsByStatus(String status);
	
	List<Exam> getAllExamsByGradeAndStatus(Grade grade, String status);
	
	List<Exam> getAllExamsByCourseAndStatus(Course course, String status);
	
	List<Exam> getAllExamsByTeacherAndStatus(User teacher, String status);
	
	List<Exam> getExamsByGradeAndStartTimeGreaterThanAndStatus(Grade grade, String startTime, String status);	
	
	List<Exam> getExamsByGradeAndStartTimeLessThanAndStatus(Grade grade, String startTime, String status);

	List<Exam> getExamsByGradeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(Grade grade, String currentTime,
			String currenTime, String status);

}
