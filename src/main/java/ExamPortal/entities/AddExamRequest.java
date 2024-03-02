package ExamPortal.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddExamRequest {

	private int id;

	private String name;

	private int teacherId;

	private int courseId;

	private int gradeId;

	private String startTime;

	private String endTime;

	public static boolean validate(AddExamRequest request) {

		if (request.getName() == null || request.getTeacherId() == 0 || request.getCourseId() == 0
				|| request.getGradeId() == 0 || request.getStartTime() == null || request.getEndTime() == null) {
			return false;
		}

		return true;

	}

}
