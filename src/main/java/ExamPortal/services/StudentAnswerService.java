package ExamPortal.services;

import ExamPortal.entities.Exam;
import ExamPortal.entities.Question;
import ExamPortal.entities.StudentAnswer;
import ExamPortal.entities.User;

import java.util.List;

public interface StudentAnswerService {
	
	StudentAnswer addAnswer(StudentAnswer answer);
	
	StudentAnswer getAnswer(int answerId);
	
	StudentAnswer getAnswerByQuestion(Question question);
	
	List<StudentAnswer> getAllAnswerByExam(Exam exam);
	
	List<StudentAnswer> addAnswers(List<StudentAnswer> answers);
	
	List<StudentAnswer> getByExamAndStudent(Exam exam, User student);


}
