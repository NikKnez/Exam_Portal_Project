package ExamPortal.repositories;

import ExamPortal.entities.Exam;
import ExamPortal.entities.ExamResult;
import ExamPortal.entities.Grade;
import ExamPortal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {

	List<ExamResult> findByExam(Exam exam);

	List<ExamResult> findByStudent(User student);

	List<ExamResult> findByStudentAndExam(User student, Exam exam);

	List<ExamResult> findByExam_Grade(Grade grade);

}
