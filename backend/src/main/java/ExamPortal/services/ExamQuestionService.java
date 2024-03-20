package ExamPortal.services;

import ExamPortal.entities.Question;

public interface ExamQuestionService {
	
	Question addExamQuestion(Question question);
	
	Question getExamQuestionById(int questionId);
	
	void deleteQuestion(Question question);

}
