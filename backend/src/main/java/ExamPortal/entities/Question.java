package ExamPortal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String question;

	private String options; // ["1st","2nd","3rd","4th"]

	@JsonIgnore
	private int correctAnswer; // index of above array

	private double marks;

	private String status;

	@JsonIgnore
	@ManyToMany(mappedBy = "questions")
	private List<Exam> exams = new ArrayList<>();

	@Transient
	private int answer;

	@Transient
	private int correctAns;

}
