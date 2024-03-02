package ExamPortal.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCourseRequest {

	private int id;

	private int gradeId;

	private String name;

	private String description;

	public static boolean validate(AddCourseRequest request) {

        return request.getName() != null && request.getGradeId() != 0 &&
				request.getDescription() != null;

    }

}
