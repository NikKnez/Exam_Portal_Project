package ExamPortal.dto;


import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class QuestionsResponseDto extends CommonApiResponse {

	private List<Question> questions = new ArrayList<>();

}
