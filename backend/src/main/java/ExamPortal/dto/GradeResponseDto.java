package ExamPortal.dto;


import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.Grade;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class GradeResponseDto extends CommonApiResponse {

	private List<Grade> grades = new ArrayList<>();

}
