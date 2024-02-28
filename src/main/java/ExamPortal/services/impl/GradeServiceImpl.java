package ExamPortal.services.impl;


import ExamPortal.repositories.GradeRepository;
import ExamPortal.entities.Grade;
import ExamPortal.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public Grade addGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

	@Override
	public Grade updateGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

	@Override
	public Grade getGradeById(int gradeId) {
		Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);

		if (optionalGrade.isPresent()) {
			return optionalGrade.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Grade> getAllGradesByStatus(String status) {
		return this.gradeRepository.findByStatus(status);
	}

}
