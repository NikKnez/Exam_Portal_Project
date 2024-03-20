package ExamPortal.dto;

import ExamPortal.entities.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
public class AddQuestionDto {

	private String question;

	private String options; // ["1st","2nd","3rd","4th"]

	private int correctAnswer; // index of above array

	private double marks;

	private int examId;

	public static Question toQuestionEntity(AddQuestionDto addQuestionDto) {
		Question question = new Question();
		BeanUtils.copyProperties(addQuestionDto, question, "examId");
		return question;
	}

	public static boolean validate(AddQuestionDto request) {

		if (request.getQuestion() == null || request.getOptions() == null || request.getMarks() == 0
				|| request.getExamId() == 0) {
			return false;
		}

		return true;

	}

}
