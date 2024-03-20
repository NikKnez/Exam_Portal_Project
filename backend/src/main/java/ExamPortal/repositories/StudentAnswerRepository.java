package ExamPortal.repositories;

import ExamPortal.entities.Exam;
import ExamPortal.entities.Question;
import ExamPortal.entities.StudentAnswer;
import ExamPortal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Integer> {
	
	List<StudentAnswer> findByExam(Exam exam);
	
	StudentAnswer findByQuestion(Question question);
	
	List<StudentAnswer> findByExamAndStudent(Exam exam, User student);

}
