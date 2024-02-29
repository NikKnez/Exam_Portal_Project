package ExamPortal.services.impl;

import ExamPortal.repositories.ExamResultRepository;
import ExamPortal.entities.Exam;
import ExamPortal.entities.ExamResult;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import ExamPortal.services.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamResultServiceImpl implements ExamResultService {
	
	@Autowired
	private ExamResultRepository examResultRepository;

	@Override
	public ExamResult addResult(ExamResult result) {
		return examResultRepository.save(result);
	}

	@Override
	public List<ExamResult> getResultsByStudent(User student) {
		return examResultRepository.findByStudent(student);
	}

	@Override
	public List<ExamResult> getResultsByStudentAndExam(User student, Exam exam) {
		return examResultRepository.findByStudentAndExam(student, exam);
	}

	@Override
	public List<ExamResult> getResultsByExam(Exam exam) {
		return examResultRepository.findByExam(exam);
	}

	@Override
	public ExamResult getExamResultById(int resultId) {
		Optional<ExamResult> optionalExamResult = examResultRepository.findById(resultId);

		if (optionalExamResult.isPresent()) {
			return optionalExamResult.get();
		} else {
			return null;
		}
	}

	@Override
	public List<ExamResult> getResultsByGrade(Grade grade) {
		return this.examResultRepository.findByExam_Grade(grade);
	}

	@Override
	public List<ExamResult> getAllResults() {
		return this.examResultRepository.findAll();
	}

}
