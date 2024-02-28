package ExamPortal.services;




import ExamPortal.entities.Exam;
import ExamPortal.entities.ExamResult;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;

import java.util.List;

public interface ExamResultService {
	
	ExamResult addResult(ExamResult result);
	
	ExamResult getExamResultById(int resultId);
	
	List<ExamResult> getResultsByStudent(User student);
	
	List<ExamResult> getResultsByStudentAndExam(User student, Exam exam);
	
	List<ExamResult> getResultsByExam(Exam exam);
	
	List<ExamResult> getResultsByGrade(Grade grade);
	
	List<ExamResult> getAllResults();

}
