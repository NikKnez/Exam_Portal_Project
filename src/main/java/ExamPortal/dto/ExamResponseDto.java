package ExamPortal.dto;


import ExamPortal.entities.Exam;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class ExamResponseDto extends CommonApiResponse {
	
	private List<Exam> exams = new ArrayList<>();

}
