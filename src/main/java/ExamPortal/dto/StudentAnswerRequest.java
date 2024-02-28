package ExamPortal.dto;


import ExamPortal.entities.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class StudentAnswerRequest {
	
    private List<Question> questions = new ArrayList<>();
    
    private int studentId;
    
    private int examId;

	public static boolean validate(StudentAnswerRequest request) {
        return request.getExamId() != 0 && request.getStudentId() != 0 &&
				!CollectionUtils.isEmpty(request.getQuestions());
    }
    
}
