package ExamPortal.services.impl;

import ExamPortal.repositories.StudentAnswerRepository;
import ExamPortal.entities.Exam;
import ExamPortal.entities.Question;
import ExamPortal.entities.StudentAnswer;
import ExamPortal.entities.User;
import ExamPortal.services.StudentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

	@Autowired
	private StudentAnswerRepository studentAnswerRepository;

	@Override
	public StudentAnswer addAnswer(StudentAnswer answer) {
		// TODO Auto-generated method stub
		return studentAnswerRepository.save(answer);
	}

	@Override
	public StudentAnswer getAnswer(int answerId) {
		Optional<StudentAnswer> optionalAnswer = studentAnswerRepository.findById(answerId);

		if (optionalAnswer.isPresent()) {
			return optionalAnswer.get();
		} else {
			return null;
		}
	}

	@Override
	public StudentAnswer getAnswerByQuestion(Question question) {
		return studentAnswerRepository.findByQuestion(question);
	}

	@Override
	public List<StudentAnswer> getAllAnswerByExam(Exam exam) {
		// TODO Auto-generated method stub
		return studentAnswerRepository.findByExam(exam);
	}

	@Override
	public List<StudentAnswer> addAnswers(List<StudentAnswer> answers) {
		// TODO Auto-generated method stub
		return studentAnswerRepository.saveAll(answers);
	}

	@Override
	public List<StudentAnswer> getByExamAndStudent(Exam exam, User student) {
		return studentAnswerRepository.findByExamAndStudent(exam, student);

	}

}
