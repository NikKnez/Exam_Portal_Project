package ExamPortal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
public class Exam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String addedDateTime;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;  // exam questions added by Role Teacher
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "grade_id")
	private Grade grade;
	
	private String startTime;
	
	private String endTime;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "exam_question_mapping",
        joinColumns = @JoinColumn(name = "exam_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();
	
	private String status;
	
	@Transient
	private String message;


}
