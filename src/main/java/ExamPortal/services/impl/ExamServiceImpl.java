package ExamPortal.services.impl;


import ExamPortal.repositories.ExamRepository;
import ExamPortal.entities.Course;
import ExamPortal.entities.Exam;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import ExamPortal.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Override
	public Exam addExam(Exam exam) {
		// TODO Auto-generated method stub
		return this.examRepository.save(exam);
	}

	@Override
	public Exam updateExam(Exam exam) {
		// TODO Auto-generated method stub
		return this.examRepository.save(exam);
	}

	@Override
	public Exam getExamById(int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);

		if (optionalExam.isPresent()) {
			return optionalExam.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Exam> getAllExamsByStatus(String status) {
		return this.examRepository.findByStatus(status);
	}

	@Override
	public List<Exam> getAllExamsByGradeAndStatus(Grade grade, String status) {
		return this.examRepository.findByGradeAndStatus(grade, status);
	}

	@Override
	public List<Exam> getAllExamsByCourseAndStatus(Course course, String status) {
		return this.examRepository.findByCourseAndStatus(course, status);
	}

	@Override
	public List<Exam> getAllExamsByTeacherAndStatus(User teacher, String status) {
		return this.examRepository.findByTeacherAndStatus(teacher, status);
	}

	public List<Exam> getExamsByGradeAndStartTimeGreaterThanAndStatus(Grade grade, String startTime, String status) {
		return this.examRepository.findByGradeAndStartTimeGreaterThanAndStatus(grade, startTime, status);

	}

	@Override
	public List<Exam> getExamsByGradeAndStartTimeLessThanAndStatus(Grade grade, String startTime, String status) {
		// TODO Auto-generated method stub
		return this.examRepository.findByGradeAndStartTimeLessThanAndStatus(grade, startTime, status);
	}

	@Override
	public List<Exam> getExamsByGradeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(Grade grade,
			String currentTime, String currenTime, String status) {

		return this.examRepository.findByGradeAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(grade, currentTime,
				currenTime, status);
	}

}
