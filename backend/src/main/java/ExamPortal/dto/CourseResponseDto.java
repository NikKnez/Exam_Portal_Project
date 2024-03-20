package ExamPortal.dto;


import ExamPortal.entities.CommonApiResponse;
import ExamPortal.entities.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CourseResponseDto extends CommonApiResponse {

	private List<Course> courses = new ArrayList<>();

}
