package ExamPortal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ExamResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int totalQuestions;

	private int totalWrongAnswers;

	private int totalCorrectAnswers;

	private double totalMarks;

	private double score; // Store the score achieved by the student

	private double percentage; // Store the percentage obtained

	private String resultStatus; // Pass, Failed

	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private User student;

	private String dateTime;

}
