package ExamPortal.dto;


import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.StudentAnswer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class QuestionAnswerResponseDto extends CommonApiResponse {

	List<StudentAnswer> questionAnswers = new ArrayList<>();

}
